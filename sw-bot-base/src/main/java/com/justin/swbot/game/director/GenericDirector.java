/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.game.GameState;
import com.justin.swbot.game.GameStatus;
import com.justin.swbot.game.profile.Profile;
import com.justin.swbot.util.ImageUtil;
import com.justin.swbot.util.Point;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

import static com.justin.swbot.game.indicator.Indicator.fiveStarRuneIndicator;
import static com.justin.swbot.game.indicator.Indicator.sixStarRuneIndicator;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class GenericDirector extends Director {
  @Getter
  private int availableRefillTime;
  @Getter
  private int availableRuns;

  public GenericDirector(Listener listener, Profile profile) {
    super(listener, profile);

    availableRefillTime = profile.getRefillTimes();
    availableRuns = profile.getMaxRuns();
  }

  @Override
  public boolean direct() {
    final GameState gameState = currentGameStatus.getGameState();
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
      proceedRuneReward();
      return true;
    } else if (gameState == GameState.GEM_REWARD) {
      proceedGemReward();
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
      commandUtil.screenLog(currentGameStatus, new File("unknownStates"));
      sleep(10000);
      return true;
    }
    return false;
  }

  /**
   * Acknowledge the battle result by click somewhere on the screen
   */
  protected void ackBattleResult() {
    progressMessage("Ending battle...");
    tapScreen(new Point(400, 900));
    sleep(1000);
    tapScreen(new Point(400, 900));

    availableRuns--;
  }

  protected void ackBattleResultFailure() {
    progressMessage("Battle fail!!! Not revive...");
    tapScreen(profile.getReviveNo());

    sleep(100);
    tapScreen(new Point(400, 900));

    availableRuns--;
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
  }

  protected void confirmSellStone() {
    progressMessage("Confirm to sell stone...");
    tapScreen(profile.getSellStoneConfirmation());
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

  protected void proceedGemReward() {
    if (profile.isSellAllRunes()) {
      sellStone(currentGameStatus);
    } else {
      try {
        collectStone(currentGameStatus);
      } catch (final IOException ex) {
        throw new RuntimeException("Error when collect stone", ex);
      }
    }
  }

  protected void proceedOtherReward() {
    progressMessage("Collecting rewards...");
    tapScreen(profile.getGetRewardLocation());
  }

  protected void proceedRuneReward() {
    if (profile.isSellAllRunes()) {
      sellRune(currentGameStatus);
    } else {
      try {
        collectRune(currentGameStatus);
      } catch (final IOException ex) {
        throw new RuntimeException("Error when collect rune", ex);
      }
    }
  }

  protected void progressMessage(final String message, final Object... args) {
  }

  protected void refillEnergy() {
    listener.onTryingToRefillEnergy();
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
    if (availableRuns <= 0) {
      listener.onNoMoreRun();
    }

    progressMessage("Replaying battle...");
    tapScreen(profile.getReplayBattle());
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
    sleep(5000);
  }

  protected void wait4Battle() {
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
