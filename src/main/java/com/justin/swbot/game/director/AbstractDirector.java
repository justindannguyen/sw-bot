/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.CommandUtil;
import com.justin.swbot.game.ControllerRegistry;
import com.justin.swbot.game.GameConfig;
import com.justin.swbot.game.GameState;
import com.justin.swbot.home.HomeController;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class AbstractDirector implements ScenarioDirector {
  private int availableRefillTime;

  @Override
  public void direct(final GameState gameState) {
    if (gameState == GameState.BATTLE_MANUAL) {
      enableAutoAttackMode();
    } else if (gameState == GameState.BATTLE_ENDED) {
      ackBattleResult();
    } else if (gameState == GameState.RUNE_REWARD) {
      proceedRuneReward();
    } else if (gameState == GameState.OTHER_REWARD) {
      proceedOtherReward();
    } else if (gameState == GameState.REPLAY_BATTLE_CONFIRMATION) {
      replayBattle();
    } else if (gameState == GameState.START_BATTLE) {
      startBattle();
    } else if (gameState == GameState.SELL_RUNE_CONFIRMATION) {
      confirmSellRune();
    } else if (gameState == GameState.NOT_ENOUGH_ENERGY) {
      proceedNotEnoughEnergy();
    } else if (gameState == GameState.NETWORK_DELAY) {
      confirmNetworkDelay();
    } else if (gameState == GameState.UNSTABLE_NETWORK) {
      resendBattleInfo();
    }
  }

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  /**
   * Acknowledge the battle result by click somewhere on the screen
   */
  protected void ackBattleResult() {
    progressMessage("Ending battle...");
    tapScreen("1", "1");
    sleep(1000);
    tapScreen("1", "1");
  }

  /**
   * Collect rune on battle result screen.
   */
  protected void collectRune() {
    progressMessage("Collecting rune...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getGetRuneLocationX(), gameConfig.getGetRuneLocationY());
  }

  protected void confirmSellRune() {
    progressMessage("Confirm to sell rune...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getSellRuneConfirmationX(), gameConfig.getSellRuneConfirmationY());

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

  protected void proceedOtherReward() {
    progressMessage("Collecting rewards...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getGetRewardLocationX(), gameConfig.getGetRewardLocationY());
  }

  protected void proceedRuneReward() {
    final GameConfig gameConfig = GameConfig.get();
    if (gameConfig.isSellAllRune()) {
      sellRune();
    } else {
      collectRune();
    }
  }

  protected void progressMessage(final String message) {
    final HomeController homeController =
        (HomeController) ControllerRegistry.get(HomeController.class);
    homeController.updateStatus(message);
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

  /**
   * Sell the rune on battle result screen.
   */
  protected void sellRune() {
    progressMessage("Selling rune...");
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getSellRuneLocationX(), gameConfig.getSellRuneLocationY());
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
