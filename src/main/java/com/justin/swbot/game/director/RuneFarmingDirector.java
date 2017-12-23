/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.game.GameState;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class RuneFarmingDirector extends AbstractDirector {
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
