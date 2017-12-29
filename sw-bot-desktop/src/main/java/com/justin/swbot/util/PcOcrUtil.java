/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class PcOcrUtil implements OcrUtil {
  public String text(final MemImage imageFile) {
    final ITesseract instance = new Tesseract(); // JNA Interface Mapping
    try {
      return instance.doOCR((BufferedImage) imageFile.get()).trim();
    } catch (final TesseractException e) {
      e.printStackTrace();
      return "";
    }
  }

  public String text(final File imageFile) {
    final ITesseract instance = new Tesseract(); // JNA Interface Mapping
    try {
      return instance.doOCR(imageFile).trim();
    } catch (final TesseractException e) {
      e.printStackTrace();
      return "";
    }
  }
}
