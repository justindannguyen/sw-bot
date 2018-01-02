/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import com.justin.swbot.util.CommandUtil;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class AbstractPickerDialog extends JDialog {
  private static final long serialVersionUID = 1L;

  private JLabel lblLoadingScreenshotFrom;
  private JScrollPane scrollPane;
  protected BufferedImage screenImage;

  public AbstractPickerDialog() {
    initGUI();

    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    final Dimension screenSize = toolkit.getScreenSize();
    setPreferredSize(screenSize);
  }

  protected void customPaint(final Graphics g) {}

  protected JLabel getMainLabel() {
    if (lblLoadingScreenshotFrom == null) {
      lblLoadingScreenshotFrom = new JLabel("...") {
        private static final long serialVersionUID = 1L;
        @Override
        protected void paintComponent(final Graphics g) {
          super.paintComponent(g);
          customPaint(g);
        }
      };
      lblLoadingScreenshotFrom.setFont(new Font("Tahoma", Font.PLAIN, 38));
    }
    return lblLoadingScreenshotFrom;
  }

  protected void refreshScreenshot() {
    getMainLabel().setText("Loading screenshot from phone, please wait...");
    getMainLabel().setIcon(null);
    getMainLabel().revalidate();
    getMainLabel().repaint();
    screenImage = null;

    // Capture the screenshot from connected phone and allow user to choose point location.
    new SwingWorker<Void, String>() {
      @Override
      protected Void doInBackground() throws Exception {
        setProgress(0);
        publish(CommandUtil.capturePhoneScreen());
        setProgress(100);
        return null;
      }

      @Override
      protected void process(final List<String> paths) {
        setScreenshot(paths.get(0));
      }
    }.execute();
  }

  private JScrollPane getScrollPane() {
    if (scrollPane == null) {
      scrollPane = new JScrollPane();
      scrollPane.setViewportView(getMainLabel());
    }
    return scrollPane;
  }

  private void initGUI() {
    setModal(true);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(0, 0));
    getContentPane().add(getScrollPane(), BorderLayout.CENTER);
  }

  private void setScreenshot(final String filename) {
    if (!isVisible()) {
      return;
    }

    if (filename == null) {
      getMainLabel().setText("Could not load screenshot, right click to refresh");
    } else {
      getMainLabel().setText("");
      Icon newIcon;
      try {
        screenImage = ImageIO.read(new File(filename));
        newIcon = new ImageIcon(screenImage);
      } catch (final IOException ex) {
        newIcon = new ImageIcon(filename);
      }
      getMainLabel().setIcon(newIcon);
    }
    getMainLabel().revalidate();
    getMainLabel().repaint();
  }
}
