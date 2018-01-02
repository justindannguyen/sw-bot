package com.justin.swbot;

import com.justin.swbot.game.indicator.IndicatorImageCache;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by akivamu on 02/01/18.
 */
public class PcIndicatorImageCache extends IndicatorImageCache<BufferedImage> {
  @Override
  public BufferedImage readImageFile(String path) {
    if (path == null || path.length() == 0) return null;

    File imageFile = new File(path);
    if (!imageFile.exists()) return null;

    try {
      return ImageIO.read(imageFile);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public void writeImageFile(String path, BufferedImage image) {
    if (image != null) {
      try {
        ImageIO.write(image, "png", new File(path));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
