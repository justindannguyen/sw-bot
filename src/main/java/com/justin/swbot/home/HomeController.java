/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import com.justin.swbot.game.Controller;
import com.justin.swbot.game.ControllerRegistry;

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

  public void selectProfile(final String profileName) {
    homeModel.setSelectedProfile(profileName);
  }

  @Override
  public void unlaunchUI() {
    homeUI.setVisible(false);
  }

  protected HomeModel getHomeModel() {
    return homeModel;
  }

  protected HomeUI getHomeUI() {
    return homeUI;
  }
}
