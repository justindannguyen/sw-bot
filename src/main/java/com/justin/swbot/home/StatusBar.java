/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class StatusBar extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel statusLabel;
    private JLabel gameStatusLabel;

    public StatusBar() {
        initGUI();
    }

    public JLabel getGameStatusLabel() {
        if (gameStatusLabel == null) {
            gameStatusLabel = new JLabel("gameStatusIcon");
            gameStatusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            gameStatusLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        }
        return gameStatusLabel;
    }

    public JLabel getStatusLabel() {
        if (statusLabel == null) {
            statusLabel = new JLabel("statusLabel");
            statusLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        }
        return statusLabel;
    }

    private void initGUI() {
        setLayout(new MigLayout("", "0[grow,fill][100px:100px:100px,fill]0", "0[fill]0"));
        add(getStatusLabel(), "cell 0 0");
        add(getGameStatusLabel(), "cell 1 0");
    }
}
