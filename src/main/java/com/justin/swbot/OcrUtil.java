/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import java.awt.image.BufferedImage;
import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface OcrUtil {
  public static String text(final BufferedImage imageFile) {
    final ITesseract instance = new Tesseract(); // JNA Interface Mapping
    try {
      return instance.doOCR(imageFile);
    } catch (final TesseractException e) {
      e.printStackTrace();
      return "";
    }
  }

  public static String text(final File imageFile) {
    final ITesseract instance = new Tesseract(); // JNA Interface Mapping
    try {
      return instance.doOCR(imageFile);
    } catch (final TesseractException e) {
      e.printStackTrace();
      return "";
    }
  }
}
