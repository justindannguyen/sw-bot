/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class PointPickerDialog extends AbstractPickerDialog {
  private static final long serialVersionUID = 1L;

  private Point point;

  public PointPickerDialog() {
    super();
    setTitle("Box Picker - click to select point");
    getMainLabel().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(final MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
          refreshScreenshot();
          e.consume();
        } else if (SwingUtilities.isLeftMouseButton(e)) {
          point = e.getPoint();
          e.consume();
          dispose();
        }
      }
    });

  }

  public Point pickPoint() {
    pack();
    refreshScreenshot();
    setVisible(true);
    return point;
  }
}
