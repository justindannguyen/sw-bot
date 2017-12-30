/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import com.justin.swbot.game.GameStatus;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class PcCommandUtil implements CommandUtil {

  // TODO: temporary fix for portrait screen orientation
  private static final int quadrants = 1;

  @Override
  public String getHomeDirAbsPath() {
    return System.getProperty("user.dir");
  }

  @Override
  public String capturePhoneScreen() {
    final File screenshotFile =
            new File(System.getProperty("java.io.tmpdir"), "phoneScreenshot.png");
    runCmd("adb", "shell", "screencap", "-p", "/mnt/sdcard/output.png");
    runCmd("adb", "pull", "/mnt/sdcard/output.png", screenshotFile.getAbsolutePath());
    runCmd("adb", "shell", "rm", "/mnt/sdcard/output.png");

    if (quadrants != 0) {
      try {
        rotateImage(screenshotFile.getAbsolutePath(), quadrants);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return screenshotFile.exists() ? screenshotFile.getAbsolutePath() : null;
  }

  private void rotateImage(String path, int quadrants) throws IOException {
    BufferedImage image = ImageIO.read(new File(path));

    int w0 = image.getWidth();
    int h0 = image.getHeight();
    int w1 = w0;
    int h1 = h0;
    int centerX = w0 / 2;
    int centerY = h0 / 2;

    if (quadrants % 2 == 1) {
      w1 = h0;
      h1 = w0;
    }

    if (quadrants % 4 == 1) {
      centerX = h0 / 2;
      centerY = h0 / 2;
    } else if (quadrants % 4 == 3) {
      centerX = w0 / 2;
      centerY = w0 / 2;
    }

    AffineTransform affineTransform = new AffineTransform();
    affineTransform.setToQuadrantRotation(quadrants, centerX, centerY);
    AffineTransformOp opRotated = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
    BufferedImage transformedImage = new BufferedImage(w1, h1, image.getType());
    transformedImage = opRotated.filter(image, transformedImage);

    ImageIO.write(transformedImage, "PNG", new File(path));
  }

  private static boolean runCmd(final String... params) {
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

  @Override
  public void tapScreen(final String x, final String y) {
    runCmd("adb", "shell", "input", "tap", x, y);
  }

  @Override
  public void screenLog(GameStatus status, File folder) {
    if (!folder.exists()) {
      folder.mkdirs();
    }
    try {
      Files.copy(new File(status.getScreenFile()).toPath(),
              new File(folder, String.format("%s.png", System.currentTimeMillis())).toPath());
    } catch (final IOException ex) {
      System.err.println("Could not log screenshoot");
    }
  }
}
