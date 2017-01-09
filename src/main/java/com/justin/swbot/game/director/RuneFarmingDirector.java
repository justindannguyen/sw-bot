/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.game.GameState;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class RuneFarmingDirector implements ScenarioDirector {

    /* (non-Javadoc)
     * @see com.justin.swbot.game.ScenarioDirector#getName()
     */
    @Override
    public String getName() {
        return "Rune Farming";
    }

    /* (non-Javadoc)
     * @see com.justin.swbot.game.ScenarioDirector#giveDirective(com.justin.swbot.game.GameState)
     */
    @Override
    public Runnable giveDirective(final GameState gameState) {
        return () -> System.out.println("nothing");
    }

}
