/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class OcrUtilTest {

  @Before
  public void setup() {
    // Ocr has native library and support only windows
    Assume.assumeTrue(System.getProperty("os.name").toLowerCase().startsWith("win"));
  }

  /**
   * Test method for {@link com.justin.swbot.OcrUtil#text(java.awt.image.BufferedImage)}.
   */
  @Test
  public void testTextBufferedImage() {
    final String sourceFile = "src/test/resources/hero.png";
    Assert.assertEquals("Hero", OcrUtil.text(new File(sourceFile)).trim());
  }

  /**
   * Test method for {@link com.justin.swbot.OcrUtil#text(java.io.File)}.
   */
  @Test
  public void testTextFile() throws IOException {
    final String sourceFile = "src/test/resources/hero.png";
    Assert.assertEquals("Hero", OcrUtil.text(ImageIO.read(new File(sourceFile))).trim());
  }

}
