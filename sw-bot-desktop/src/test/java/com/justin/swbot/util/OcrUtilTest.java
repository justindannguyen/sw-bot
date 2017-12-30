/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import com.justin.swbot.BaseTest;
import com.justin.swbot.dependencies.DependenciesRegistry;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class OcrUtilTest extends BaseTest {

  private OcrUtil ocrUtil;

  @Before
  public void setup() {
    ocrUtil = DependenciesRegistry.ocrUtil;

    // Ocr has native library and support only windows
    Assume.assumeTrue(System.getProperty("os.name").toLowerCase().startsWith("win"));
  }

  /**
   * Test method for {@link com.justin.swbot.util.OcrUtil#text(MemImage, Rectangle)}.
   */
  @Test
  public void testTextBufferedImage() {
    final String sourceFile = "src/test/resources/hero.png";
    Assert.assertEquals("Hero", ocrUtil.text(new File(sourceFile), null));
  }

  /**
   * Test method for {@link com.justin.swbot.util.OcrUtil#text(java.io.File, Rectangle)}.
   */
  @Test
  public void testTextFile() throws IOException {
    final String sourceFile = "src/test/resources/hero.png";
    Assert.assertEquals("Hero", ocrUtil.text(new MemImage<>(ImageIO.read(new File(sourceFile))), null));
  }

  @Test
  public void testTextFile_withPercent() throws IOException {
    final String sourceFile = "src/test/resources/percent.png";
    Assert.assertTrue(ocrUtil.text(new MemImage<>(ImageIO.read(new File(sourceFile))), null).contains("°/o"));
  }
}
