/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import com.justin.swbot.home.HomeController;

import javax.swing.SwingUtilities;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class Main {
    public static void main(final String[] args) {
        HomeController homeController = new HomeController();
        homeController.initialize();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                homeController.launchUI();
            }
        });
    }
}
