/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Observable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.justin.swbot.component.event.ValueListener;
import com.justin.swbot.game.Controller;
import com.justin.swbot.game.ControllerRegistry;
import com.justin.swbot.game.GameConfig;
import com.justin.swbot.home.HomeController;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class AddProfileControllerAction
    implements AddProfileModelListener, ActionListener, KeyListener, ChangeListener,
    ValueListener {
  private final AddProfileController controller;

  public AddProfileControllerAction(final AddProfileController addProfileController) {
    this.controller = addProfileController;
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    final AddProfileUI ui = controller.getUI();
    if (e.getSource() == ui.getCancelButton()) {
      controller.unlaunchUI();
      ControllerRegistry.get(HomeController.class).launchUI();
    } else if (e.getSource() == ui.getCreateButton()) {
      createProfile();
    }
  }

  public void deinitialize() {
    controller.getModel().deleteObserver(this);
  }

  public void initialize() {
    final AddProfileModel model = controller.getModel();
    final AddProfileUI ui = controller.getUI();

    // Add model listeners
    model.addObserver(this);

    // Add UI listeners
    ui.getCancelButton().addActionListener(this);
    ui.getCreateButton().addActionListener(this);
    ui.getTextField().addKeyListener(this);
    ui.getSpinner().addChangeListener(this);
    ui.getReplayPointPicker().setValueListener(this);
    ui.getStartBattlePointPicker().setValueListener(this);
    ui.getSellRunePointPicker().setValueListener(this);
    ui.getSellRuneConfirmPointPicker().setValueListener(this);
    ui.getGetRunePointPicker().setValueListener(this);
    ui.getGetRewardPointPicker().setValueListener(this);
    ui.getAutoAttackPointPicker().setValueListener(this);
    ui.getRechargeYesPointPicker().setValueListener(this);
    ui.getRechargeNoPointPicker().setValueListener(this);
    ui.getEnergyShopPointPicker().setValueListener(this);
    ui.getConfirmRechargePointPicker().setValueListener(this);
    ui.getAckRefillPointPicker().setValueListener(this);
    ui.getCloseShopPointPicker().setValueListener(this);
    ui.getNetworkDelayPointPicker().setValueListener(this);
    ui.getResendBattleInfoPointPicker().setValueListener(this);

    ui.getReplayBattleBoxPicker().setValueListener(this);
    ui.getStartBattleBoxPicker().setValueListener(this);
    ui.getEndBattleBoxPicker().setValueListener(this);
    ui.getRuneRewardBoxPicker().setValueListener(this);
    ui.getConfirmSellRuneBoxPicker().setValueListener(this);
    ui.getOtherRewardBoxPicker().setValueListener(this);
    ui.getManualAttBoxPicker().setValueListener(this);
    ui.getNoEnergyBoxPicker().setValueListener(this);
    ui.getNetworkDelayBoxPicker().setValueListener(this);
    ui.getUnstableNetworkBoxPicker().setValueListener(this);
  }

  @Override
  public void keyPressed(final KeyEvent e) {
  }

  @Override
  public void keyReleased(final KeyEvent e) {
    final AddProfileUI ui = controller.getUI();
    if (e.getSource() == ui.getTextField()) {
      final AddProfileModel model = controller.getModel();
      model.setProfileName(ui.getTextField().getText());
    }
  }

  @Override
  public void keyTyped(final KeyEvent e) {

  }

  @Override
  public void stateChanged(final ChangeEvent e) {
    final AddProfileUI ui = controller.getUI();
    if (e.getSource() == ui.getSpinner()) {
      final AddProfileModel model = controller.getModel();
      model.setRefillTimes((int) ui.getSpinner().getValue());
    }
  }

  @Override
  public void update(final Observable o, final Object arg) {
    // TODO Auto-generated method stub

  }

  @Override
  public void valueChanged(final Object source, final Object newValue) {
    final AddProfileUI ui = controller.getUI();
    final AddProfileModel model = controller.getModel();
    if (source == ui.getReplayPointPicker()) {
      model.setReplayBattleLocation((Point) newValue);
    } else if (source == ui.getStartBattlePointPicker()) {
      model.setStartBattleLocation((Point) newValue);
    } else if (source == ui.getSellRunePointPicker()) {
      model.setSellRuneLocation((Point) newValue);
    } else if (source == ui.getSellRuneConfirmPointPicker()) {
      model.setSellRuneConfirmLocation((Point) newValue);
    } else if (source == ui.getGetRunePointPicker()) {
      model.setGetRuneRewardLocation((Point) newValue);
    } else if (source == ui.getGetRewardPointPicker()) {
      model.setGetRewardLocation((Point) newValue);
    } else if (source == ui.getAutoAttackPointPicker()) {
      model.setEnableAutoAttackLocation((Point) newValue);
    } else if (source == ui.getRechargeYesPointPicker()) {
      model.setRechargeEneryYesLocation((Point) newValue);
    } else if (source == ui.getRechargeNoPointPicker()) {
      model.setRechargeEnergyNoLocation((Point) newValue);
    } else if (source == ui.getEnergyShopPointPicker()) {
      model.setEnergyLocationOnShop((Point) newValue);
    } else if (source == ui.getConfirmRechargePointPicker()) {
      model.setConfirmRechargeEnergyLoation((Point) newValue);
    } else if (source == ui.getAckRefillPointPicker()) {
      model.setAckRefillSuccessLocation((Point) newValue);
    } else if (source == ui.getCloseShopPointPicker()) {
      model.setCloseRefillShopLocation((Point) newValue);
    } else if (source == ui.getNetworkDelayPointPicker()) {
      model.setConfirmNetworkDelayLocation((Point) newValue);
    } else if (source == ui.getResendBattleInfoPointPicker()) {
      model.setResendBattleInfoLocation((Point) newValue);
    } else if (source == ui.getReplayBattleBoxPicker()) {
      model.setReplayBattleIndicator((BufferedImage) newValue);
    } else if (source == ui.getStartBattleBoxPicker()) {
      model.setStartBattleIndicator((BufferedImage) newValue);
    } else if (source == ui.getEndBattleBoxPicker()) {
      model.setBattleEndIndicator((BufferedImage) newValue);
    } else if (source == ui.getRuneRewardBoxPicker()) {
      model.setRuneRewardIndiator((BufferedImage) newValue);
    } else if (source == ui.getConfirmSellRuneBoxPicker()) {
      model.setConfirmSellRuneIndicator((BufferedImage) newValue);
    } else if (source == ui.getOtherRewardBoxPicker()) {
      model.setOtherRewardIndicator((BufferedImage) newValue);
    } else if (source == ui.getManualAttBoxPicker()) {
      model.setManualAttackIndicator((BufferedImage) newValue);
    } else if (source == ui.getNoEnergyBoxPicker()) {
      model.setNoEnergyIndicator((BufferedImage) newValue);
    } else if (source == ui.getNetworkDelayBoxPicker()) {
      model.setNetworkDelayIndicator((BufferedImage) newValue);
    } else if (source == ui.getUnstableNetworkBoxPicker()) {
      model.setNetworkUnstableIndicator((BufferedImage) newValue);
    }
  }

  private void createProfile() {
    // TODO validation all fields, for now assume all are valid.
    final GameConfig config = GameConfig.get();
    final AddProfileModel model = controller.getModel();
    config.setProfileName(model.getProfileName());
    config.setRefillTimes(model.getRefillTimes());
    config.setReplayBattle(model.getReplayBattleLocation());
    config.setStartBattle(model.getStartBattleLocation());
    config.setSellRuneLocation(model.getSellRuneLocation());
    config.setSellRuneConfirmation(model.getSellRuneConfirmLocation());
    config.setGetRuneLocation(model.getGetRuneRewardLocation());
    config.setGetRewardLocation(model.getGetRewardLocation());
    config.setEnableAutoMode(model.getEnableAutoAttackLocation());
    config.setRechargeEnergyYes(model.getRechargeEneryYesLocation());
    config.setRechargeEnergyNo(model.getRechargeEnergyNoLocation());
    config.setRechargeEnergy(model.getEnergyLocationOnShop());
    config.setConfirmRechargeEnergy(model.getConfirmRechargeEnergyLoation());
    config.setAckRechargeEnergyOk(model.getAckRefillSuccessLocation());
    config.setCloseRechargeEnergy(model.getCloseRefillShopLocation());
    config.setConfirmNetworkDelay(model.getConfirmNetworkDelayLocation());
    config.setResendBattleInfoX(model.getResendBattleInfoLocation());
    config.setReplayBattleIndicator(model.getReplayBattleIndicator());
    config.setStartBattleIndicator(model.getStartBattleIndicator());
    config.setBattleEndIndicator(model.getBattleEndIndicator());
    config.setRuneRewardIndiator(model.getRuneRewardIndiator());
    config.setConfirmSellRuneIndicator(model.getConfirmSellRuneIndicator());
    config.setOtherRewardIndicator(model.getOtherRewardIndicator());
    config.setManualAttackIndicator(model.getManualAttackIndicator());
    config.setNoEnergyIndicator(model.getNoEnergyIndicator());
    config.setNetworkDelayIndicator(model.getNetworkDelayIndicator());
    config.setNetworkUnstableIndicator(model.getNetworkUnstableIndicator());

    config.save();

    controller.unlaunchUI();
    final Controller homeController = ControllerRegistry.get(HomeController.class);
    homeController.launchUI();
  }
}
