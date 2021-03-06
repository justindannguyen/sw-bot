/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface CommandUtil {
  public static String capturePhoneScreen() {
    final File screenshotFile =
        new File(System.getProperty("java.io.tmpdir"), "phoneScreenshot.png");
    runCmd("adb", "shell", "screencap", "-p", "/mnt/sdcard/output.png");
    runCmd("adb", "pull", "/mnt/sdcard/output.png", screenshotFile.getAbsolutePath());
    runCmd("adb", "shell", "rm", "/mnt/sdcard/output.png");
    return screenshotFile.exists() ? screenshotFile.getAbsolutePath() : null;
  }

  public static boolean runCmd(final String... params) {
    if (params == null || params.length == 0) {
      throw new IllegalArgumentException("command is null or empty");
    }
    final ProcessBuilder pb = new ProcessBuilder(Arrays.asList(params));
    try {
      final Process process = pb.redirectErrorStream(true).start();
      final BufferedReader reader =
          new BufferedReader(new InputStreamReader(process.getInputStream()));
      String consoleLine;
      while ((consoleLine = reader.readLine()) != null) {
        System.out.println(consoleLine);
        // Just make buffer empty to prevent process from endless execution, especially on platform
        // that limited buffer size for standard input and output streams.
      }
      return process.waitFor() == 0;
    } catch (IOException | InterruptedException ex) {
      return false;
    }
  }

  public static void tapScreen(final String x, final String y) {
    runCmd("adb", "shell", "input", "tap", x, y);
  }
}
