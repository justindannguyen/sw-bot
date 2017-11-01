/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import java.io.IOException;

import org.junit.Assert;
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
  public void testRunCmd_withWindowsPingCommand() {
    Assert.assertTrue(CommandUtil.get().runCmd("ping", "8.8.8.8", "-n", "1"));
  }
}
