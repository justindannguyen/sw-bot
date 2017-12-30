/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.justin.swbot.component.event.ValueListener;

import net.miginfocom.swing.MigLayout;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class AbstractPicker extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;

  private JLabel textLabel;
  private JButton browseButton;
  protected ValueListener valueListener;
  private JButton infoButton;
  private Object data;

  public AbstractPicker() {
    initGUI();

    getBrowseButton().addActionListener(this);
    getInfoButton().addActionListener(this);
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    if (e.getSource() == getBrowseButton()) {
      pickData();
    } else if (e.getSource() == getInfoButton()) {
      viewData();
    }
  }

  public Object getData() {
    return data;
  }

  public void setData(final Object data) {
    getTextLabel().setText(getDataText(data));
    this.data = data;
  }

  public void setValueListener(final ValueListener valueListener) {
    this.valueListener = valueListener;
  }

  protected JButton getBrowseButton() {
    if (browseButton == null) {
      browseButton = new JButton("...");
    }
    return browseButton;
  }

  protected abstract String getDataText(Object data);

  protected JButton getInfoButton() {
    if (infoButton == null) {
      infoButton = new JButton("i");
    }
    return infoButton;
  }

  protected JLabel getTextLabel() {
    if (textLabel == null) {
      textLabel = new JLabel("Not Available");
    }
    return textLabel;
  }

  protected abstract void pickData();

  protected void viewData() {

  }

  private void initGUI() {
    setLayout(new MigLayout("", "0[grow,fill][50px,fill]0[50px,fill]0", "0[]0"));
    add(getTextLabel(), "cell 0 0");
    add(getInfoButton(), "cell 1 0");
    add(getBrowseButton(), "cell 2 0");
  }
}
