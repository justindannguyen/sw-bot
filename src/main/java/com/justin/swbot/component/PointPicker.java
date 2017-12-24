/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Point;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class PointPicker extends AbstractPicker {
  private static final long serialVersionUID = 1L;

  public PointPicker() {
    super();

    remove(getInfoButton());
  }

  @Override
  protected String getDataText(final Object data) {
    final Point selectedPoint = (Point) data;
    return selectedPoint == null ? ""
        : String.format("Point (%s, %s)", selectedPoint.x, selectedPoint.y);
  }

  @Override
  protected void pickData() {
    final PointPickerDialog pickerDialog = new PointPickerDialog();
    final Point selectedPoint = pickerDialog.pickPoint();

    if (selectedPoint != null) {
      setData(selectedPoint);
      if (valueListener != null) {
        valueListener.valueChanged(this, selectedPoint);
      }
    }
  }
}
