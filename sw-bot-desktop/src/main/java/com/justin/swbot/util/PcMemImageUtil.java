package com.justin.swbot.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PcMemImageUtil implements MemImageUtil {
  @Override
  public MemImage loadImage(File imageFile) throws IOException {
    if (imageFile != null && imageFile.exists()) {
      return new MemImage<>(ImageIO.read(imageFile));
    }
    return null;
  }

  @Override
  public void storeImage(MemImage image, File location) throws IOException {
    if (image != null && image.get() != null) {
      ImageIO.write((BufferedImage) image.get(), "png", location);
    }
  }
}
