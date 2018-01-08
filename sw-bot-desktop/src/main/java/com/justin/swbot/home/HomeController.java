/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import com.justin.swbot.Controller;
import com.justin.swbot.ControllerRegistry;
import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.AutoSession;
import com.justin.swbot.game.GameState;
import com.justin.swbot.game.GameStatus;
import com.justin.swbot.game.director.Director;
import com.justin.swbot.game.profile.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.swing.SwingUtilities;
import java.util.Map;

/**
 * @author tuan3.nguyen@gmail.com
 */
public final class HomeController implements Controller, AutoSession.Listener {
  private HomeUI homeUI;
  private HomeModel homeModel;
  private HomeControllerAction homeControllerAction;

  // Workaround to remember last selected profile, to prevent popup edit profile dialog
  @Setter
  @Getter
  private String lastSelectedProfile;

  private AutoSession autoSession;

  public void initialize() {
    ControllerRegistry.register(this);
    if (homeModel == null) {
      homeModel = new HomeModel();
    }
  }

  private boolean isSessionRunning() {
    return autoSession != null && autoSession.getState().equals(AutoSession.State.RUNNING);
  }

  public void onBtnStartClicked() {
    if (isSessionRunning()) {
      stopAuto();
    } else {
      startAuto();
    }
  }

  private void startAuto() {
    // Check condition so that we can start the auto
    if (homeModel.getProfiles().indexOf(homeModel.getSelectedProfile()) <= 1) {
      updateMessage("Select profile to start...");
      return;
    }

    // Profile
    Profile profile = DependenciesRegistry.profileManager.load(homeModel.getSelectedProfile());
    if (profile == null) {
      updateMessage("Invalid profile");
      return;
    }

    // Find Director
    Class<? extends Director> selectedDirector = null;
    for (Map.Entry<String, Class<? extends Director>> entry : homeModel.getDirectors().entrySet()) {
      if (entry.getKey().equals(homeModel.getSelectedDirector())) {
        selectedDirector = entry.getValue();
        break;
      }
    }
    if (selectedDirector == null) {
      updateMessage("Select scenario to start...");
      return;
    }

    // Start session
    autoSession = new AutoSession(this, profile, selectedDirector);
    autoSession.start();

    homeUI.getToggeButton().setText("Stop");
  }

  private void stopAuto() {
    if (!isSessionRunning()) return;

    autoSession.stop();
    autoSession = null;
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

  private void updateMessage(final String message) {
    SwingUtilities.invokeLater(() -> homeUI.getStatusBar().getStatusLabel().setText(message));
  }

  private void updateGameState(GameState gameState) {
    SwingUtilities
        .invokeLater(() -> homeUI.getStatusBar().getGameStatusLabel()
            .setText(gameState == null ? "" : gameState.name()));
  }

  protected HomeModel getHomeModel() {
    return homeModel;
  }

  protected HomeUI getHomeUI() {
    return homeUI;
  }

  @Override
  public void onSessionStopped() {
    updateMessage("Stopped");
  }

  @Override
  public void onStartDetectingGameStatus() {
    updateMessage("Detecting...");
  }

  @Override
  public void onGameStatusDetected(GameStatus gameStatus) {
    updateMessage("Matched: " + gameStatus.getGameState().name());
    updateGameState(gameStatus.getGameState());
  }

  @Override
  public void onStartGivingDirection() {

  }

  @Override
  public void onGameStatusProcessed(GameStatus gameStatus, boolean success) {
    updateMessage("Done direct for " + gameStatus.getGameState().name());
  }

  @Override
  public void onException(Exception e) {
    updateMessage("Exception: " + e.getMessage());
  }

  @Override
  public void onTryingToRefillEnergy() {
    updateMessage("Trying to refill energy...");
  }

  @Override
  public void onNoMoreRun() {
    updateMessage("No more run");
    autoSession.stop();
  }
}
