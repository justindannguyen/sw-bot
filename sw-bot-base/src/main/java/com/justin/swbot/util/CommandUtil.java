/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import com.justin.swbot.game.GameStatus;

import java.io.File;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface CommandUtil {
    String getHomeDirAbsPath();

    String capturePhoneScreen();

    void tapScreen(final String x, final String y);

    void screenLog(final GameStatus status, final File folder);
}
