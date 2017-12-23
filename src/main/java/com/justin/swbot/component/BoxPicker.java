/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Image;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class BoxPicker extends AbstractPicker {
  private static final long serialVersionUID = 1L;

  public BoxPicker() {
    super();
  }

  @Override
  protected void pickData() {
    final BoxPickerDialog pickerDialog = new BoxPickerDialog();
    final Image selectedBox = pickerDialog.pickBox();
    if (selectedBox != null) {
      getTextLabel().setText("Image Available");
    }
    if (valueListener != null) {
      valueListener.valueChanged(this, selectedBox);
    }
  }

  @Override
  protected void viewData() {
    // TODO Auto-generated method stub
    super.viewData();
  }
}
