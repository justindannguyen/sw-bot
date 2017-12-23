/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import static org.bytedeco.javacpp.opencv_core.CV_32FC1;
import static org.bytedeco.javacpp.opencv_core.CV_8UC1;
import static org.bytedeco.javacpp.opencv_core.minMaxLoc;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.matchTemplate;

import java.awt.Rectangle;
import java.nio.DoubleBuffer;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_imgproc;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface ImageUtil {
  /**
   * Check if the source file contains the template file. In case of matching then the location and
   * size will be returned, otherwise <code>null</code> as mismatch.
   *
   * @param sourceFilePath full path of the source file which contain the game screen.
   * @param templateFilePath full path of the template file which contain a small part of game
   *        screen e.g. a text or unique identifier of game screen.
   * @param threshold percentage matching in order to consider as match e.g. 95% will be considered
   *        as match
   * @return the location in pixel and size of the template file in source file, <code>null</code>
   *         if not match in term of similarity and threshold
   */
  public static Rectangle contains(final String sourceFilePath, final String templateFilePath,
      final double threshold) {
    // read in image default colors
    final Mat sourceColor = imread(sourceFilePath);
    final Mat sourceGrey = new Mat(sourceColor.size(), CV_8UC1);
    cvtColor(sourceColor, sourceGrey, COLOR_BGR2GRAY);
    // load in template in grey
    final Mat templateGrey = imread(templateFilePath, CV_LOAD_IMAGE_GRAYSCALE);
    // Size for the result image
    final Size size = new Size(sourceGrey.cols() - templateGrey.cols() + 1,
        sourceGrey.rows() - templateGrey.rows() + 1);
    final Mat result = new Mat(size, CV_32FC1);
    matchTemplate(sourceGrey, templateGrey, result, opencv_imgproc.TM_CCORR_NORMED);

    final DoubleBuffer minVal = DoubleBuffer.allocate(8);
    final DoubleBuffer maxVal = DoubleBuffer.allocate(8);
    final Point minLoc = new Point();
    final Point maxLoc = new Point();
    minMaxLoc(result, minVal, maxVal, minLoc, maxLoc, null);

    final double similarity = maxVal.get() * 100;
    return similarity >= threshold
        ? new Rectangle(maxLoc.x(), maxLoc.y(), templateGrey.cols(), templateGrey.rows()) : null;
  }
}
