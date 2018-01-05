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
import com.justin.swbot.game.profile.Profile;
import com.justin.swbot.ui.HomeView;
import com.justin.swbot.util.Point;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

import static com.justin.swbot.game.indicator.Indicator.fiveStarRuneIndicator;
import static com.justin.swbot.game.indicator.Indicator.sixStarRuneIndicator;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class AbstractDirector implements ScenarioDirector {
  private final CommandUtil commandUtil;
  private final OcrUtil ocrUtil;
  private HomeView homeView;

  @Getter
  private int availableRefillTime;
  @Getter
  private int battleCount;
  @Getter
  private int deadCount;
  private Profile profile;

  public AbstractDirector() {
    this.commandUtil = DependenciesRegistry.commandUtil;
    this.ocrUtil = DependenciesRegistry.ocrUtil;
  }

  @Override
  public void bindView(HomeView homeView) {
    this.homeView = homeView;
  }

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
      commandUtil.screenLog(gameStatus, new File("unknownStates"));
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
    tapScreen(new Point(400, 900));
    sleep(1000);
    tapScreen(new Point(400, 900));
  }

  protected void ackBattleResultFailure() {
    progressMessage("Battle fail!!! Not revive...");
    tapScreen(profile.getReviveNo());

    sleep(100);
    tapScreen(new Point(400, 900));
    sleep(100);
    replayBattle();
    deadCount++;
  }

  /**
   * Collect rune on battle result screen.
   */
  protected void collectRune(final GameStatus gameStatus) throws IOException {
    boolean pickRune = profile.isPickAllRunes();
    if (!profile.isPickAllRunes()) {
      pickRune = applyRuneFilter(gameStatus);
    }
    if (pickRune) {
      progressMessage("Collecting rune...");
      tapScreen(profile.getGetRuneLocation());
      if (profile.isRunLogging()) {
        commandUtil.screenLog(gameStatus, new File("runeLog"));
      }
    } else {
      // Rune will be sold if non of rules are matching
      sellRune(gameStatus);
    }
  }

  protected void collectStone(final GameStatus gameStatus) throws IOException {
    boolean pickRune = profile.isPickAllRunes();
    if (!pickRune) {
      pickRune = applyStoneFilter(gameStatus);
    }
    if (pickRune) {
      progressMessage("Collecting stone...");
      tapScreen(profile.getGetGemLocation());
      if (profile.isRunLogging()) {
        commandUtil.screenLog(gameStatus, new File("runeLog"));
      }
    } else {
      // Rune will be sold if non of rules are matching
      sellStone(gameStatus);
    }
  }

  protected void confirmSellRune() {
    progressMessage("Confirm to sell rune...");
    tapScreen(profile.getSellRuneConfirmation());

    sleep(100);
    replayBattle();
  }

  protected void confirmSellStone() {
    progressMessage("Confirm to sell stone...");
    tapScreen(profile.getSellStoneConfirmation());

    sleep(100);
    replayBattle();
  }

  /**
   * Enable auto attack mode by clicking on play icon (third button) at bottom left
   */
  protected void enableAutoAttackMode() {
    progressMessage("Enabling auto mode...");
    tapScreen(profile.getEnableAutoMode());
  }

  protected void notRefillCrys() {
    progressMessage("Not refill crys, wait for energy instead...");
    tapScreen(profile.getRechargeCrysNo());
    availableRefillTime = 0;
  }

  protected void proceedGemReward(final GameStatus gameStatus) {
    if (profile.isSellAllRunes()) {
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
    tapScreen(profile.getGetRewardLocation());
  }

  protected void proceedRuneReward(final GameStatus gameStatus) {
    if (profile.isSellAllRunes()) {
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
    homeView.updateStatus(String.format(message, args));
  }

  protected void refillEnergy() {
    progressMessage("Refilling energy...");
    // On screen of not enough energy, select YES on to recharges energy
    tapScreen(profile.getRechargeEnergyYes());
    sleep(500);
    // On shop screen, select energy
    tapScreen(profile.getRechargeEnergy());
    sleep(500);
    // On shop screen, confirm to purchase energy with 30 crystals
    tapScreen(profile.getConfirmRechargeEnergy());
    sleep(2000);
    // On shop screen, click OK confirm purchase successful
    tapScreen(profile.getAckRechargeEnergyOk());
    sleep(500);
    // Close the shop screen
    tapScreen(profile.getCloseRechargeEnergy());
  }

  protected void replayBattle() {
    progressMessage("Replaying battle...");
    tapScreen(profile.getReplayBattle());

    sleep(100);
    startBattle();
  }

  /**
   * Sell the rune on battle result screen.
   */
  protected void sellRune(final GameStatus gameStatus) {
    progressMessage("Selling rune...");
    tapScreen(profile.getSellRuneLocation());

    if (profile.isRunLogging()) {
      commandUtil.screenLog(gameStatus, new File("runeLog", "sold"));
    }
  }

  protected void sellStone(final GameStatus gameStatus) {
    progressMessage("Selling stone...");
    tapScreen(profile.getSellGemLocation());

    if (profile.isRunLogging()) {
      commandUtil.screenLog(gameStatus, new File("runeLog", "sold"));
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
    tapScreen(profile.getStartBattle());
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
    tapScreen(profile.getRechargeEnergyNo());

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
    if (profile.isPickLegendRunes() || profile.isPickHeroRunes()) {
      final String rareLevel = ocrUtil.text(new File(gameStatus.getScreenFile()), profile.getRareLevelBox());
      final boolean legend = rareLevel.equals("Legend");
      if (legend) {
        return true;
      }
      final boolean hero = rareLevel.equals("Hero");
      if (hero && profile.isPickHeroRunes()) {
        return true;
      }
    }
    if (profile.isPickSixStarRunes()) {
      final boolean sixStar = ImageUtil.contains(gameStatus.getScreenFile(),
          profile.getIndicatorFile(sixStarRuneIndicator).getAbsolutePath(), 98) != null;
      if (sixStar) {
        return true;
      }
    }
    if (profile.isPickFiveStarRunes()) {
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
    if (profile.isPickSpdPercentGrind()) {
      final String grindOptions = ocrUtil.text(new File(gameStatus.getScreenFile()), profile.getGrindstoneStatBox());
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
    tapScreen(profile.getConfirmNetworkDelay());
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
    tapScreen(profile.getResendBattleInfo());
  }

  private void tapScreen(Point point) {
    String tapX = String.valueOf(point.x);
    String tapY = String.valueOf(point.y);
    if (profile.isRandomClick()) {
      tapX = String.valueOf(point.x + (int) (10 * (Math.random() - Math.random())));
      tapY = String.valueOf(point.y + (int) (10 * (Math.random() - Math.random())));
    }
    commandUtil.tapScreen(tapX, tapY);
  }
}
