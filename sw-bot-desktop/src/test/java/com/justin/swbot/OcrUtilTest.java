/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import java.io.File;
import java.io.IOException;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.util.OcrUtil;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

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
   * Test method for {@link OcrUtil#text(File)}.
   */
  @Test
  public void testTextBufferedImage() {
    final String sourceFile = "src/test/resources/hero.png";
    Assert.assertEquals("Hero", ocrUtil.text(new File(sourceFile)));
  }

  @Test
  public void testTextFile_withPercent() throws IOException {
    final String sourceFile = "src/test/resources/percent.png";
    Assert.assertTrue(ocrUtil.text(new File(sourceFile)).contains("°/o"));
  }
}
