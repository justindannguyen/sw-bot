/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

import static com.justin.swbot.util.PcConverter.toAwtRectangle;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class PcOcrUtil implements OcrUtil {
  @Override
  public String text(final File imageFile) {
    return text(imageFile, null);
  }

  @Override
  public String text(File imageFile, Rectangle box) {
    final ITesseract instance = new Tesseract(); // JNA Interface Mapping
    String text = "";
    try {
      if (box != null) text = instance.doOCR(imageFile, toAwtRectangle(box));
      else text = instance.doOCR(imageFile);
    } catch (final TesseractException e) {
      e.printStackTrace();
    }
    return text.trim();
  }
}
