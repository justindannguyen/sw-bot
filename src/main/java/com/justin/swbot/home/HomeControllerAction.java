/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import static com.justin.swbot.home.HomeModel.PROFILES_LOADED;
import static com.justin.swbot.home.HomeModel.SCENARIOS_LOADED;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

/**
 * Action handling for the {@link HomeUI} as well as establish a link between model and view.
 *
 * @author tuan3.nguyen@gmail.com
 */
public class HomeControllerAction implements HomeModelListener, ActionListener {
    private final HomeController homeController;

    public HomeControllerAction(final HomeController homeController) {
        this.homeController = homeController;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        final HomeUI homeUI = homeController.getHomeUI();
        final Object source = e.getSource();
        if (source == homeUI.getToggeButton()) {
        }
    }

    public void deinitialize() {
        homeController.getHomeModel().deleteObserver(this);
    }

    public void initialize() {
        final HomeModel homeModel = homeController.getHomeModel();
        final HomeUI homeUI = homeController.getHomeUI();

        // Add model listeners
        homeModel.addObserver(this);

        // Add UI listeners
        homeUI.getToggeButton().addActionListener(this);

        // Reload default data
        homeModel.loadData();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(final Observable o, final Object arg) {
        if (PROFILES_LOADED.equals(arg)) {
            updateProfileComboBox();
        } else if (SCENARIOS_LOADED.equals(arg)) {
            updateScenarioComboBox();
        }
    }

    /**
     * Update profile combobox with new data from model.
     */
    private void updateProfileComboBox() {
        final HomeModel homeModel = homeController.getHomeModel();
        final HomeUI homeUI = homeController.getHomeUI();

        final Runnable runnable = () -> {
            final DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) homeUI.getProfileComboBox().getModel();
            model.removeAllElements();
            homeModel.getProfiles().stream().forEach(profile -> model.addElement(profile));
        };
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(runnable);
        } else {
            runnable.run();
        }
    }

    /**
     * Update scenario combobox with new data from model.
     */
    private void updateScenarioComboBox() {
        final HomeModel homeModel = homeController.getHomeModel();
        final HomeUI homeUI = homeController.getHomeUI();

        final Runnable runnable = () -> {
            final DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) homeUI.getScenarioCombobox().getModel();
            model.removeAllElements();
            homeModel.getScenarios().stream().map(pair -> pair.getKey()).forEach(key -> model.addElement(key));
        };
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(runnable);
        } else {
            runnable.run();
        }
    }
}
