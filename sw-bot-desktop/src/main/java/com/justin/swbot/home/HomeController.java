/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import javax.swing.SwingUtilities;

import com.justin.swbot.game.BotEngine;
import com.justin.swbot.game.Controller;
import com.justin.swbot.game.ControllerRegistry;
import com.justin.swbot.game.GameState;
import com.justin.swbot.ui.HomeView;

/**
 * @author tuan3.nguyen@gmail.com
 */
public final class HomeController implements Controller, HomeView {
  private HomeUI homeUI;
  private HomeModel homeModel;
  private HomeControllerAction homeControllerAction;

  public void initialize() {
    ControllerRegistry.register(this);
    if (homeModel == null) {
      homeModel = new HomeModel();
    }

    BotEngine botEngine = BotEngine.get();
    botEngine.setHomeView(this);
    botEngine.start();
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
