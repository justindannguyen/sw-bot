/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.BotEngine;
import com.justin.swbot.Controller;
import com.justin.swbot.ControllerRegistry;
import com.justin.swbot.game.GameState;
import com.justin.swbot.game.director.ScenarioDirector;
import com.justin.swbot.game.profile.Profile;
import com.justin.swbot.ui.HomeView;
import lombok.Getter;
import lombok.Setter;

import javax.swing.SwingUtilities;
import java.util.AbstractMap;

/**
 * @author tuan3.nguyen@gmail.com
 */
public final class HomeController implements Controller, HomeView {
  private HomeUI homeUI;
  private HomeModel homeModel;
  private HomeControllerAction homeControllerAction;

  // Workaround to remember last selected profile, to prevent popup edit profile dialog
  @Setter
  @Getter
  private String lastSelectedProfile;

  private BotEngine botEngine;

  public void initialize() {
    ControllerRegistry.register(this);
    if (homeModel == null) {
      homeModel = new HomeModel();
    }
  }

  private boolean isBotEngineRunning() {
    return botEngine != null && botEngine.isAlive();
  }

  public void onBtnStartClicked() {
    if (isBotEngineRunning()) {
      stopAuto();
    } else {
      startAuto();
    }
  }

  private void startAuto() {
    // Check condition so that we can start the auto
    if (homeModel.getProfiles().indexOf(homeModel.getSelectedProfile()) <= 1) {
      updateStatus("Select profile to start...");
      return;
    }

    // Profile
    Profile profile = DependenciesRegistry.profileManager.load(homeModel.getSelectedProfile());
    if (profile == null) {
      updateStatus("Invalid profile");
      return;
    }

    // Director
    ScenarioDirector selectedDirector = null;
    for (final AbstractMap.SimpleImmutableEntry<String, ScenarioDirector> scenario : homeModel.getScenarios()) {
      if (scenario.getKey().equals(homeModel.getSelectedScenario())) {
        selectedDirector = scenario.getValue();
        break;
      }
    }
    if (selectedDirector == null) {
      updateStatus("Select scenario to start...");
      return;
    }
    selectedDirector.setProfile(profile);
    selectedDirector.bindView(this);
    selectedDirector.restart();

    // Engine
    botEngine = new BotEngine(selectedDirector, profile, this);
    botEngine.start();

    homeUI.getToggeButton().setText("Stop");
  }

  private void stopAuto() {
    if (!isBotEngineRunning()) return;

    botEngine.stopEngine();
    botEngine = null;
    homeUI.getToggeButton().setText("Start");
  }

  @Override
  public void launchUI() {
    if (homeUI == null) {
      homeUI = new HomeUI();
      homeUI.pack();
    }
    homeUI.setVisible(true);

    if (homeControllerAction == null) {
      homeControllerAction = new HomeControllerAction(this);
      homeControllerAction.initialize();
    }
    homeModel.loadData();
  }

  @Override
  public void unlaunchUI() {
    homeUI.setVisible(false);
  }

  @Override
  public void updateGameStatus(final GameState state) {
    SwingUtilities
        .invokeLater(() -> homeUI.getStatusBar().getGameStatusLabel()
            .setText(state == null ? "" : state.name()));
  }

  @Override
  public void updateStatus(final String message) {
    SwingUtilities.invokeLater(() -> homeUI.getStatusBar().getStatusLabel().setText(message));
  }

  protected HomeModel getHomeModel() {
    return homeModel;
  }

  protected HomeUI getHomeUI() {
    return homeUI;
  }
}
