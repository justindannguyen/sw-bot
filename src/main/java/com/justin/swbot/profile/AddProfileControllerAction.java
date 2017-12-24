/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import static com.justin.swbot.profile.AddProfileModel.MODEL_LOADED;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
      cancel();
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

    ui.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(final WindowEvent e) {
        cancel();
      }
    });
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
    if (MODEL_LOADED.equals(arg)) {
      reloadUIFromModel();
    }
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

  private void cancel() {
    controller.unlaunchUI();
    ControllerRegistry.get(HomeController.class).launchUI();
  }

  private void createProfile() {
    // TODO validation all fields, for now assume all are valid.
    final GameConfig config = GameConfig.get();
    final AddProfileModel model = controller.getModel();
    if (model.getProfileName() != null) {
      config.setProfileName(model.getProfileName());
    }
    config.setRefillTimes(model.getRefillTimes());
    if (model.getReplayBattleLocation() != null) {
      config.setReplayBattle(model.getReplayBattleLocation());
    }
    if (model.getStartBattleLocation() != null) {
      config.setStartBattle(model.getStartBattleLocation());
    }
    if (model.getSellRuneLocation() != null) {
      config.setSellRuneLocation(model.getSellRuneLocation());
    }
    if (model.getSellRuneConfirmLocation() != null) {
      config.setSellRuneConfirmation(model.getSellRuneConfirmLocation());
    }
    if (model.getGetRuneRewardLocation() != null) {
      config.setGetRuneLocation(model.getGetRuneRewardLocation());
    }
    if (model.getGetRewardLocation() != null) {
      config.setGetRewardLocation(model.getGetRewardLocation());
    }
    if (model.getEnableAutoAttackLocation() != null) {
      config.setEnableAutoMode(model.getEnableAutoAttackLocation());
    }
    if (model.getRechargeEneryYesLocation() != null) {
      config.setRechargeEnergyYes(model.getRechargeEneryYesLocation());
    }
    if (model.getRechargeEnergyNoLocation() != null) {
      config.setRechargeEnergyNo(model.getRechargeEnergyNoLocation());
    }
    if (model.getEnergyLocationOnShop() != null) {
      config.setRechargeEnergy(model.getEnergyLocationOnShop());
    }
    if (model.getConfirmRechargeEnergyLoation() != null) {
      config.setConfirmRechargeEnergy(model.getConfirmRechargeEnergyLoation());
    }
    if (model.getAckRefillSuccessLocation() != null) {
      config.setAckRechargeEnergyOk(model.getAckRefillSuccessLocation());
    }
    if (model.getCloseRefillShopLocation() != null) {
      config.setCloseRechargeEnergy(model.getCloseRefillShopLocation());
    }
    if (model.getConfirmNetworkDelayLocation() != null) {
      config.setConfirmNetworkDelay(model.getConfirmNetworkDelayLocation());
    }
    if (model.getResendBattleInfoLocation() != null) {
      config.setResendBattleInfoX(model.getResendBattleInfoLocation());
    }
    if (model.getReplayBattleIndicator() != null) {
      config.setReplayBattleIndicator(model.getReplayBattleIndicator());
    }
    if (model.getStartBattleIndicator() != null) {
      config.setStartBattleIndicator(model.getStartBattleIndicator());
    }
    if (model.getBattleEndIndicator() != null) {
      config.setBattleEndIndicator(model.getBattleEndIndicator());
    }
    if (model.getRuneRewardIndiator() != null) {
      config.setRuneRewardIndiator(model.getRuneRewardIndiator());
    }
    if (model.getConfirmSellRuneIndicator() != null) {
      config.setConfirmSellRuneIndicator(model.getConfirmSellRuneIndicator());
    }
    if (model.getOtherRewardIndicator() != null) {
      config.setOtherRewardIndicator(model.getOtherRewardIndicator());
    }
    if (model.getManualAttackIndicator() != null) {
      config.setManualAttackIndicator(model.getManualAttackIndicator());
    }
    if (model.getNoEnergyIndicator() != null) {
      config.setNoEnergyIndicator(model.getNoEnergyIndicator());
    }
    if (model.getNetworkDelayIndicator() != null) {
      config.setNetworkDelayIndicator(model.getNetworkDelayIndicator());
    }
    if (model.getNetworkUnstableIndicator() != null) {
      config.setNetworkUnstableIndicator(model.getNetworkUnstableIndicator());
    }
    config.save();

    controller.unlaunchUI();
    final Controller homeController = ControllerRegistry.get(HomeController.class);
    homeController.launchUI();
  }

  private void reloadUIFromModel() {
    final AddProfileModel model = controller.getModel();
    final AddProfileUI ui = controller.getUI();

    ui.getTextField().setText(model.getProfileName());
    ui.getSpinner().setValue(model.getRefillTimes());
    ui.getReplayPointPicker().setData(model.getReplayBattleLocation());
    ui.getStartBattlePointPicker().setData(model.getStartBattleLocation());
    ui.getSellRunePointPicker().setData(model.getSellRuneLocation());
    ui.getSellRuneConfirmPointPicker().setData(model.getSellRuneConfirmLocation());
    ui.getGetRunePointPicker().setData(model.getGetRuneRewardLocation());
    ui.getGetRewardPointPicker().setData(model.getGetRewardLocation());
    ui.getAutoAttackPointPicker().setData(model.getEnableAutoAttackLocation());
    ui.getRechargeYesPointPicker().setData(model.getRechargeEneryYesLocation());
    ui.getRechargeNoPointPicker().setData(model.getRechargeEnergyNoLocation());
    ui.getEnergyShopPointPicker().setData(model.getEnergyLocationOnShop());
    ui.getConfirmRechargePointPicker().setData(model.getConfirmRechargeEnergyLoation());
    ui.getAckRefillPointPicker().setData(model.getAckRefillSuccessLocation());
    ui.getCloseShopPointPicker().setData(model.getCloseRefillShopLocation());
    ui.getNetworkDelayPointPicker().setData(model.getConfirmNetworkDelayLocation());
    ui.getResendBattleInfoPointPicker().setData(model.getResendBattleInfoLocation());

    ui.getReplayBattleBoxPicker().setData(model.getReplayBattleIndicator());
    ui.getStartBattleBoxPicker().setData(model.getStartBattleIndicator());
    ui.getEndBattleBoxPicker().setData(model.getBattleEndIndicator());
    ui.getRuneRewardBoxPicker().setData(model.getRuneRewardIndiator());
    ui.getConfirmSellRuneBoxPicker().setData(model.getConfirmSellRuneIndicator());
    ui.getOtherRewardBoxPicker().setData(model.getOtherRewardIndicator());
    ui.getManualAttBoxPicker().setData(model.getManualAttackIndicator());
    ui.getNoEnergyBoxPicker().setData(model.getNoEnergyIndicator());
    ui.getNetworkDelayBoxPicker().setData(model.getNetworkDelayIndicator());
    ui.getUnstableNetworkBoxPicker().setData(model.getNetworkUnstableIndicator());
  }
}
