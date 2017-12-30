package com.justin.swbot.util;

import com.justin.swbot.dependencies.DependenciesRegistry;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PcRuneRecognizer implements RuneRecognizer {
  private final OcrUtil ocrUtil;

  public PcRuneRecognizer() {
    this.ocrUtil = DependenciesRegistry.ocrUtil;
  }

  @Override
  public String readRareLevel(String imagePath, Rectangle box) {
    try {
      final BufferedImage screenImage = ImageIO.read(new File(imagePath));
      return ocrUtil.text(new MemImage<>(screenImage), box);
    } catch (IOException e) {
      return "";
    }
  }

  @Override
  public String readGrindOptions(String imagePath, Rectangle box) {
    try {
      final BufferedImage screenImage = ImageIO.read(new File(imagePath));
      return ocrUtil.text(new MemImage<>(screenImage), box);
    } catch (IOException e) {
      return "";
    }
  }
}
