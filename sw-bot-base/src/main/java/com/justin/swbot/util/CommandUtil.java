/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface CommandUtil {
  String capturePhoneScreen();

  boolean runCmd(final String... params);

  void tapScreen(final String x, final String y);
}
