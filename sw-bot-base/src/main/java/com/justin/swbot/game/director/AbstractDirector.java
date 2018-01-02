/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.util.CommandUtil;
import com.justin.swbot.util.ImageUtil;
import com.justin.swbot.util.OcrUtil;
import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.GameState;
import com.justin.swbot.game.GameStatus;
import com.justin.swbot.game.Profile;
import com.justin.swbot.ui.HomeView;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.justin.swbot.game.indicator.Indicator.fiveStarRuneIndicator;
import static com.justin.swbot.game.indicator.Indicator.sixStarRuneIndicator;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class AbstractDirector implements ScenarioDirector {
  private int availableRefillTime;
  private int battleCount;
  private int deadCount;
  private Profile profile;

  @Override
  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  @Override
  public boolean direct(final GameStatus gameStatus) {
    final GameState gameState = gameStatus.getGameState();
    if (gameState == GameState.BATTLE_MANUAL) {
      enableAutoAttackMode();
      return true;
    } else if (gameState == GameState.BATTLE_ENDED) {
      ackBattleResult();
      return true;
    } else if (gameState == GameState.BATTLE_ENDED_FAIL) {
      ackBattleResultFailure();
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
      wait4Battle();
      return true;
    } else if (gameState == GameState.NO_CRYS) {
      notRefillCrys();
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
    availableRefillTime = Integer.valueOf(profile.getRefillTimes());
    battleCount = 0;
    deadCount = 0;
  }

  /**
   * Acknowledge the battle result by click somewhere on the screen
   */
  protected void ackBattleResult() {
    progressMessage("Ending battle...");
    tapScreen("400", "900");
    sleep(1000);
    tapScreen("400", "900");
  }

  protected void ackBattleResultFailure() {
    progressMessage("Battle fail!!! Not revive...");
    tapScreen(profile.getReviveNoX(), profile.getReviveNoY());

    sleep(100);
    tapScreen("400", "900");
    sleep(100);
    replayBattle();
    deadCount++;
  }

  /**
   * Collect rune on battle result screen.
   */
  protected void collectRune(final GameStatus gameStatus) throws IOException {
    boolean pickRune = profile.isPickAllRune();
    if (!profile.isPickAllRune()) {
      pickRune = applyRuneFilter(gameStatus);
    }
    if (pickRune) {
      progressMessage("Collecting rune...");
      tapScreen(profile.getGetRuneLocationX(), profile.getGetRuneLocationY());
      if (profile.isRuneLog()) {
        screenLog(gameStatus, new File("runeLog"));
      }
    } else {
      // Rune will be sold if non of rules are matching
      sellRune(gameStatus);
    }
  }

  protected void collectStone(final GameStatus gameStatus) throws IOException {
    boolean pickRune = profile.isPickAllRune();
    if (!profile.isPickAllRune()) {
      pickRune = applyStoneFilter(gameStatus);
    }
    if (pickRune) {
      progressMessage("Collecting stone...");
      tapScreen(profile.getGetGemLocationX(), profile.getGetGemLocationY());
      if (profile.isRuneLog()) {
        screenLog(gameStatus, new File("runeLog"));
      }
    } else {
      // Rune will be sold if non of rules are matching
      sellStone(gameStatus);
    }
  }

  protected void confirmSellRune() {
    progressMessage("Confirm to sell rune...");
    tapScreen(profile.getSellRuneConfirmationX(), profile.getSellRuneConfirmationY());

    sleep(100);
    replayBattle();
  }

  protected void confirmSellStone() {
    progressMessage("Confirm to sell stone...");
    tapScreen(profile.getSellStoneConfirmationX(), profile.getSellStoneConfirmationY());

    sleep(100);
    replayBattle();
  }

  /**
   * Enable auto attack mode by clicking on play icon (third button) at bottom left
   */
  protected void enableAutoAttackMode() {
    progressMessage("Enabling auto mode...");
    tapScreen(profile.getEnableAutoModeX(), profile.getEnableAutoModeY());
  }

  protected void notRefillCrys() {
    progressMessage("Not refill crys, wait for energy instead...");
    tapScreen(profile.getRechargeCrysNoX(), profile.getRechargeCrysNoY());
    availableRefillTime = 0;
  }

  protected void proceedGemReward(final GameStatus gameStatus) {
    if (profile.isSellAllRune()) {
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
    tapScreen(profile.getGetRewardLocationX(), profile.getGetRewardLocationY());
  }

  protected void proceedRuneReward(final GameStatus gameStatus) {
    if (profile.isSellAllRune()) {
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
    final HomeView homeView = DependenciesRegistry.homeView;
    homeView.updateStatus(String.format(message, args));
  }

  protected void refillEnergy() {
    progressMessage("Refilling energy...");
    // On screen of not enough energy, select YES on to recharges energy
    tapScreen(profile.getRechargeEnergyYesX(), profile.getRechargeEnergyYesY());
    sleep(500);
    // On shop screen, select energy
    tapScreen(profile.getRechargeEnergyX(), profile.getRechargeEnergyY());
    sleep(500);
    // On shop screen, confirm to purchase energy with 30 crystals
    tapScreen(profile.getConfirmRechargeEnergyX(), profile.getConfirmRechargeEnergyY());
    sleep(2000);
    // On shop screen, click OK confirm purchase successful
    tapScreen(profile.getAckRechargeEnergyOkX(), profile.getAckRechargeEnergyOkY());
    sleep(500);
    // Close the shop screen
    tapScreen(profile.getCloseRechargeEnergyX(), profile.getCloseRechargeEnergyY());
  }

  protected void replayBattle() {
    progressMessage("Replaying battle...");
    tapScreen(profile.getReplayBattleX(), profile.getReplayBattleY());

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
    tapScreen(profile.getSellRuneLocationX(), profile.getSellRuneLocationY());

    if (profile.isRuneLog()) {
      screenLog(gameStatus, new File("runeLog", "sold"));
    }
  }

  protected void sellStone(final GameStatus gameStatus) {
    progressMessage("Selling stone...");
    tapScreen(profile.getSellGemLocationX(), profile.getSellGemLocationY());

    if (profile.isRuneLog()) {
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
    tapScreen(profile.getStartBattleX(), profile.getStartBattleY());
    battleCount++;
    sleep(5000);
  }

  protected void wait4Battle() {
    progressMessage("Wait for result of battle (%s/%s), refill remain %s...", deadCount,
        battleCount, availableRefillTime);
    sleep(10000);
  }

  protected void waitForEnergy() {
    progressMessage("Insuffience energy and waiting...");
    tapScreen(profile.getRechargeEnergyNoX(), profile.getRechargeEnergyNoY());

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
    final BufferedImage screenImage = ImageIO.read(new File(gameStatus.getScreenFile()));
    if (profile.isPickLegendRune() || profile.isPickHeroRune()) {
      final Rectangle box = profile.getRareLevelAreaBox();
      final BufferedImage rareLevelImage =
          screenImage.getSubimage(box.x, box.y, box.width, box.height);
      final String rareLevel = OcrUtil.text(rareLevelImage);
      final boolean legend = rareLevel.equals("Legend");
      if (legend) {
        return true;
      }
      final boolean hero = rareLevel.equals("Hero");
      if (hero && profile.isPickHeroRune()) {
        return true;
      }
    }
    if (profile.isPick6StarRune()) {
      final boolean sixStar = ImageUtil.contains(gameStatus.getScreenFile(),
          profile.getIndicatorFile(sixStarRuneIndicator).getAbsolutePath(), 98) != null;
      if (sixStar) {
        return true;
      }
    }
    if (profile.isPick5StarRune()) {
      final boolean fiveStar = ImageUtil.contains(gameStatus.getScreenFile(),
          profile.getIndicatorFile(fiveStarRuneIndicator).getAbsolutePath(), 98) != null;
      if (fiveStar) {
        return true;
      }
      final boolean sixStar = ImageUtil.contains(gameStatus.getScreenFile(),
          profile.getIndicatorFile(sixStarRuneIndicator).getAbsolutePath(), 98) != null;
      if (sixStar) {
        return true;
      }
    }
    return false;
  }

  private boolean applyStoneFilter(final GameStatus gameStatus) throws IOException {
    final BufferedImage screenImage = ImageIO.read(new File(gameStatus.getScreenFile()));
    if (profile.isPickSpdPercentGrindstone()) {
      final Rectangle box = profile.getGrindstoneStatAreaBox();
      final BufferedImage grindImage = screenImage.getSubimage(box.x, box.y, box.width, box.height);
      final String grindOptions = OcrUtil.text(grindImage);
      final boolean percentOption = grindOptions.contains("°/o") || grindOptions.contains("%");
      final boolean spdOption = grindOptions.contains("SPD");
      if (percentOption || spdOption) {
        return true;
      }
    }
    return false;
  }

  private void confirmNetworkDelay() {
    progressMessage("Network delay!");
    tapScreen(profile.getConfirmNetworkDelayX(), profile.getConfirmNetworkDelayY());
  }

  private void proceedNotEnoughEnergy() {
    if (availableRefillTime <= 0) {
      waitForEnergy();
    } else {
      refillEnergy();
      availableRefillTime--;
    }
  }

  private void resendBattleInfo() {
    progressMessage("Network unstable! resending information...");
    tapScreen(profile.getResendBattleInfoX(), profile.getResendBattleInfoY());
  }

  private void tapScreen(final String x, final String y) {
    String tapX = x;
    String tapY = y;
    if (profile.isClickRandom()) {
      tapX = String.valueOf(Integer.valueOf(x) + (int) (10 * (Math.random() - Math.random())));
      tapY = String.valueOf(Integer.valueOf(y) + (int) (10 * (Math.random() - Math.random())));
    }
    CommandUtil.tapScreen(tapX, tapY);
  }
}
