/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import static com.justin.swbot.CommandUtil.tapScreen;

import com.justin.swbot.game.GameConfig;
import com.justin.swbot.game.GameState;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class AbstractDirector implements ScenarioDirector {
  private int availableRefillTime;

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  @Override
  public Runnable giveDirective(final GameState gameState) {
    if (gameState == GameState.BATTLE_MANUAL) {
      enableAutoAttackMode();
    } else if (gameState == GameState.BATTLE_RESULT_WIN) {
      ackBattleResult();
    } else if (gameState == GameState.BATTLE_RESULT_FAIL) {
      ackBattleResult();
    } else if (gameState == GameState.RUNE_REWARD) {
      ackBattleResult();
    } else if (gameState == GameState.OTHER_REWARD) {
      ackBattleResult();
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
    return null;
  }

  /**
   * Acknowledge the battle result by click somewhere on the screen
   */
  protected void ackBattleResult() {
    tapScreen("1", "1");
    sleep(500);
    tapScreen("1", "1");
  }

  /**
   * Collect rune on battle result screen.
   */
  protected void collectRune() {
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getGetRuneLocationX(), gameConfig.getGetRuneLocationY());
  }

  protected void confirmSellRune() {
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getSellRuneConfirmationX(), gameConfig.getSellRuneConfirmationY());

    sleep(100);
    replayBattle();
  }

  /**
   * Enable auto attack mode by clicking on play icon (third button) at bottom left
   */
  protected void enableAutoAttackMode() {
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getEnableAutoModeX(), gameConfig.getEnableAutoModeY());
  }

  protected void proceedOtherReward() {
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getGetRewardLocationX(), gameConfig.getGetRewardLocationY());
  }

  protected void proceedRuneReward() {
    // Default options will be sell the rune.
    sellRune();
  }

  protected void refillEnergy() {
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
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getReplayBattleX(), gameConfig.getReplayBattleY());

    sleep(100);
    startBattle();
  }

  /**
   * Sell the rune on battle result screen.
   */
  protected void sellRune() {
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
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getStartBattleX(), gameConfig.getStartBattleY());
  }

  protected void waitForEnergy() {
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getRechargeEnergyNoX(), gameConfig.getRechargeEnergyNoY());

    // Wait for 10 minutes
    sleep(10 * 60 * 1000);
  }

  private void confirmNetworkDelay() {
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
    final GameConfig gameConfig = GameConfig.get();
    tapScreen(gameConfig.getResendBattleInfoX(), gameConfig.getResendBattleInfoY());
  }
}
