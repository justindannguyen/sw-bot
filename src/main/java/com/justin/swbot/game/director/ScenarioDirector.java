/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.game.BotEngine;
import com.justin.swbot.game.GameState;

import java.util.ServiceLoader;

/**
 * <p>
 * The game will contain multiple scenario e.g. rune farming, level farming, material dungeon farming, etc and director
 * is required for each scenario to provide what to do, where to click.
 *
 * <p>
 * The director requires the game state as input so that it can give the correct command to the bot engine e.g. if we
 * are farming rune and game state is at the end of battle, director should give command to keep all item.
 *
 * <p>
 * The director alone can't decide everything and it may need help from the data secretary in order to have phone
 * profile, location and screen image.
 *
 * <p>
 * The director will be registered in bot engine by using the services, refer {@link ServiceLoader}. Therefore, if you
 * want to auto new scenario, following steps is a must:
 * <ul>
 * <li>Create new director class which implement this interface.</li>
 * <li>Register new director into the <strong>META-INF/services/</strong>
 * <li>Implement the give directive logic</li>
 * </ul>
 *
 * @author tuan3.nguyen@gmail.com
 * @see BotEngine
 */
public interface ScenarioDirector {
    /**
     * Get the unique name of the director.
     *
     * @return unique identification name.
     */
    String getName();

    /**
     * Base on the give game state, the director give suggestion what to do, where to click as a {@link Runnable}.
     *
     * @param gameState
     *            current game state.
     * @return command to run at this game state.
     */
    Runnable giveDirective(GameState gameState);
}
