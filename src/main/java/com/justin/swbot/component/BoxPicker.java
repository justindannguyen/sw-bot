/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class BoxPicker extends AbstractPicker {
  private static final long serialVersionUID = 1L;

  public BoxPicker() {
    super();
  }

  @Override
  protected String getDataText(final Object data) {
    return data == null ? "Not Available" : "Available";
  }

  @Override
  protected void pickData() {
    final BoxPickerDialog pickerDialog = new BoxPickerDialog();
    final Image selectedBox = pickerDialog.pickBox();
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
    if (data != null) {
      final BufferedImage image = (BufferedImage) getData();
      final JLabel label = new JLabel();
      label.setIcon(new ImageIcon(image));
      JOptionPane.showMessageDialog(this, label);
    }
  }
}
