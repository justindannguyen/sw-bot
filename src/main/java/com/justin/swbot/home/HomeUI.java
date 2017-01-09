/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;

/**
 * @author tuan3.nguyen@gmail.com
 */
public final class HomeUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private StatusBar statusBar;
    private JPanel actionPanel;
    private JComboBox<String> scenarioCombobox;
    private JToggleButton toggeButton;
    private JComboBox<String> profileComboBox;

    public HomeUI() {
        initGUI();
    }

    private JPanel getActionPanel() {
        if (actionPanel == null) {
            actionPanel = new JPanel();
            actionPanel.setLayout(new MigLayout("", "[grow,fill][grow,fill][100px:100px:100px,fill]", "[]"));
            actionPanel.add(getProfileComboBox(), "cell 0 0,growx");
            actionPanel.add(getScenarioCombobox(), "flowx,cell 1 0,growx");
            actionPanel.add(getToggeButton(), "cell 2 0");
        }
        return actionPanel;
    }

    public JComboBox<String> getProfileComboBox() {
        if (profileComboBox == null) {
            profileComboBox = new JComboBox<String>();
            profileComboBox.setModel(new DefaultComboBoxModel<>());
        }
        return profileComboBox;
    }

    public JComboBox<String> getScenarioCombobox() {
        if (scenarioCombobox == null) {
            scenarioCombobox = new JComboBox<String>();
            scenarioCombobox.setModel(new DefaultComboBoxModel<>());
        }
        return scenarioCombobox;
    }

    private StatusBar getStatusBar() {
        if (statusBar == null) {
            statusBar = new StatusBar();
        }
        return statusBar;
    }

    public JToggleButton getToggeButton() {
        if (toggeButton == null) {
            toggeButton = new JToggleButton("Start");
        }
        return toggeButton;
    }

    private void initGUI() {
        setPreferredSize(new Dimension(450, 100));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Summoner War Bot");
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().add(getStatusBar(), BorderLayout.SOUTH);
        getContentPane().add(getActionPanel(), BorderLayout.NORTH);
    }
}
