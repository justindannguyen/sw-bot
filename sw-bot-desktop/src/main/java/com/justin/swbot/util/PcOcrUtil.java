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
  @Override
  public String text(final MemImage imageFile, Rectangle box) {
    final ITesseract instance = new Tesseract(); // JNA Interface Mapping
    String text = "";
    try {
      if (box == null) {
        text = instance.doOCR((BufferedImage) imageFile.get());
      } else {
        text = instance.doOCR((BufferedImage) imageFile.get(), new java.awt.Rectangle(box.x, box.y, box.width, box.height));
      }
    } catch (final TesseractException e) {
      e.printStackTrace();
    }
    return text.trim();
  }

  @Override
  public String text(final File imageFile, Rectangle box) {
    final ITesseract instance = new Tesseract(); // JNA Interface Mapping
    String text = "";
    try {
      if (box == null) {
        text = instance.doOCR(imageFile);
      } else {
        text = instance.doOCR(imageFile, new java.awt.Rectangle(box.x, box.y, box.width, box.height));
      }
    } catch (final TesseractException e) {
      e.printStackTrace();
    }
    return text.trim();
  }
}
