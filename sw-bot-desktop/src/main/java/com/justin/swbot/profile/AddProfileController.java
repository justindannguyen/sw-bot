/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.Controller;
import com.justin.swbot.ControllerRegistry;
import com.justin.swbot.game.profile.Profile;
import com.justin.swbot.game.profile.ProfileManager;
import lombok.Setter;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class AddProfileController implements Controller {
  private AddProfileUI ui;
  private final AddProfileModel model;
  private AddProfileControllerAction controllerAction;
  private ProfileManager profileManager;

  @Setter
  private String profileName; // editing profile name, null: create new profile

  public AddProfileController() {
    super();
    ControllerRegistry.register(this);
    model = new AddProfileModel();
    profileManager = DependenciesRegistry.profileManager;
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
      controllerAction.initialize();
    }

    if (profileName == null) {
      model.populateDataFromProfile(profileManager.createEmptyProfile());
    } else {
      Profile profile = profileManager.load(profileName);
      if (profile != null) model.populateDataFromProfile(profile);

      model.setProfileName(profileName);
    }
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
