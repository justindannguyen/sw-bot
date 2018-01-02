package com.justin.swbot;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.util.PcCommandUtil;

/**
 * Created by akivamu on 02/01/18.
 */
public abstract class BaseTest {
  public BaseTest() {
    DependenciesRegistry.settings = new PcSettings();
    DependenciesRegistry.commandUtil = new PcCommandUtil();
  }
}
