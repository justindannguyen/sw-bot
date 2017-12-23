/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class GameConfig {
  public static final String SELL_RUNE_LOC_X = "sellRuneLocationX";
  public static final String SELL_RUNE_LOC_Y = "sellRuneLocationY";
  public static final String GET_RUNE_LOC_X = "getRuneLocationX";
  public static final String GET_RUNE_LOC_Y = "getRuneLocationY";
  public static final String GET_REWARD_LOC_X = "getRewardLocationX";
  public static final String GET_REWARD_LOC_Y = "getRewardLocationY";
  public static final String ENABLE_AUTO_MODE_X = "enableAutoModeX";
  public static final String ENABLE_AUTO_MODE_Y = "enableAutoModeY";
  public static final String REPLAY_BATTLE_X = "replayBattleX";
  public static final String REPLAY_BATTLE_Y = "replayBattleY";
  public static final String START_BATTLE_X = "startBattleX";
  public static final String START_BATTLE_Y = "startBattleY";
  public static final String SELL_RUNE_CONFIRM_X = "sellRuneConfirmationX";
  public static final String SELL_RUNE_CONFIRM_Y = "sellRuneConfirmationY";
  public static final String RECHARGE_ENGERGY_YES_X = "rechargeEnergyYesX";
  public static final String RECHARGE_ENGERGY_YES_Y = "rechargeEnergyYesY";
  public static final String RECHARGE_ENGERGY_NO_X = "rechargeEnergyNoX";
  public static final String RECHARGE_ENGERGY_NO_Y = "rechargeEnergyNoY";
  public static final String RECHARGE_ENERGY_X = "rechargeEnergyX";
  public static final String RECHARGE_ENERGY_Y = "rechargeEnergyY";
  public static final String CONFIRM_RECHARGE_ENERGY_X = "confirmRechargeEnergyX";
  public static final String CONFIRM_RECHARGE_ENERGY_Y = "confirmRechargeEnergyY";
  public static final String ACK_RECHARGE_ENERGY_OK_X = "ackRechargeEnergyOkX";
  public static final String ACK_RECHARGE_ENERGY_OK_Y = "ackRechargeEnergyOkY";
  public static final String CLOSE_RECHARGE_ENERGY_X = "closeRechargeEnergyX";
  public static final String CLOSE_RECHARGE_ENERGY_Y = "closeRechargeEnergyY";
  public static final String REFILL_TIMES = "refillTimes";
  public static final String CONFIRM_NETWORK_DELAY_X = "confirmNetworkDelayX";
  public static final String CONFIRM_NETWORK_DELAY_Y = "confirmNetworkDelayY";
  public static final String RESEND_BATTLE_INFO_X = "resendBattleInfoX";
  public static final String RESEND_BATTLE_INFO_Y = "resendBattleInfoY";

  private static final GameConfig INSTANCE = new GameConfig();

  public static GameConfig get() {
    return INSTANCE;
  }

  private final Properties props = new Properties();

  public String getAckRechargeEnergyOkX() {
    return props.getProperty(ACK_RECHARGE_ENERGY_OK_X);
  }

  public String getAckRechargeEnergyOkY() {
    return props.getProperty(ACK_RECHARGE_ENERGY_OK_Y);
  }

  public String getCloseRechargeEnergyX() {
    return props.getProperty(CLOSE_RECHARGE_ENERGY_X);
  }

  public String getCloseRechargeEnergyY() {
    return props.getProperty(CLOSE_RECHARGE_ENERGY_Y);
  }

  public String getConfirmNetworkDelayX() {
    return props.getProperty(CONFIRM_NETWORK_DELAY_X);
  }

  public String getConfirmNetworkDelayY() {
    return props.getProperty(CONFIRM_NETWORK_DELAY_Y);
  }

  public String getConfirmRechargeEnergyX() {
    return props.getProperty(CONFIRM_RECHARGE_ENERGY_X);
  }

  public String getConfirmRechargeEnergyY() {
    return props.getProperty(CONFIRM_RECHARGE_ENERGY_Y);
  }

  public String getEnableAutoModeX() {
    return props.getProperty(ENABLE_AUTO_MODE_X);
  }

  public String getEnableAutoModeY() {
    return props.getProperty(ENABLE_AUTO_MODE_Y);
  }

  public String getGetRewardLocationX() {
    return props.getProperty(GET_REWARD_LOC_X);
  }

  public String getGetRewardLocationY() {
    return props.getProperty(GET_REWARD_LOC_Y);
  }

  public String getGetRuneLocationX() {
    return props.getProperty(GET_RUNE_LOC_X);
  }

  public String getGetRuneLocationY() {
    return props.getProperty(GET_RUNE_LOC_Y);
  }

  public String getRechargeEnergyNoX() {
    return props.getProperty(RECHARGE_ENGERGY_NO_X);
  }

  public String getRechargeEnergyNoY() {
    return props.getProperty(RECHARGE_ENGERGY_NO_Y);
  }

  public String getRechargeEnergyX() {
    return props.getProperty(RECHARGE_ENERGY_X);
  }

  public String getRechargeEnergyY() {
    return props.getProperty(RECHARGE_ENERGY_Y);
  }

  public String getRechargeEnergyYesX() {
    return props.getProperty(RECHARGE_ENGERGY_YES_X);
  }

  public String getRechargeEnergyYesY() {
    return props.getProperty(RECHARGE_ENGERGY_YES_Y);
  }

  public String getRefillTimes() {
    return props.getProperty(REFILL_TIMES);
  }

  public String getReplayBattleX() {
    return props.getProperty(REPLAY_BATTLE_X);
  }

  public String getReplayBattleY() {
    return props.getProperty(REPLAY_BATTLE_Y);
  }

  public String getResendBattleInfoX() {
    return props.getProperty(RESEND_BATTLE_INFO_X);
  }

  public String getResendBattleInfoY() {
    return props.getProperty(RESEND_BATTLE_INFO_Y);
  }

  public String getSellRuneConfirmationX() {
    return props.getProperty(SELL_RUNE_CONFIRM_X);
  }

  public String getSellRuneConfirmationY() {
    return props.getProperty(SELL_RUNE_CONFIRM_Y);
  }

  public String getSellRuneLocationX() {
    return props.getProperty(SELL_RUNE_LOC_X);
  }

  public String getSellRuneLocationY() {
    return props.getProperty(SELL_RUNE_LOC_Y);
  }

  public String getStartBattleX() {
    return props.getProperty(START_BATTLE_X);
  }

  public String getStartBattleY() {
    return props.getProperty(START_BATTLE_Y);
  }

  public void load(final String file) {
    try {
      props.load(new FileInputStream(file));
    } catch (final IOException ex) {
      System.err.println("Can't load the game configuration");
      ex.printStackTrace();
    }
  }
}
