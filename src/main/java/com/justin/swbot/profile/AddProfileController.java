/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import com.justin.swbot.game.Controller;
import com.justin.swbot.game.ControllerRegistry;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class AddProfileController implements Controller {
  private AddProfileUI ui;
  private AddProfileModel model;
  private AddProfileControllerAction controllerAction;

  public void initialize() {
    ControllerRegistry.register(this);
    if (model == null) {
      model = new AddProfileModel();
    }
  }

  @Override
  public void launchUI() {
    if (ui == null) {
      ui = new AddProfileUI();
      ui.pack();
    }
    ui.setVisible(true);

    if (controllerAction == null) {
      controllerAction = new AddProfileControllerAction(this);
    }
    controllerAction.initialize();
  }

  @Override
  public void unlaunchUI() {
    this.ui.dispose();
  }

  protected AddProfileModel getModel() {
    return model;
  }

  protected AddProfileUI getUI() {
    return ui;
  }
}
