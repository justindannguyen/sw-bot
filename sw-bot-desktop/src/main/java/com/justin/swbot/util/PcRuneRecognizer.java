package com.justin.swbot.util;

import com.justin.swbot.dependencies.DependenciesRegistry;

import java.io.File;

public class PcRuneRecognizer implements RuneRecognizer {
  private final OcrUtil ocrUtil;

  public PcRuneRecognizer() {
    this.ocrUtil = DependenciesRegistry.ocrUtil;
  }

  @Override
  public String readRareLevel(String imagePath, Rectangle box) {
    return ocrUtil.text(new File(imagePath), box);
  }

  @Override
  public String readGrindOptions(String imagePath, Rectangle box) {
    return ocrUtil.text(new File(imagePath), box);
  }
}
