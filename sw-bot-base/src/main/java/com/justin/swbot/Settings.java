package com.justin.swbot;

import com.justin.swbot.game.indicator.IndicatorImageCache;

/**
 * Created by akivamu on 02/01/18.
 */
public abstract class Settings {
  public static final String PROFILES_DIR_NAME = "profiles";

  protected abstract String getHomeFolderPath();

  public String getProfilesFolderPath() {
    return getHomeFolderPath() + "/" + PROFILES_DIR_NAME;
  }

  public abstract IndicatorImageCache newIndicatorImageCacheInstance();
}
