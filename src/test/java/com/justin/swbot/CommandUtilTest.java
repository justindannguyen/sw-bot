/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class CommandUtilTest {

  /**
   * Test method for {@link com.justin.swbot.CommandUtil#get()}.
   */
  @Test
  public void testGet() {
    Assert.assertNotNull(CommandUtil.get());
  }

  @Test
  public void testRunCmd_withInvalidCommand() {
    Assert.assertFalse(CommandUtil.get().runCmd("this-is-an-invalid-command"));
  }

  /**
   * Test method for {@link com.justin.swbot.CommandUtil#runCmd(java.lang.String[])}.
   *
   * @throws InterruptedException
   * @throws IOException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRunCmd_withInvalidParams() {
    CommandUtil.get().runCmd();
  }

  @Test
  public void testRunCmd_withLinuxPingCommand() {
    // This test only run on Linux
    Assume.assumeTrue(System.getProperty("os.name").toLowerCase().startsWith("linux"));
    Assert.assertTrue(CommandUtil.get().runCmd("ping", "8.8.8.8", "-c", "1"));
  }

  @Test
  public void testRunCmd_withWindowsPingCommand() {
    // This test only run on Windows
    Assume.assumeTrue(System.getProperty("os.name").toLowerCase().startsWith("win"));
    Assert.assertTrue(CommandUtil.get().runCmd("ping", "8.8.8.8", "-n", "1"));
  }
}
