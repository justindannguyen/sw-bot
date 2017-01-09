/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

/**
 * @author tuan3.nguyen@gmail.com
 */
public final class HomeController {
    private HomeUI homeUI;
    private HomeModel homeModel;
    private HomeControllerAction homeControllerAction;

    public void initialize() {
        if (homeModel == null) {
            homeModel = new HomeModel();
        }
    }

    public void launchUI() {
        if (homeUI == null) {
            homeUI = new HomeUI();
            homeUI.pack();
        }
        homeUI.setVisible(true);

        if (homeControllerAction == null) {
            homeControllerAction = new HomeControllerAction(this);
        }
        homeControllerAction.initialize();
    }

    protected HomeModel getHomeModel() {
        return homeModel;
    }

    protected HomeUI getHomeUI() {
        return homeUI;
    }
}
