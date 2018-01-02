package com.justin.swbot;

import com.justin.swbot.game.indicator.IndicatorImageCache;

/**
 * Created by akivamu on 02/01/18.
 */
public class PcSettings extends Settings {
  @Override
  protected String getHomeFolderPath() {
    return "./";
  }

  @Override
  public IndicatorImageCache newIndicatorImageCacheInstance() {
    return new PcIndicatorImageCache();
  }
}