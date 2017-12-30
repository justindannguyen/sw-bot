/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.justin.swbot.OcrUtil;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class BoxPicker extends AbstractPicker {
  private static class ImageBox extends JLabel {
    private static final long serialVersionUID = 1L;

    private final Rectangle box;

    public ImageBox(final Rectangle box) {
      this.box = box;
    }

    @Override
    protected void paintComponent(final Graphics g) {
      super.paintComponent(g);
      if (box != null) {
        g.setColor(Color.green);
        g.drawRect(box.x, box.y, box.width, box.height);
      }
    }
  }

  private static final long serialVersionUID = 1L;

  public BoxPicker() {
    super();
  }

  @Override
  protected String getDataText(final Object data) {
    final Rectangle rect = (Rectangle) data;
    return data == null ? "Not Available"
        : String.format("x: %s, y: %s, w:%s, h: %s", rect.x, rect.y, rect.width, rect.height);
  }

  @Override
  protected void pickData() {
    final BoxPickerDialog pickerDialog = new BoxPickerDialog();
    final Rectangle selectedBox = pickerDialog.pickBox();
    if (selectedBox != null) {
      setData(selectedBox);
      if (valueListener != null) {
        valueListener.valueChanged(this, selectedBox);
      }
    }
  }

  @Override
  protected void viewData() {
    super.viewData();
    final Rectangle data = (Rectangle) getData();
    if (data == null) {
      return;
    }

    final JFileChooser fc = new JFileChooser(new File("").getAbsolutePath());
    final int option = fc.showOpenDialog(this);
    fc.setMultiSelectionEnabled(false);
    if (option != JFileChooser.APPROVE_OPTION) {
      return;
    }
    try {
      final BufferedImage image = ImageIO.read(fc.getSelectedFile());
      final JLabel label = new ImageBox(data);
      label.setIcon(new ImageIcon(image));
      JOptionPane.showMessageDialog(this, new JScrollPane(label));

      JOptionPane.showMessageDialog(this,
          OcrUtil.text(image.getSubimage(data.x, data.y, data.width, data.height)));
    } catch (final IOException ex) {
      JOptionPane.showMessageDialog(this, "Could not display image " + ex.getMessage());
    }
  }
}
