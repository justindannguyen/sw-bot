/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class BoxPickerDialog extends AbstractPickerDialog {
  private static final long serialVersionUID = 1L;

  private Point startPoint;
  private Point endPoint;

  public BoxPickerDialog() {
    super();

    final MouseAdapter mouseAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(final MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
          refreshScreenshot();
          e.consume();
        }
      }

      @Override
      public void mouseDragged(final MouseEvent e) {
        if (startPoint != null) {
          endPoint = e.getPoint();
          e.consume();
          repaint();
        }
      }

      @Override
      public void mousePressed(final MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
          startPoint = e.getPoint();
          e.consume();
        }
      }

      @Override
      public void mouseReleased(final MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
          endPoint = e.getPoint();
          e.consume();
          dispose();
        }
      }
    };
    getMainLabel().addMouseListener(mouseAdapter);
    getMainLabel().addMouseMotionListener(mouseAdapter);
  }

  @Override
  public void paint(final Graphics g) {
    super.paint(g);

    if(startPoint != null && endPoint != null) {
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


  public Image pickBox() {
    pack();
    refreshScreenshot();
    setVisible(true);

    final int x = Math.min(startPoint.x, endPoint.x);
    final int y = Math.min(startPoint.y, endPoint.y);
    final int width = Math.abs(startPoint.x - endPoint.x);
    final int height = Math.abs(startPoint.y - endPoint.y);
    if (width == 0 || height == 0 || screenImage == null) {
      return null;
    }
    return screenImage.getSubimage(x, y, width, height);
  }
}
