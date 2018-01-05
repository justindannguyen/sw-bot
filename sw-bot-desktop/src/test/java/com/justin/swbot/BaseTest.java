package com.justin.swbot;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.profile.ProfileManager;
import com.justin.swbot.util.PcCommandUtil;
import com.justin.swbot.util.PcOcrUtil;

/**
 * Created by akivamu on 02/01/18.
 */
public abstract class BaseTest {
  public BaseTest() {
    DependenciesRegistry.settings = new PcSettings();
    DependenciesRegistry.commandUtil = new PcCommandUtil();
    DependenciesRegistry.ocrUtil = new PcOcrUtil();
    DependenciesRegistry.profileManager = new ProfileManager();
    DependenciesRegistry.profileManager.setLocation(DependenciesRegistry.settings.getProfilesFolderPath());
  }
}
