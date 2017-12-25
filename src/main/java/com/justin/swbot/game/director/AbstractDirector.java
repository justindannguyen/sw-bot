/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import com.justin.swbot.CommandUtil;
import com.justin.swbot.ImageUtil;
import com.justin.swbot.OcrUtil;
import com.justin.swbot.game.ControllerRegistry;
import com.justin.swbot.game.GameConfig;
import com.justin.swbot.game.GameState;
import com.justin.swbot.game.GameStatus;
import com.justin.swbot.home.HomeController;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class AbstractDirector implements ScenarioDirector {
  private int availableRefillTime;
  private int battleCount;
  private int refillCount;

  @Override
  public boolean direct(final GameStatus gameStatus) {
    final GameState gameState = gameStatus.getGameState();
    if (gameState == GameState.BATTLE_MANUAL) {
      enableAutoAttackMode();
      return true;
    } else if (gameState == GameState.BATTLE_ENDED) {
      ackBattleResult();
      return true;
    } else if (gameState == GameState.RUNE_REWARD) {
      proceedRuneReward(gameStatus);
      return true;
    } else if (gameState == GameState.GEM_REWARD) {
      proceedGemReward(gameStatus);
      return true;
    } else if (gameState == GameState.OTHER_REWARD) {
      proceedOtherReward();
      return true;
    } else if (gameState == GameState.REPLAY_BATTLE_CONFIRMATION) {
      replayBattle();
      return true;
    } else if (gameState == GameState.START_BATTLE) {
      startBattle();
      return true;
    } else if (gameState == GameState.SELL_RUNE_CONFIRMATION) {
      confirmSellRune();
      return true;
    } else if (gameState == GameState.SELL_STONE_CONFIRMATION) {
      confirmSellStone();
      return true;
    } else if (gameState == GameState.NOT_ENOUGH_ENERGY) {
      proceedNotEnoughEnergy();
      return true;
    } else if (gameState == GameState.NETWORK_DELAY) {
      confirmNetworkDelay();
      return true;
    } else if (gameState == GameState.UNSTABLE_NETWORK) {
      resendBattleInfo();
      return true;
    } else if (gameState == GameState.IN_BATTLE) {
      progressMessage("Wait for result of battle no %s, refill remain %s...", battleCount,
          availableRefillTime);
      sleep(5000);
      return true;
    } else if (gameState == GameState.UNKNOWN) {
      // Log unknown situation where directive can't handle
      screenLog(gameStatus, new File("unknownStates"));
      sleep(10000);
      return true;
    }
    return false;
  }

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  @Override
  public void restart() {
    final GameConfig gameConfig = GameConfig.get();
    availableRefillTime = Integer.valueOf(gameConfig.getRefillTimes());
    battleCount = 0;
    refillCount = 0;
  }

  /**
   * Acknowledge the battle result by click somewhere on the screen
   */
  protected void ackBattleResult() {
    progressMessage("Ending battle...");
    tapScreen("50", "900");
    sleep(1000);
    tapScreen("50", "900");
  }

  /**
   * Collect rune on battle result screen.
   */
  protected void collectRune(final GameStatus gameStatus) throws IOException {
    final GameConfig gameConfig = GameConfig.get();
    boolean pickRune = gameConfig.isPickAllRune();
    if (!gameConfig.isPickAllRune()) {
      pickRune = applyRuneFilter(gameStatus);
    }
    if (pickRune) {
      progressMessage("Collecting rune...");
      tapScreen(gameConfig.getGetRuneLocationX(), gameConfig.getGetRuneLocationY());
      if (gameConfig.isRuneLog()) {
        screenLog(gameStatus, new File("runeLog"));
      }
    } else {
      // Rune will be sold if non of rules are matching
      sellRune(gameStatus);
    }
  }

  protected void collectStone(final GameStatus gameStatus) throws IOException {
    final GameConfig gameConfig = GameConfig.get();
    boolean pickRune = gameConfig.isPickAllRune();
    if (!gameConfig.isPickAllRune()) {
      pickRune = applyStoneFilter(gameStatus);
    }
    if (pickRune) {
      progressMessage("Collecting stone...");
      tapScreen(gameConfig.getGetGemLocationX(), gameConfig.getGetGemLocationY());
      if (gameConfig.isRuneLog()) {
        screenLog(gameStatus, new File("runeLog"));
      }
    } else {
      // Rune will be sold if non of rules are matching
      sellStone(gameStatus);
    }
  }

  protected void confirmSellRune() {
    progressMessage("Confirm to sell rune...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getSellRuneConfirmationX(), gameConfig.getSellRuneConfirmationY());

    sleep(100);
    replayBattle();
  }

  protected void confirmSellStone() {
    progressMessage("Confirm to sell stone...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getSellStoneConfirmationX(), gameConfig.getSellStoneConfirmationY());

    sleep(100);
    replayBattle();
  }

  /**
   * Enable auto attack mode by clicking on play icon (third button) at bottom left
   */
  protected void enableAutoAttackMode() {
    progressMessage("Enabling auto mode...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getEnableAutoModeX(), gameConfig.getEnableAutoModeY());
  }

  protected void proceedGemReward(final GameStatus gameStatus) {
    final GameConfig gameConfig = GameConfig.get();
    if (gameConfig.isSellAllRune()) {
      sellStone(gameStatus);
    } else {
      try {
        collectStone(gameStatus);
      } catch (final IOException ex) {
        throw new RuntimeException("Error when collect stone", ex);
      }
    }
  }

  protected void proceedOtherReward() {
    progressMessage("Collecting rewards...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getGetRewardLocationX(), gameConfig.getGetRewardLocationY());
  }

  protected void proceedRuneReward(final GameStatus gameStatus) {
    final GameConfig gameConfig = GameConfig.get();
    if (gameConfig.isSellAllRune()) {
      sellRune(gameStatus);
    } else {
      try {
        collectRune(gameStatus);
      } catch (final IOException ex) {
        throw new RuntimeException("Error when collect rune", ex);
      }
    }
  }

  protected void progressMessage(final String message, final Object... args) {
    final HomeController homeController =
        (HomeController) ControllerRegistry.get(HomeController.class);
    homeController.updateStatus(String.format(message, args));
  }

  protected void refillEnergy() {
    progressMessage("Refilling energy...");
    final GameConfig gameConfig = GameConfig.get();
    // On screen of not enough energy, select YES on to recharges energy
    tapScreen(gameConfig.getRechargeEnergyYesX(), gameConfig.getRechargeEnergyYesY());
    sleep(500);
    // On shop screen, select energy
    tapScreen(gameConfig.getRechargeEnergyX(), gameConfig.getRechargeEnergyY());
    sleep(500);
    // On shop screen, confirm to purchase energy with 30 crystals
    tapScreen(gameConfig.getConfirmRechargeEnergyX(), gameConfig.getConfirmRechargeEnergyY());
    sleep(2000);
    // On shop screen, click OK confirm purchase successful
    tapScreen(gameConfig.getAckRechargeEnergyOkX(), gameConfig.getAckRechargeEnergyOkY());
    sleep(500);
    // Close the shop screen
    tapScreen(gameConfig.getCloseRechargeEnergyX(), gameConfig.getCloseRechargeEnergyY());
  }

  protected void replayBattle() {
    progressMessage("Replaying battle...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getReplayBattleX(), gameConfig.getReplayBattleY());

    sleep(100);
    startBattle();
  }

  protected void screenLog(final GameStatus status, final File folder) {
    if (!folder.exists()) {
      folder.mkdirs();
    }
    try {
      Files.copy(new File(status.getScreenFile()).toPath(),
          new File(folder, String.format("%s.png", System.currentTimeMillis())).toPath());
    } catch (final IOException ex) {
      System.err.println("Could not log screenshoot");
    }
  }

  /**
   * Sell the rune on battle result screen.
   */
  protected void sellRune(final GameStatus gameStatus) {
    progressMessage("Selling rune...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getSellRuneLocationX(), gameConfig.getSellRuneLocationY());

    if (gameConfig.isRuneLog()) {
      screenLog(gameStatus, new File("runeLog", "sold"));
    }
  }

  protected void sellStone(final GameStatus gameStatus) {
    progressMessage("Selling stone...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getSellGemLocationX(), gameConfig.getSellGemLocationY());

    if (gameConfig.isRuneLog()) {
      screenLog(gameStatus, new File("runeLog", "sold"));
    }
  }

  protected void sleep(final long sleepMs) {
    try {
      Thread.sleep(sleepMs);
    } catch (final InterruptedException ex) {
      System.err.println("Could not sleep!!!");
    }
  }

  protected void startBattle() {
    progressMessage("Starting new battle...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getStartBattleX(), gameConfig.getStartBattleY());
    battleCount++;
    sleep(10000);
  }

  protected void waitForEnergy() {
    progressMessage("Insuffience energy and waiting...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getRechargeEnergyNoX(), gameConfig.getRechargeEnergyNoY());

    // Wait for 10 minutes
    final long time = System.currentTimeMillis();
    final long timeToWait = time + 30 * 60 * 1000;
    while (true) {
      sleep(1000);
      final long remainingTime = timeToWait - System.currentTimeMillis();
      if (remainingTime <= 0) {
        break;
      }

      progressMessage(String.format("No enough energy, resume in % seconds", remainingTime));
    }
  }

  private boolean applyRuneFilter(final GameStatus gameStatus) throws IOException {
    final GameConfig gameConfig = GameConfig.get();
    final BufferedImage screenImage = ImageIO.read(new File(gameStatus.getScreenFile()));
    if (gameConfig.isPickLegendRune() || gameConfig.isPickHeroRune()) {
      final Rectangle box = gameConfig.getRareLevelAreaBox();
      final BufferedImage rareLevelImage =
          screenImage.getSubimage(box.x, box.y, box.width, box.height);
      final String rareLevel = OcrUtil.text(rareLevelImage);
      final boolean legend = rareLevel.equals("Legend");
      if (legend) {
        return true;
      }
      final boolean hero = rareLevel.equals("Hero");
      if (hero && gameConfig.isPickHeroRune()) {
        return true;
      }
    }
    if (gameConfig.isPick6StarRune()) {
      final boolean sixStar = ImageUtil.contains(gameStatus.getScreenFile(),
          gameConfig.getSixStarRuneIndicatorFile().getAbsolutePath(), 98) != null;
      if (sixStar) {
        return true;
      }
    }
    if (gameConfig.isPick5StarRune()) {
      final boolean fiveStar = ImageUtil.contains(gameStatus.getScreenFile(),
          gameConfig.getFiveStarRuneIndicatorFile().getAbsolutePath(), 98) != null;
      if (fiveStar) {
        return true;
      }
      final boolean sixStar = ImageUtil.contains(gameStatus.getScreenFile(),
          gameConfig.getSixStarRuneIndicatorFile().getAbsolutePath(), 98) != null;
      if (sixStar) {
        return true;
      }
    }
    return false;
  }

  private boolean applyStoneFilter(final GameStatus gameStatus) throws IOException {
    final GameConfig gameConfig = GameConfig.get();
    final BufferedImage screenImage = ImageIO.read(new File(gameStatus.getScreenFile()));
    if (gameConfig.isPickSpdPercentGrindstone()) {
      final Rectangle box = gameConfig.getGrindstoneStatAreaBox();
      final BufferedImage grindImage = screenImage.getSubimage(box.x, box.y, box.width, box.height);
      final String grindOptions = OcrUtil.text(grindImage);
      final boolean percentOption = grindOptions.contains("°/o");
      final boolean spdOption = grindOptions.contains("SPD");
      if (percentOption || spdOption) {
        return true;
      }
    }
    return false;
  }

  private void confirmNetworkDelay() {
    progressMessage("Network delay!");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getConfirmNetworkDelayX(), gameConfig.getConfirmNetworkDelayY());
  }

  private void proceedNotEnoughEnergy() {
    if (availableRefillTime <= 0) {
      waitForEnergy();
    } else {
      refillEnergy();
      availableRefillTime--;
      refillCount++;
    }
  }

  private void resendBattleInfo() {
    progressMessage("Network unstable! resending information...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getResendBattleInfoX(), gameConfig.getResendBattleInfoY());
  }

  private void tapScreen(final String x, final String y) {
    final GameConfig gameConfig = GameConfig.get();
    String tapX = x;
    String tapY = y;
    if (gameConfig.isClickRandom()) {
      tapX = String.valueOf(Integer.valueOf(x) + (int) (10 * (Math.random() - Math.random())));
      tapY = String.valueOf(Integer.valueOf(y) + (int) (10 * (Math.random() - Math.random())));
    }
    CommandUtil.tapScreen(tapX, tapY);
  }
}
