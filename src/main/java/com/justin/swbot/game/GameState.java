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
}
