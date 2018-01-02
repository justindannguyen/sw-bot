package com.justin.swbot.game.indicator;

import java.util.HashMap;
import java.util.Map;

public abstract class IndicatorImageCache<ImageType> {
  private Map<Indicator, ImageType> indicators = new HashMap<Indicator, ImageType>();

  public void loadAllIndicators(String folderPath) {
    if (folderPath == null) {
      return;
    }

    for (Indicator indicator : Indicator.values()) {
      ImageType image = readImageFile(folderPath + "/" + indicator.name());
      indicators.put(indicator, image);
    }
  }

  public void saveAllIndicators(String folderPath) {
    if (folderPath == null) throw new IllegalStateException("Must specify indicator path");

    for (Indicator indicator : Indicator.values()) {
      ImageType image = get(indicator);
      if (image != null) {
        writeImageFile(folderPath + "/" + indicator.name(), image);
      }
    }
  }

  public abstract ImageType readImageFile(String path);

  public abstract void writeImageFile(String path, ImageType image);

  public ImageType get(Indicator indicator) {
    return indicators.get(indicator);
  }

  public ImageType put(Indicator indicator, ImageType image) {
    return indicators.put(indicator, image);
  }
}
