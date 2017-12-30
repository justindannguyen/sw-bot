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
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

import com.justin.swbot.game.BotEngine;
import com.justin.swbot.game.Controller;
import com.justin.swbot.game.ControllerRegistry;
import com.justin.swbot.game.GameConfig;
import com.justin.swbot.game.director.AbstractDirector;
import com.justin.swbot.game.director.ScenarioDirector;
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
      toggleGameAuto();
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
    homeUI.getScenarioCombobox().addItemListener(this);
  }

  @Override
  public void itemStateChanged(final ItemEvent e) {
    if (e.getStateChange() != ItemEvent.SELECTED) {
      return;
    }
    final HomeUI homeUI = homeController.getHomeUI();
    final HomeModel homeModel = homeController.getHomeModel();
    if (e.getSource() == homeUI.getProfileComboBox()) {
      final int selectedIndex = homeModel.setSelectedProfile((String) e.getItem());
      if (selectedIndex == 1) {
        createNewProfile();
      } else if (selectedIndex > 1) {
        editProfile(homeModel.getSelectedProfile());
      }
    } else if (e.getSource() == homeUI.getScenarioCombobox()) {
      homeModel.setSelectedScenario((String) e.getItem());
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
    GameConfig.get().clear();
    homeController.unlaunchUI();

    Controller controller = ControllerRegistry.get(AddProfileController.class);
    if (controller == null) {
      final AddProfileController profileController = new AddProfileController();
      profileController.initialize();
      controller = profileController;
    }
    controller.launchUI();
  }

  private void editProfile(final String selectedProfile) {
    if (selectedProfile.equalsIgnoreCase(GameConfig.get().getProfileName())) {
      return;
    }

    GameConfig.get().load(selectedProfile);
    homeController.unlaunchUI();

    Controller controller = ControllerRegistry.get(AddProfileController.class);
    if (controller == null) {
      final AddProfileController profileController = new AddProfileController();
      profileController.initialize();
      controller = profileController;
    }
    controller.launchUI();
  }

  private void toggleGameAuto() {
    final boolean running = BotEngine.get().isRunning();
    final HomeUI homeUI = homeController.getHomeUI();
    if (!running) {
      // Check condition so that we can start the auto
      final HomeModel model = homeController.getHomeModel();
      if (model.getProfiles().indexOf(model.getSelectedProfile()) <= 1) {
        homeController.updateStatus("Select profile to start...");
        return;
      }
      ScenarioDirector selectedDirector = null;
      for (final SimpleImmutableEntry<String, ScenarioDirector> scenario : model.getScenarios()) {
        if (scenario.getKey().equals(model.getSelectedScenario())) {
          selectedDirector = scenario.getValue();
          break;
        }
      }
      if (selectedDirector == null) {
        homeController.updateStatus("Select scenario to start...");
        return;
      }

      // Attach view
      if (selectedDirector instanceof AbstractDirector) {
        AbstractDirector abstractDirector = (AbstractDirector) selectedDirector;
        abstractDirector.setHomeView(homeController);
        abstractDirector.setGameConfig(GameConfig.get());
      }

      BotEngine.get().setDirector(selectedDirector);
    }

    homeUI.getToggeButton().setText(running ? "Start" : "Stop");
    BotEngine.get().setRunning(!running);
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
