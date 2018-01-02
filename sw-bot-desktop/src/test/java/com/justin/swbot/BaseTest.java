package com.justin.swbot;

import com.justin.swbot.dependencies.DependenciesRegistry;

/**
 * Created by akivamu on 02/01/18.
 */
public abstract class BaseTest {
  public BaseTest() {
    DependenciesRegistry.settings = new PcSettings();
  }
}
