/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.Point;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class PointPicker extends AbstractPicker<Point> {
  private static final long serialVersionUID = 1L;

  public PointPicker() {
    super();

    remove(getInfoButton());
  }

  @Override
  protected void pickData() {
    final PointPickerDialog pickerDialog = new PointPickerDialog();
    final Point selectedPoint = pickerDialog.pickPoint();
    if (selectedPoint != null) {
      getTextLabel().setText(String.format("Point (%s, %s)", selectedPoint.x, selectedPoint.y));
    }
    if (valueListener != null) {
      valueListener.valueChanged(selectedPoint);
    }
  }
}
