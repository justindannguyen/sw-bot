/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class ImagePickerDialog extends BoxPickerDialog {
  private static final long serialVersionUID = 1L;

  private Point startPoint;

  private Point endPoint;
  public ImagePickerDialog() {
    super();

    setTitle("Image Picker - drag to select rectangle");
  }

  public Image pickImage() {
    final Rectangle box = pickBox();

    return box == null || screenImage == null ? null
        : screenImage.getSubimage(box.x, box.y, box.width, box.height);
  }

  @Override
  protected void customPaint(final Graphics g) {
    super.customPaint(g);
    if (startPoint != null && endPoint != null) {
      final Graphics g2 = g.create();
      final int x = Math.min(startPoint.x, endPoint.x);
      final int y = Math.min(startPoint.y, endPoint.y);
      final int width = Math.abs(startPoint.x - endPoint.x);
      final int height = Math.abs(startPoint.y - endPoint.y);
      g2.setColor(Color.GREEN);
      g2.drawRect(x, y, width, height);
      g2.dispose();
    }
  }
}
