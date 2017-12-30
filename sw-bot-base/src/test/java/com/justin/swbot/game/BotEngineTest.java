/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import com.justin.swbot.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class BotEngineTest extends BaseTest {

    @Test
    public void testIsRunning() {
        final BotEngine instance = BotEngine.get();
        instance.setRunning(true);
        Assert.assertTrue(instance.isRunning());
    }
}
