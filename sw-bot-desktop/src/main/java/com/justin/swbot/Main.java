/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.home.HomeController;
import com.justin.swbot.util.PcCommandUtil;
import com.justin.swbot.util.PcOcrUtil;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SaharaSkin;
import org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class Main {
  public static void main(final String[] args) throws UnsupportedLookAndFeelException {
    SubstanceLookAndFeel.setSkin(new SaharaSkin());
    UIManager.setLookAndFeel(new SubstanceSaharaLookAndFeel());

    DependenciesRegistry.settings = new PcSettings();
    DependenciesRegistry.commandUtil = new PcCommandUtil();
    DependenciesRegistry.ocrUtil = new PcOcrUtil();

    final HomeController homeController = new HomeController();
    homeController.initialize();
    SwingUtilities.invokeLater(() -> homeController.launchUI());
  }

  private Main() {
    // Hidden constructor
  }
}
