/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import com.justin.swbot.util.PcUtil;

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
    Rectangle rect = PcUtil.castToRectangle(data);
    return data == null ? "Not Available"
        : String.format("x: %s, y: %s, w:%s, h: %s", rect.x, rect.y, rect.width, rect.height);
  }

  @Override
  protected void pickData() {
    final BoxPickerDialog pickerDialog = new BoxPickerDialog();
    final Rectangle selectedBox = pickerDialog.pickBox();
    if (selectedBox != null) {
      Object genericValue = PcUtil.toGeneric(selectedBox);
      setData(genericValue);
      if (valueListener != null) {
        valueListener.valueChanged(this, genericValue);
      }
    }
  }
}
