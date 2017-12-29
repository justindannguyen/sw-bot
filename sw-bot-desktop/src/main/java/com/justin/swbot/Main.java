/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SaharaSkin;
import org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel;

import com.justin.swbot.home.HomeController;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.util.PcCommandUtil;
import com.justin.swbot.util.PcMemImageUtil;
import com.justin.swbot.util.PcOcrUtil;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class Main {
  public static void main(final String[] args) throws UnsupportedLookAndFeelException {
    DependenciesRegistry.commandUtil = new PcCommandUtil();
    DependenciesRegistry.memImageUtil = new PcMemImageUtil();
    DependenciesRegistry.ocrUtil = new PcOcrUtil();

    SubstanceLookAndFeel.setSkin(new SaharaSkin());
    UIManager.setLookAndFeel(new SubstanceSaharaLookAndFeel());

    final HomeController homeController = new HomeController();
    homeController.initialize();
    SwingUtilities.invokeLater(() -> homeController.launchUI());
  }

  private Main() {
    // Hidden constructor
  }
}
