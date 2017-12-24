/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import javax.swing.SwingUtilities;

import com.justin.swbot.game.Controller;
import com.justin.swbot.game.ControllerRegistry;
import com.justin.swbot.game.GameState;

/**
 * @author tuan3.nguyen@gmail.com
 */
public final class HomeController implements Controller {
  private HomeUI homeUI;
  private HomeModel homeModel;
  private HomeControllerAction homeControllerAction;

  public void initialize() {
    ControllerRegistry.register(this);
    if (homeModel == null) {
      homeModel = new HomeModel();
    }
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

  public void updateGameStatus(final GameState state) {
    updateStatus(state.toString());
  }

  public void updateStatus(final String status) {
    SwingUtilities.invokeLater(() -> homeUI.getStatusBar().getStatusLabel().setText(status));

  }

  protected HomeModel getHomeModel() {
    return homeModel;
  }

  protected HomeUI getHomeUI() {
    return homeUI;
  }
}
