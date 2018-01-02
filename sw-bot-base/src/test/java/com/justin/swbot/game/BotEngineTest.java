/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class BotEngineTest {

    @Test
    public void testGet_neverNull() {
        Assert.assertNotNull(BotEngine.get());
    }

    @Test
    public void testGet_singleton() {
        final BotEngine instance1 = BotEngine.get();
        final BotEngine instance2 = BotEngine.get();

        Assert.assertTrue(instance1 == instance2);
    }

    @Test
    public void testIsRunning() {
        final BotEngine instance = BotEngine.get();
        instance.setRunning(true);
        Assert.assertTrue(instance.isRunning());
    }
}
