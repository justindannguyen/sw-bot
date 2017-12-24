/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.justin.swbot.component.BoxPicker;
import com.justin.swbot.component.PointPicker;

import net.miginfocom.swing.MigLayout;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class AddProfileUI extends JFrame {
  private static final long serialVersionUID = 1L;

  private JLabel lblProfileName;
  private JTextField textField;
  private JLabel lblRefillTimes;
  private JSpinner spinner;
  private JLabel lblReplayBattleLocation;
  private JLabel lblStartBattleLocation;
  private PointPicker replayPointPicker;
  private PointPicker startBattlePointPicker;
  private JPanel pointConfigurationPanel;
  private JLabel lblSellRuneLocation;
  private PointPicker sellRunePointPicker;
  private JLabel lblGetRuneLocation;
  private PointPicker getRunePointPicker;
  private JLabel lblGetRewardLocation;
  private PointPicker getRewardPointPicker;
  private JLabel lblEnableAutoAttack;
  private PointPicker autoAttackPointPicker;
  private JLabel lblSellRuneConfirm;
  private PointPicker sellRuneConfirmPointPicker;
  private JLabel lblRechargeEnergyLocation;
  private PointPicker rechargeYesPointPicker;
  private JLabel lblNotRechargeEnergy;
  private PointPicker rechargeNoPointPicker;
  private JLabel lblEnergyOptionLocation;
  private PointPicker energyShopPointPicker;
  private JLabel lblConfirmRechargeEnergy;
  private PointPicker confirmRechargePointPicker;
  private JLabel lblAckRechargeSuccessful;
  private PointPicker ackRefillPointPicker;
  private JLabel lblCloseRefillShop;
  private PointPicker closeShopPointPicker;
  private JLabel lblNetworkDelay;
  private JLabel lblUnstableNetwork;
  private PointPicker networkDelayPointPicker;
  private PointPicker resendBattleInfoPointPicker;
  private JPanel panel;
  private JLabel lblReplayBattleIndicator;
  private BoxPicker replayBattleBoxPicker;
  private JLabel lblStartBattleIndicator;
  private BoxPicker startBattleBoxPicker;
  private JLabel lblBattleEndIndicator;
  private BoxPicker endBattleBoxPicker;
  private JLabel lblSellRuneIndiator;
  private BoxPicker runeRewardBoxPicker;
  private JLabel lblConfirmRune;
  private BoxPicker confirmSellRuneBoxPicker;
  private JLabel lblOtherRewardIndicator;
  private BoxPicker otherRewardBoxPicker;
  private JLabel lblManualAttackIndicator;
  private BoxPicker manualAttBoxPicker;
  private JLabel lblNoEnergyIndicator;
  private BoxPicker noEnergyBoxPicker;
  private JLabel lblNetworkDelayIndicator;
  private BoxPicker networkDelayBoxPicker;
  private JLabel lblUnstableNetworkIndicator;
  private BoxPicker unstableNetworkBoxPicker;
  private JButton createButton;
  private JButton cancelButton;

  public AddProfileUI() {
    initGUI();
  }

  public PointPicker getAckRefillPointPicker() {
    if (ackRefillPointPicker == null) {
      ackRefillPointPicker = new PointPicker();
    }
    return ackRefillPointPicker;
  }

  public PointPicker getAutoAttackPointPicker() {
    if (autoAttackPointPicker == null) {
      autoAttackPointPicker = new PointPicker();
    }
    return autoAttackPointPicker;
  }

  public JButton getCancelButton() {
    if (cancelButton == null) {
      cancelButton = new JButton("Cancel");
    }
    return cancelButton;
  }

  public PointPicker getCloseShopPointPicker() {
    if (closeShopPointPicker == null) {
      closeShopPointPicker = new PointPicker();
    }
    return closeShopPointPicker;
  }

  public PointPicker getConfirmRechargePointPicker() {
    if (confirmRechargePointPicker == null) {
      confirmRechargePointPicker = new PointPicker();
    }
    return confirmRechargePointPicker;
  }

  public BoxPicker getConfirmSellRuneBoxPicker() {
    if (confirmSellRuneBoxPicker == null) {
      confirmSellRuneBoxPicker = new BoxPicker();
    }
    return confirmSellRuneBoxPicker;
  }

  public JButton getCreateButton() {
    if (createButton == null) {
      createButton = new JButton("Create");
    }
    return createButton;
  }

  public BoxPicker getEndBattleBoxPicker() {
    if (endBattleBoxPicker == null) {
      endBattleBoxPicker = new BoxPicker();
    }
    return endBattleBoxPicker;
  }

  public PointPicker getEnergyShopPointPicker() {
    if (energyShopPointPicker == null) {
      energyShopPointPicker = new PointPicker();
    }
    return energyShopPointPicker;
  }

  public PointPicker getGetRewardPointPicker() {
    if (getRewardPointPicker == null) {
      getRewardPointPicker = new PointPicker();
    }
    return getRewardPointPicker;
  }

  public PointPicker getGetRunePointPicker() {
    if (getRunePointPicker == null) {
      getRunePointPicker = new PointPicker();
    }
    return getRunePointPicker;
  }

  public BoxPicker getManualAttBoxPicker() {
    if (manualAttBoxPicker == null) {
      manualAttBoxPicker = new BoxPicker();
    }
    return manualAttBoxPicker;
  }

  public BoxPicker getNetworkDelayBoxPicker() {
    if (networkDelayBoxPicker == null) {
      networkDelayBoxPicker = new BoxPicker();
    }
    return networkDelayBoxPicker;
  }

  public PointPicker getNetworkDelayPointPicker() {
    if (networkDelayPointPicker == null) {
      networkDelayPointPicker = new PointPicker();
    }
    return networkDelayPointPicker;
  }

  public BoxPicker getNoEnergyBoxPicker() {
    if (noEnergyBoxPicker == null) {
      noEnergyBoxPicker = new BoxPicker();
    }
    return noEnergyBoxPicker;
  }

  public BoxPicker getOtherRewardBoxPicker() {
    if (otherRewardBoxPicker == null) {
      otherRewardBoxPicker = new BoxPicker();
    }
    return otherRewardBoxPicker;
  }

  public PointPicker getRechargeNoPointPicker() {
    if (rechargeNoPointPicker == null) {
      rechargeNoPointPicker = new PointPicker();
    }
    return rechargeNoPointPicker;
  }

  public PointPicker getRechargeYesPointPicker() {
    if (rechargeYesPointPicker == null) {
      rechargeYesPointPicker = new PointPicker();
    }
    return rechargeYesPointPicker;
  }

  public BoxPicker getReplayBattleBoxPicker() {
    if (replayBattleBoxPicker == null) {
      replayBattleBoxPicker = new BoxPicker();
    }
    return replayBattleBoxPicker;
  }

  public PointPicker getReplayPointPicker() {
    if (replayPointPicker == null) {
      replayPointPicker = new PointPicker();
    }
    return replayPointPicker;
  }

  public PointPicker getResendBattleInfoPointPicker() {
    if (resendBattleInfoPointPicker == null) {
      resendBattleInfoPointPicker = new PointPicker();
    }
    return resendBattleInfoPointPicker;
  }

  public BoxPicker getRuneRewardBoxPicker() {
    if (runeRewardBoxPicker == null) {
      runeRewardBoxPicker = new BoxPicker();
    }
    return runeRewardBoxPicker;
  }

  public PointPicker getSellRuneConfirmPointPicker() {
    if (sellRuneConfirmPointPicker == null) {
      sellRuneConfirmPointPicker = new PointPicker();
    }
    return sellRuneConfirmPointPicker;
  }

  public PointPicker getSellRunePointPicker() {
    if (sellRunePointPicker == null) {
      sellRunePointPicker = new PointPicker();
    }
    return sellRunePointPicker;
  }

  public JSpinner getSpinner() {
    if (spinner == null) {
      spinner = new JSpinner();
      spinner.setModel(new SpinnerNumberModel(0, 0, 20, 1));
    }
    return spinner;
  }

  public BoxPicker getStartBattleBoxPicker() {
    if (startBattleBoxPicker == null) {
      startBattleBoxPicker = new BoxPicker();
    }
    return startBattleBoxPicker;
  }

  public PointPicker getStartBattlePointPicker() {
    if (startBattlePointPicker == null) {
      startBattlePointPicker = new PointPicker();
    }
    return startBattlePointPicker;
  }

  public JTextField getTextField() {
    if (textField == null) {
      textField = new JTextField();
      textField.setColumns(10);
    }
    return textField;
  }

  public BoxPicker getUnstableNetworkBoxPicker() {
    if (unstableNetworkBoxPicker == null) {
      unstableNetworkBoxPicker = new BoxPicker();
    }
    return unstableNetworkBoxPicker;
  }

  private JLabel getLblAckRechargeSuccessful() {
    if (lblAckRechargeSuccessful == null) {
      lblAckRechargeSuccessful = new JLabel("Ack Refill Successful");
      lblAckRechargeSuccessful.setToolTipText(
          "After refill energy successful, a screen with OK button will be displayed. This will be OK location");
    }
    return lblAckRechargeSuccessful;
  }

  private JLabel getLblBattleEndIndicator() {
    if (lblBattleEndIndicator == null) {
      lblBattleEndIndicator = new JLabel("Battle End Indicator");
    }
    return lblBattleEndIndicator;
  }

  private JLabel getLblCloseRefillShop() {
    if (lblCloseRefillShop == null) {
      lblCloseRefillShop = new JLabel("Close Refill Shop");
      lblCloseRefillShop.setToolTipText("On refill shop, this is the point of CLOSE button");
    }
    return lblCloseRefillShop;
  }

  private JLabel getLblConfirmRechargeEnergy() {
    if (lblConfirmRechargeEnergy == null) {
      lblConfirmRechargeEnergy = new JLabel("Confirm Recharge Energy");
      lblConfirmRechargeEnergy.setToolTipText(
          "On refill shop, this location will be used to confirm YES to use 30 crystal to refill.");
    }
    return lblConfirmRechargeEnergy;
  }

  private JLabel getLblConfirmRune() {
    if (lblConfirmRune == null) {
      lblConfirmRune = new JLabel("Confirm Sell Rune Indicator");
    }
    return lblConfirmRune;
  }

  private JLabel getLblEnableAutoAttack() {
    if (lblEnableAutoAttack == null) {
      lblEnableAutoAttack = new JLabel("Enable Auto Attack");
      lblEnableAutoAttack.setToolTipText("Play icon button to enable auto attack.");
    }
    return lblEnableAutoAttack;
  }

  private JLabel getLblEnergyOptionLocation() {
    if (lblEnergyOptionLocation == null) {
      lblEnergyOptionLocation = new JLabel("Energy Location on Shop");
      lblEnergyOptionLocation
          .setToolTipText("On refill shop, this is the location to refill energy");
    }
    return lblEnergyOptionLocation;
  }

  private JLabel getLblGetRewardLocation() {
    if (lblGetRewardLocation == null) {
      lblGetRewardLocation = new JLabel("Get Reward");
      lblGetRewardLocation.setToolTipText(
          "After battle end, this point will be used to get other reward ather than runes. This screen normally have only GET option");
    }
    return lblGetRewardLocation;
  }

  private JLabel getLblGetRuneLocation() {
    if (lblGetRuneLocation == null) {
      lblGetRuneLocation = new JLabel("Get Rune");
      lblGetRuneLocation.setToolTipText(
          "After battle end, this point will be used to get the rune. This screen normally has 2 options e.g. GET or SELL");
    }
    return lblGetRuneLocation;
  }

  private JLabel getLblManualAttackIndicator() {
    if (lblManualAttackIndicator == null) {
      lblManualAttackIndicator = new JLabel("Manual Attack Indicator");
    }
    return lblManualAttackIndicator;
  }

  private JLabel getLblNetworkDelay() {
    if (lblNetworkDelay == null) {
      lblNetworkDelay = new JLabel("Network Delay");
      lblNetworkDelay.setToolTipText(
          "After the battle end, if there is no networks then new screen will be shown with OK option. This is location of OK button");
    }
    return lblNetworkDelay;
  }

  private JLabel getLblNetworkDelayIndicator() {
    if (lblNetworkDelayIndicator == null) {
      lblNetworkDelayIndicator = new JLabel("Network Delay Indicator");
    }
    return lblNetworkDelayIndicator;
  }

  private JLabel getLblNoEnergyIndicator() {
    if (lblNoEnergyIndicator == null) {
      lblNoEnergyIndicator = new JLabel("No Energy Indicator");
    }
    return lblNoEnergyIndicator;
  }

  private JLabel getLblNotRechargeEnergy() {
    if (lblNotRechargeEnergy == null) {
      lblNotRechargeEnergy = new JLabel("Recharge Energy NO");
      lblNotRechargeEnergy.setToolTipText(
          "When energy is not enough to start new battle, screen will display confirmation to recharge with 2 options: YES, NO. This location will be NO");
    }
    return lblNotRechargeEnergy;
  }

  private JLabel getLblOtherRewardIndicator() {
    if (lblOtherRewardIndicator == null) {
      lblOtherRewardIndicator = new JLabel("Other Reward Indicator");
    }
    return lblOtherRewardIndicator;
  }

  private JLabel getLblProfileName() {
    if (lblProfileName == null) {
      lblProfileName = new JLabel("Profile Name*");
    }
    return lblProfileName;
  }

  private JLabel getLblRechargeEnergyLocation() {
    if (lblRechargeEnergyLocation == null) {
      lblRechargeEnergyLocation = new JLabel("Recharge Energy YES");
      lblRechargeEnergyLocation.setToolTipText(
          "When energy is not enough to start new battle, screen will display confirmation to recharge with 2 options: YES, NO. This location will be YES");
    }
    return lblRechargeEnergyLocation;
  }

  private JLabel getLblRefillTimes() {
    if (lblRefillTimes == null) {
      lblRefillTimes = new JLabel("Refill Times");
      lblRefillTimes.setToolTipText(
          "Indicate number of times that the bot will use crystal to refill energy. Put 0 only start the battle when energy is available");
    }
    return lblRefillTimes;
  }

  private JLabel getLblReplayBattleIndicator() {
    if (lblReplayBattleIndicator == null) {
      lblReplayBattleIndicator = new JLabel("Replay Battle Indicator");
      lblReplayBattleIndicator.setToolTipText(
          "After the battle end, A screen with REPLAY and NO option. This will be the unique element to identify the screen");
    }
    return lblReplayBattleIndicator;
  }

  private JLabel getLblReplayBattleLocation() {
    if (lblReplayBattleLocation == null) {
      lblReplayBattleLocation = new JLabel("Replay Battle");
      lblReplayBattleLocation.setToolTipText(
          "After the battle, screen will display REPLAY or NO, this point is used to replay battle.");
    }
    return lblReplayBattleLocation;
  }

  private JLabel getLblSellRuneConfirm() {
    if (lblSellRuneConfirm == null) {
      lblSellRuneConfirm = new JLabel("Sell 5* Rune Confirm");
      lblSellRuneConfirm.setToolTipText(
          "When 5* or +9 rune is being sold, it asks for confirmation. This will be YES");
    }
    return lblSellRuneConfirm;
  }

  private JLabel getLblSellRuneIndiator() {
    if (lblSellRuneIndiator == null) {
      lblSellRuneIndiator = new JLabel("Rune Reward Indicator");
    }
    return lblSellRuneIndiator;
  }

  private JLabel getLblSellRuneLocation() {
    if (lblSellRuneLocation == null) {
      lblSellRuneLocation = new JLabel("Sell Rune");
      lblSellRuneLocation.setToolTipText(
          "After battle end, this point will be used to sell the rune. This screen normally has 2 options e.g. GET or SELL");
    }
    return lblSellRuneLocation;
  }

  private JLabel getLblStartBattleIndicator() {
    if (lblStartBattleIndicator == null) {
      lblStartBattleIndicator = new JLabel("Start Battle Indicator");
      lblStartBattleIndicator.setToolTipText(
          "After replay battle is selected, A screen with START BATTLE and CANCEL option. This will be the unique element to identify the screen");
    }
    return lblStartBattleIndicator;
  }

  private JLabel getLblStartBattleLocation() {
    if (lblStartBattleLocation == null) {
      lblStartBattleLocation = new JLabel("Start Battle");
      lblStartBattleLocation
          .setToolTipText("After monster selection, this point is used to click START BATTLE");
    }
    return lblStartBattleLocation;
  }

  private JLabel getLblUnstableNetwork() {
    if (lblUnstableNetwork == null) {
      lblUnstableNetwork = new JLabel("Unstable Network, Resend?");
      lblUnstableNetwork.setToolTipText(
          "After click start battle button, if there is no networks then new screen will be shown with RESEND and NO option. This is location of RESEND button");
    }
    return lblUnstableNetwork;
  }

  private JLabel getLblUnstableNetworkIndicator() {
    if (lblUnstableNetworkIndicator == null) {
      lblUnstableNetworkIndicator = new JLabel("Unstable Network Indicator");
    }
    return lblUnstableNetworkIndicator;
  }

  private JPanel getPanel() {
    if (panel == null) {
      panel = new JPanel();
      panel.setBorder(new TitledBorder(null, "Game Status Configuration (hover text for usage)",
          TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel.setLayout(new MigLayout("", "[150px][grow,fill]", "[][][][][][][][][][]"));
      panel.add(getLblReplayBattleIndicator(), "cell 0 0");
      panel.add(getReplayBattleBoxPicker(), "cell 1 0,grow");
      panel.add(getLblStartBattleIndicator(), "cell 0 1");
      panel.add(getStartBattleBoxPicker(), "cell 1 1,grow");
      panel.add(getLblBattleEndIndicator(), "cell 0 2");
      panel.add(getEndBattleBoxPicker(), "cell 1 2,grow");
      panel.add(getLblSellRuneIndiator(), "cell 0 3");
      panel.add(getRuneRewardBoxPicker(), "cell 1 3,grow");
      panel.add(getLblConfirmRune(), "cell 0 4");
      panel.add(getConfirmSellRuneBoxPicker(), "cell 1 4,grow");
      panel.add(getLblOtherRewardIndicator(), "cell 0 5");
      panel.add(getOtherRewardBoxPicker(), "cell 1 5,grow");
      panel.add(getLblManualAttackIndicator(), "cell 0 6");
      panel.add(getManualAttBoxPicker(), "cell 1 6,grow");
      panel.add(getLblNoEnergyIndicator(), "cell 0 7");
      panel.add(getNoEnergyBoxPicker(), "cell 1 7,grow");
      panel.add(getLblNetworkDelayIndicator(), "cell 0 8");
      panel.add(getNetworkDelayBoxPicker(), "cell 1 8,grow");
      panel.add(getLblUnstableNetworkIndicator(), "cell 0 9");
      panel.add(getUnstableNetworkBoxPicker(), "cell 1 9,grow");
    }
    return panel;
  }

  private JPanel getPointConfigurationPanel() {
    if (pointConfigurationPanel == null) {
      pointConfigurationPanel = new JPanel();
      pointConfigurationPanel.setBorder(new TitledBorder(
          new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
              new Color(160, 160, 160)),
          "Points Configuration (hover text for usage)", TitledBorder.LEADING, TitledBorder.TOP,
          null, new Color(0, 0, 0)));
      pointConfigurationPanel
          .setLayout(new MigLayout("", "[150px][grow,fill]", "[][][][][][][][][][][][][][][]"));
      pointConfigurationPanel.add(getLblReplayBattleLocation(), "cell 0 0");
      pointConfigurationPanel.add(getReplayPointPicker(), "cell 1 0");
      pointConfigurationPanel.add(getLblStartBattleLocation(), "cell 0 1");
      pointConfigurationPanel.add(getStartBattlePointPicker(), "cell 1 1");
      pointConfigurationPanel.add(getLblSellRuneLocation(), "cell 0 2");
      pointConfigurationPanel.add(getSellRunePointPicker(), "cell 1 2,grow");
      pointConfigurationPanel.add(getLblSellRuneConfirm(), "cell 0 3");
      pointConfigurationPanel.add(getSellRuneConfirmPointPicker(), "cell 1 3,grow");
      pointConfigurationPanel.add(getLblGetRuneLocation(), "cell 0 4");
      pointConfigurationPanel.add(getGetRunePointPicker(), "cell 1 4,grow");
      pointConfigurationPanel.add(getLblGetRewardLocation(), "cell 0 5");
      pointConfigurationPanel.add(getGetRewardPointPicker(), "cell 1 5,grow");
      pointConfigurationPanel.add(getLblEnableAutoAttack(), "cell 0 6");
      pointConfigurationPanel.add(getAutoAttackPointPicker(), "cell 1 6,grow");
      pointConfigurationPanel.add(getLblRechargeEnergyLocation(), "cell 0 7");
      pointConfigurationPanel.add(getRechargeYesPointPicker(), "cell 1 7,grow");
      pointConfigurationPanel.add(getLblNotRechargeEnergy(), "cell 0 8");
      pointConfigurationPanel.add(getRechargeNoPointPicker(), "cell 1 8,grow");
      pointConfigurationPanel.add(getLblEnergyOptionLocation(), "cell 0 9");
      pointConfigurationPanel.add(getEnergyShopPointPicker(), "cell 1 9,grow");
      pointConfigurationPanel.add(getLblConfirmRechargeEnergy(), "cell 0 10");
      pointConfigurationPanel.add(getConfirmRechargePointPicker(), "cell 1 10,grow");
      pointConfigurationPanel.add(getLblAckRechargeSuccessful(), "cell 0 11");
      pointConfigurationPanel.add(getAckRefillPointPicker(), "cell 1 11,grow");
      pointConfigurationPanel.add(getLblCloseRefillShop(), "cell 0 12");
      pointConfigurationPanel.add(getCloseShopPointPicker(), "cell 1 12,grow");
      pointConfigurationPanel.add(getLblNetworkDelay(), "cell 0 13");
      pointConfigurationPanel.add(getNetworkDelayPointPicker(), "cell 1 13,grow");
      pointConfigurationPanel.add(getLblUnstableNetwork(), "cell 0 14");
      pointConfigurationPanel.add(getResendBattleInfoPointPicker(), "cell 1 14,grow");
    }
    return pointConfigurationPanel;
  }

  private void initGUI() {
    setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
    setPreferredSize(new Dimension(800, 800));
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Profile Editor");
    getContentPane()
        .setLayout(new MigLayout("", "[400px,grow,fill][450px,grow,fill]", "[][][grow][]"));
    getContentPane().add(getLblProfileName(), "flowx,cell 0 0");
    getContentPane().add(getLblRefillTimes(), "flowx,cell 1 0");
    getContentPane().add(getPointConfigurationPanel(), "cell 0 2,grow");
    getContentPane().add(getTextField(), "cell 0 0");
    getContentPane().add(getPanel(), "cell 1 2,grow");
    getContentPane().add(getSpinner(), "cell 1 0");
    getContentPane().add(getCreateButton(), "flowx,cell 1 3");
    getContentPane().add(getCancelButton(), "cell 1 3");
  }
}
