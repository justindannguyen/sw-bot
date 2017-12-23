/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.game.GameState;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class RiftDungeonDirector extends AbstractDirector {

  @Override
  public Runnable giveDirective(final GameState gameState) {
    final Runnable directive = super.giveDirective(gameState);
    if (directive != null) {
      return directive;
    }
    return null;
  }
}
