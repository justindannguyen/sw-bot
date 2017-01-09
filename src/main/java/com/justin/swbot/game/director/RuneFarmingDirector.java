/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.game.GameState;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class RuneFarmingDirector implements ScenarioDirector {
    /**
     * Unique ID for the director, it also can be used as name identification.
     */
    public static final String ID = "Rune Farming";

    /* (non-Javadoc)
     * @see com.justin.swbot.game.ScenarioDirector#getName()
     */
    @Override
    public String getName() {
        return ID;
    }

    /* (non-Javadoc)
     * @see com.justin.swbot.game.ScenarioDirector#giveDirective(com.justin.swbot.game.GameState)
     */
    @Override
    public Runnable giveDirective(final GameState gameState) {
        if (gameState == null) {
            throw new IllegalArgumentException("Game state is null");
        }
        // TODO to be implemented
        return () -> System.out.println("nothing");
    }

}
