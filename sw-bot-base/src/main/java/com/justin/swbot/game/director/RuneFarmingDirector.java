/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.game.analyzer.Analyzer;
import com.justin.swbot.game.analyzer.DungeonAnalyzer;
import com.justin.swbot.game.profile.Profile;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class RuneFarmingDirector extends GenericDirector {
  public RuneFarmingDirector(Listener listener, Profile profile) {
    super(listener, profile);
  }

  @Override
  protected Analyzer getDefaultAnalyzer() {
    return new DungeonAnalyzer();
  }
}
