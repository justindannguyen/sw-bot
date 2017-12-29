package com.justin.swbot;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.util.PcCommandUtil;
import com.justin.swbot.util.PcMemImageUtil;
import com.justin.swbot.util.PcOcrUtil;

/**
 * Created by akivamu on 28/12/17.
 */
public abstract class BaseTest {
  public BaseTest() {
    DependenciesRegistry.commandUtil = new PcCommandUtil();
    DependenciesRegistry.memImageUtil = new PcMemImageUtil();
    DependenciesRegistry.ocrUtil = new PcOcrUtil();
  }
}
