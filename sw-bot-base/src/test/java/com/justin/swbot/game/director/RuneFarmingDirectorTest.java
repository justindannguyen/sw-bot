/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class RuneFarmingDirectorTest {
  private RuneFarmingDirector instanceUnderTest;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    instanceUnderTest = new RuneFarmingDirector();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    instanceUnderTest = null;
  }

  /**
   * Test method for {@link com.justin.swbot.game.director.RuneFarmingDirector#getName()}.
   */
  @Test
  public void testGetName() {
    Assert.assertEquals("RuneFarmingDirector", instanceUnderTest.getName());
  }
}
