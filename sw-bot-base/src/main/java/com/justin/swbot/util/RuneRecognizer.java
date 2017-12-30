package com.justin.swbot.util;

public interface RuneRecognizer {
  String readRareLevel(String imagePath, Rectangle box);

  String readGrindOptions(String imagePath, Rectangle box);
}
