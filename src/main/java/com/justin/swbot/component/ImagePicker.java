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
public class ImagePicker extends AbstractPicker {
  private static final long serialVersionUID = 1L;

  @Override
  protected String getDataText(final Object data) {
    return data == null ? "Not Available" : "Available";
  }

  @Override
  protected void pickData() {
    final ImagePickerDialog pickerDialog = new ImagePickerDialog();
    final Image selectedBox = pickerDialog.pickImage();
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
