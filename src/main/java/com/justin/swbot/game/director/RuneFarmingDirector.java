/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class RuneFarmingDirector extends AbstractDirector {
  @Override
  protected void startBattle() {
    super.startBattle();
    sleep(60000);
  }
}
