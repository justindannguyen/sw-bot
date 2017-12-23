/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import static com.justin.swbot.home.HomeModel.PROFILES_LOADED;
import static com.justin.swbot.home.HomeModel.PROFILE_SELECTED;
import static com.justin.swbot.home.HomeModel.SCENARIOS_LOADED;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

import com.justin.swbot.game.Controller;
import com.justin.swbot.game.ControllerRegistry;
import com.justin.swbot.profile.AddProfileController;

/**
 * Action handling for the {@link HomeUI} as well as establish a link between model and view.
 *
 * @author tuan3.nguyen@gmail.com
 */
public final class HomeControllerAction implements HomeModelListener, ActionListener, ItemListener {
  private final HomeController homeController;

  public HomeControllerAction(final HomeController initialHomeController) {
    this.homeController = initialHomeController;
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    final HomeUI homeUI = homeController.getHomeUI();
    final Object source = e.getSource();
    if (source == homeUI.getToggeButton()) {
      // TODO to be implemented.
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
    homeUI.getProfileComboBox().addItemListener(this);
  }

  @Override
  public void itemStateChanged(final ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      final HomeModel homeModel = homeController.getHomeModel();
      final int selectedIndex = homeModel.setSelectedProfile((String) e.getItem());
      if (selectedIndex == 1) {
        createNewProfile();
      }
    }
  }

  @Override
  public void update(final Observable o, final Object arg) {
    if (PROFILES_LOADED.equals(arg)) {
      updateProfileComboBox();
    } else if (SCENARIOS_LOADED.equals(arg)) {
      updateScenarioComboBox();
    } else if (PROFILE_SELECTED.equals(arg)) {
      final HomeUI homeUI = homeController.getHomeUI();
      final HomeModel homeModel = homeController.getHomeModel();
      homeUI.getProfileComboBox().setSelectedItem(homeModel.getSelectedProfile());
    }
  }

  /**
   * Open dialog to create new profile.
   */
  private void createNewProfile() {
    homeController.unlaunchUI();

    Controller controller = ControllerRegistry.get(AddProfileController.class);
    if (controller == null) {
      final AddProfileController profileController = new AddProfileController();
      profileController.initialize();
      controller = profileController;
    }
    controller.launchUI();
  }

  /**
   * Update profile combobox with new data from model.
   */
  private void updateProfileComboBox() {
    final HomeModel homeModel = homeController.getHomeModel();
    final HomeUI homeUI = homeController.getHomeUI();

    final Runnable runnable = () -> {
      final DefaultComboBoxModel<String> model =
          (DefaultComboBoxModel<String>) homeUI.getProfileComboBox().getModel();
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
      final DefaultComboBoxModel<String> model =
          (DefaultComboBoxModel<String>) homeUI.getScenarioCombobox().getModel();
      model.removeAllElements();
      homeModel.getScenarios().stream().map(pair -> pair.getKey())
          .forEach(key -> model.addElement(key));
    };
    if (!SwingUtilities.isEventDispatchThread()) {
      SwingUtilities.invokeLater(runnable);
    } else {
      runnable.run();
    }
  }
}
