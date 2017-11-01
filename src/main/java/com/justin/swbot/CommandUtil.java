/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class CommandUtil {
  private static CommandUtil instance = new CommandUtil();

  public static CommandUtil get() {
    return instance;
  }

  private CommandUtil() {
    // Hidden constructor
  }

  public boolean runCmd(final String... params) {
    if (params == null || params.length == 0) {
      throw new IllegalArgumentException("command is null or empty");
    }
    final ProcessBuilder pb = new ProcessBuilder(Arrays.asList(params));
    try {
      final Process process = pb.start();
      return process.waitFor() == 0;
    } catch (IOException | InterruptedException ex) {
      return false;
    }
  }
}
