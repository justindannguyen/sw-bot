/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Rectangle;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class BoxPicker extends AbstractPicker {
  private static final long serialVersionUID = 1L;

  public BoxPicker() {
    super();

    remove(getInfoButton());
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
}
