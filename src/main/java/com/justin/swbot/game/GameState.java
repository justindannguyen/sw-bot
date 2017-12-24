/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

/**
 * <p>
 * The enum represent the game state which normally is a screen identification, or the network status e.g. if we are at
 * the victory or poor network at the end of battle, or energy need to refill, etc.
 *
 * <p>
 * The respective bot engine is required to identify the game state base on the configuration.
 *
 * @author tuan3.nguyen@gmail.com
 * @see BotEngine
 */
public enum GameState {
  /**
   * Unable to identify the game state.
   */
  UNKNOWN,
  /**
   * Game battle are in manual mode.
   */
  BATTLE_MANUAL,
  /**
   * Battle end with successful.
   */
  BATTLE_ENDED,
  /**
   * Rune & rune enchant materials are considered in this category. Ideally, it will have get or
   * sell actions.
   */
  RUNE_REWARD,
  /**
   * Other rewards which only have 1 OK (get) action.
   */
  OTHER_REWARD,
  /**
   * Yes or no to replay new battle.
   */
  REPLAY_BATTLE_CONFIRMATION,
  /**
   * Start new battle.
   */
  START_BATTLE,
  /**
   * Sell rune yes, no confirmation with 5* or +9
   */
  SELL_RUNE_CONFIRMATION,
  /**
   * Not enough energy
   */
  NOT_ENOUGH_ENERGY,
  /**
   * Network delay after the battle.
   */
  NETWORK_DELAY,
  /**
   * Unstable network when start new battle.
   */
  UNSTABLE_NETWORK;
}
