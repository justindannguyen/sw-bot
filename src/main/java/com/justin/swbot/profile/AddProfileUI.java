/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.justin.swbot.component.BoxPicker;
import com.justin.swbot.component.ImagePicker;
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
  private JPanel statusIndicatorTab;
  private JLabel lblReplayBattleIndicator;
  private ImagePicker replayBattleBoxPicker;
  private JLabel lblStartBattleIndicator;
  private ImagePicker startBattleBoxPicker;
  private JLabel lblBattleEndIndicator;
  private ImagePicker endBattleBoxPicker;
  private JLabel lblSellRuneIndiator;
  private ImagePicker runeRewardBoxPicker;
  private JLabel lblConfirmRune;
  private ImagePicker confirmSellRuneBoxPicker;
  private JLabel lblOtherRewardIndicator;
  private ImagePicker otherRewardBoxPicker;
  private JLabel lblManualAttackIndicator;
  private ImagePicker manualAttBoxPicker;
  private JLabel lblNoEnergyIndicator;
  private ImagePicker noEnergyBoxPicker;
  private JLabel lblNetworkDelayIndicator;
  private ImagePicker networkDelayBoxPicker;
  private JLabel lblUnstableNetworkIndicator;
  private ImagePicker unstableNetworkBoxPicker;
  private JButton createButton;
  private JButton cancelButton;
  private JTabbedPane tabbedPane;
  private JPanel commonTab;
  private JPanel runeTab;
  private JPanel refillTab;
  private JCheckBox sellAllRuneCheckbox;
  private JPanel panel;
  private JLabel lblRuneRules;
  private JCheckBox legendRuneCheckbox;
  private JCheckBox heroRuneCheckbox;
  private JCheckBox allRuneCheckbox;
  private JCheckBox sixStarRuneCheckBox;
  private ImagePicker sixStarRuneBoxPicker;
  private JCheckBox fiveStarRuneCheckBox;
  private ImagePicker fiveStarRuneBoxPicker;
  private JCheckBox grindPercentCheckbox;
  private JCheckBox randomClickCheckbox;
  private JCheckBox runLogCheckbox;
  private JLabel lblRareBoundary;
  private BoxPicker rareLevelBoxPicker;
  private BoxPicker grindGemBoxPicker;
  private JLabel lblGetStone;
  private PointPicker getStonePointPicker;
  private JLabel lblSellStone;
  private PointPicker sellStonePointPicker;
  private JLabel lblStoneRewardIndiator;
  private ImagePicker stoneRewardImagePicker;
  private JLabel lblInBattleIndicator;
  private ImagePicker inBattleImagePicker;
  private JLabel lblSellRareStone;
  private PointPicker sellStoneConfirmPointPicker;
  private JLabel lblConfirmSellStone;
  private ImagePicker confirmSellStoneImagePicker;
  private JLabel lblNotRevive;
  private PointPicker noRevivePointPicker;
  private JLabel lblReviveIndicator;
  private ImagePicker reviveImagePicker;
  private JLabel lblNotRechargeCrystals;
  private PointPicker notRechargeCrysPointPicker;
  private JLabel lblNoCrystalIndicator;
  private ImagePicker noCrysImagePicker;

  public AddProfileUI() {
    initGUI();

    setSize(new Dimension(650, 700));
    setPreferredSize(new Dimension(650, 700));
    setLocationRelativeTo(null);
  }

  public PointPicker getAckRefillPointPicker() {
    if (ackRefillPointPicker == null) {
      ackRefillPointPicker = new PointPicker();
    }
    return ackRefillPointPicker;
  }

  public JCheckBox getAllRuneCheckbox() {
    if (allRuneCheckbox == null) {
      allRuneCheckbox = new JCheckBox("Pick all (ignore other rules configuration if selected)");
    }
    return allRuneCheckbox;
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

  public ImagePicker getConfirmSellRuneBoxPicker() {
    if (confirmSellRuneBoxPicker == null) {
      confirmSellRuneBoxPicker = new ImagePicker();
    }
    return confirmSellRuneBoxPicker;
  }

  public ImagePicker getConfirmSellStoneImagePicker() {
    if (confirmSellStoneImagePicker == null) {
    	confirmSellStoneImagePicker = new ImagePicker();
    }
    return confirmSellStoneImagePicker;
  }

  public JButton getCreateButton() {
    if (createButton == null) {
      createButton = new JButton("Create");
    }
    return createButton;
  }

  public ImagePicker getEndBattleBoxPicker() {
    if (endBattleBoxPicker == null) {
      endBattleBoxPicker = new ImagePicker();
    }
    return endBattleBoxPicker;
  }

  public PointPicker getEnergyShopPointPicker() {
    if (energyShopPointPicker == null) {
      energyShopPointPicker = new PointPicker();
    }
    return energyShopPointPicker;
  }

  public ImagePicker getFiveStarRuneBoxPicker() {
    if (fiveStarRuneBoxPicker == null) {
      fiveStarRuneBoxPicker = new ImagePicker();
    }
    return fiveStarRuneBoxPicker;
  }

  public JCheckBox getFiveStarRuneCheckBox() {
    if (fiveStarRuneCheckBox == null) {
      fiveStarRuneCheckBox = new JCheckBox("Pick 5 star +");
    }
    return fiveStarRuneCheckBox;
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

  public PointPicker getGetStonePointPicker() {
    if (getStonePointPicker == null) {
      getStonePointPicker = new PointPicker();
    }
    return getStonePointPicker;
  }

  public BoxPicker getGrindGemBoxPicker() {
    if (grindGemBoxPicker == null) {
      grindGemBoxPicker = new BoxPicker();
    }
    return grindGemBoxPicker;
  }

  public JCheckBox getGrindPercentCheckbox() {
    if (grindPercentCheckbox == null) {
      grindPercentCheckbox = new JCheckBox("Pick stone/gem SPD,% only");
    }
    return grindPercentCheckbox;
  }

  public JCheckBox getHeroRuneCheckbox() {
    if (heroRuneCheckbox == null) {
      heroRuneCheckbox = new JCheckBox("Pick hero+");
    }
    return heroRuneCheckbox;
  }

  public ImagePicker getInBattleImagePicker() {
    if (inBattleImagePicker == null) {
      inBattleImagePicker = new ImagePicker();
    }
    return inBattleImagePicker;
  }

  public JCheckBox getLegendRuneCheckbox() {
    if (legendRuneCheckbox == null) {
      legendRuneCheckbox = new JCheckBox("Pick legend only");
    }
    return legendRuneCheckbox;
  }

  public ImagePicker getManualAttBoxPicker() {
    if (manualAttBoxPicker == null) {
      manualAttBoxPicker = new ImagePicker();
    }
    return manualAttBoxPicker;
  }

  public ImagePicker getNetworkDelayBoxPicker() {
    if (networkDelayBoxPicker == null) {
      networkDelayBoxPicker = new ImagePicker();
    }
    return networkDelayBoxPicker;
  }

  public PointPicker getNetworkDelayPointPicker() {
    if (networkDelayPointPicker == null) {
      networkDelayPointPicker = new PointPicker();
    }
    return networkDelayPointPicker;
  }

  public ImagePicker getNoCrysImagePicker() {
    if (noCrysImagePicker == null) {
    	noCrysImagePicker = new ImagePicker();
    }
    return noCrysImagePicker;
  }

  public ImagePicker getNoEnergyBoxPicker() {
    if (noEnergyBoxPicker == null) {
      noEnergyBoxPicker = new ImagePicker();
    }
    return noEnergyBoxPicker;
  }

  public PointPicker getNoRevivePointPicker() {
    if (noRevivePointPicker == null) {
    	noRevivePointPicker = new PointPicker();
    }
    return noRevivePointPicker;
  }

  public PointPicker getNotRechargeCrysPointPicker() {
    if (notRechargeCrysPointPicker == null) {
    	notRechargeCrysPointPicker = new PointPicker();
    }
    return notRechargeCrysPointPicker;
  }

  public ImagePicker getOtherRewardBoxPicker() {
    if (otherRewardBoxPicker == null) {
      otherRewardBoxPicker = new ImagePicker();
    }
    return otherRewardBoxPicker;
  }

  public JCheckBox getRandomClickCheckbox() {
    if (randomClickCheckbox == null) {
      randomClickCheckbox = new JCheckBox("Random Click");
    }
    return randomClickCheckbox;
  }

  public BoxPicker getRareLevelBoxPicker() {
    if (rareLevelBoxPicker == null) {
      rareLevelBoxPicker = new BoxPicker();
    }
    return rareLevelBoxPicker;
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

  public ImagePicker getReplayBattleBoxPicker() {
    if (replayBattleBoxPicker == null) {
      replayBattleBoxPicker = new ImagePicker();
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

  public ImagePicker getReviveImagePicker() {
    if (reviveImagePicker == null) {
      reviveImagePicker = new ImagePicker();
    }
    return reviveImagePicker;
  }

  public ImagePicker getRuneRewardBoxPicker() {
    if (runeRewardBoxPicker == null) {
      runeRewardBoxPicker = new ImagePicker();
    }
    return runeRewardBoxPicker;
  }

  public JCheckBox getRunLogCheckbox() {
    if (runLogCheckbox == null) {
      runLogCheckbox = new JCheckBox("Log");
    }
    return runLogCheckbox;
  }

  public JCheckBox getSellAllRuneCheckbox() {
    if (sellAllRuneCheckbox == null) {
      sellAllRuneCheckbox = new JCheckBox("Sell all runes & stones");
    }
    return sellAllRuneCheckbox;
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

  public PointPicker getSellStoneConfirmPointPicker() {
    if (sellStoneConfirmPointPicker == null) {
      sellStoneConfirmPointPicker = new PointPicker();
    }
    return sellStoneConfirmPointPicker;
  }

  public PointPicker getSellStonePointPicker() {
    if (sellStonePointPicker == null) {
      sellStonePointPicker = new PointPicker();
    }
    return sellStonePointPicker;
  }

  public ImagePicker getSixStarRuneBoxPicker() {
    if (sixStarRuneBoxPicker == null) {
      sixStarRuneBoxPicker = new ImagePicker();
    }
    return sixStarRuneBoxPicker;
  }

  public JCheckBox getSixStarRuneCheckbox() {
    if (sixStarRuneCheckBox == null) {
      sixStarRuneCheckBox = new JCheckBox("Pick 6 star only");
    }
    return sixStarRuneCheckBox;
  }

  public JSpinner getSpinner() {
    if (spinner == null) {
      spinner = new JSpinner();
      spinner.setModel(new SpinnerNumberModel(0, 0, 20, 1));
    }
    return spinner;
  }

  public ImagePicker getStartBattleBoxPicker() {
    if (startBattleBoxPicker == null) {
      startBattleBoxPicker = new ImagePicker();
    }
    return startBattleBoxPicker;
  }

  public PointPicker getStartBattlePointPicker() {
    if (startBattlePointPicker == null) {
      startBattlePointPicker = new PointPicker();
    }
    return startBattlePointPicker;
  }

  public ImagePicker getStoneRewardImagePicker() {
    if (stoneRewardImagePicker == null) {
      stoneRewardImagePicker = new ImagePicker();
    }
    return stoneRewardImagePicker;
  }

  public JTextField getTextField() {
    if (textField == null) {
      textField = new JTextField();
      textField.setColumns(10);
    }
    return textField;
  }

  public ImagePicker getUnstableNetworkBoxPicker() {
    if (unstableNetworkBoxPicker == null) {
      unstableNetworkBoxPicker = new ImagePicker();
    }
    return unstableNetworkBoxPicker;
  }

  private JPanel getCommonTab() {
    if (commonTab == null) {
      commonTab = new JPanel();
      commonTab.setLayout(new MigLayout("", "[150px][grow,fill]", "[][][][][][][][][]"));
      commonTab.add(getLblProfileName(), "cell 0 0");
      commonTab.add(getTextField(), "cell 1 0");
      commonTab.add(getLblReplayBattleLocation(), "cell 0 1");
      commonTab.add(getReplayPointPicker(), "cell 1 1");
      commonTab.add(getLblStartBattleLocation(), "cell 0 2");
      commonTab.add(getStartBattlePointPicker(), "cell 1 2");
      commonTab.add(getLblEnableAutoAttack(), "cell 0 3");
      commonTab.add(getAutoAttackPointPicker(), "cell 1 3");
      commonTab.add(getLblNetworkDelay(), "cell 0 4");
      commonTab.add(getNetworkDelayPointPicker(), "cell 1 4");
      commonTab.add(getLblUnstableNetwork(), "cell 0 5");
      commonTab.add(getResendBattleInfoPointPicker(), "cell 1 5");
      commonTab.add(getLblNotRevive(), "cell 0 6");
      commonTab.add(getNoRevivePointPicker(), "cell 1 6,grow");
      commonTab.add(getLblNotRechargeCrystals(), "cell 0 7");
      commonTab.add(getNotRechargeCrysPointPicker(), "cell 1 7,grow");
      commonTab.add(getRandomClickCheckbox(), "cell 0 8");
    }
    return commonTab;
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

  private JLabel getLblConfirmSellStone() {
    if (lblConfirmSellStone == null) {
    	lblConfirmSellStone = new JLabel("Confirm Sell Stone Indicator");
    }
    return lblConfirmSellStone;
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

  private JLabel getLblGetStone() {
    if (lblGetStone == null) {
      lblGetStone = new JLabel("Get Stone");
    }
    return lblGetStone;
  }

  private JLabel getLblInBattleIndicator() {
    if (lblInBattleIndicator == null) {
      lblInBattleIndicator = new JLabel("In Battle Indicator");
    }
    return lblInBattleIndicator;
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

  private JLabel getLblNoCrystalIndicator() {
    if (lblNoCrystalIndicator == null) {
    	lblNoCrystalIndicator = new JLabel("No Crystal Indicator");
    }
    return lblNoCrystalIndicator;
  }

  private JLabel getLblNoEnergyIndicator() {
    if (lblNoEnergyIndicator == null) {
      lblNoEnergyIndicator = new JLabel("No Energy Indicator");
    }
    return lblNoEnergyIndicator;
  }

  private JLabel getLblNotRechargeCrystals() {
    if (lblNotRechargeCrystals == null) {
    	lblNotRechargeCrystals = new JLabel("Not Recharge Crystals");
    }
    return lblNotRechargeCrystals;
  }

  private JLabel getLblNotRechargeEnergy() {
    if (lblNotRechargeEnergy == null) {
      lblNotRechargeEnergy = new JLabel("Recharge Energy NO");
      lblNotRechargeEnergy.setToolTipText(
          "When energy is not enough to start new battle, screen will display confirmation to recharge with 2 options: YES, NO. This location will be NO");
    }
    return lblNotRechargeEnergy;
  }

  private JLabel getLblNotRevive() {
    if (lblNotRevive == null) {
    	lblNotRevive = new JLabel("Not Revive");
    }
    return lblNotRevive;
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

  private JLabel getLblRareBoundary() {
    if (lblRareBoundary == null) {
      lblRareBoundary = new JLabel("Rare Level Area");
    }
    return lblRareBoundary;
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

  private JLabel getLblReviveIndicator() {
    if (lblReviveIndicator == null) {
    	lblReviveIndicator = new JLabel("Revive Indicator");
    }
    return lblReviveIndicator;
  }

  private JLabel getLblRuneRules() {
    if (lblRuneRules == null) {
      lblRuneRules = new JLabel("Rules apply when \"Sell all runes and stones\" are unchecked.");
    }
    return lblRuneRules;
  }

  private JLabel getLblSellRareStone() {
    if (lblSellRareStone == null) {
      lblSellRareStone = new JLabel("Sell Rare Stone Confirm");
    }
    return lblSellRareStone;
  }

  private JLabel getLblSellRuneConfirm() {
    if (lblSellRuneConfirm == null) {
      lblSellRuneConfirm = new JLabel("Sell Rare Rune Confirm");
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

  private JLabel getLblSellStone() {
    if (lblSellStone == null) {
      lblSellStone = new JLabel("Sell Stone");
    }
    return lblSellStone;
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

  private JLabel getLblStoneRewardIndiator() {
    if (lblStoneRewardIndiator == null) {
      lblStoneRewardIndiator = new JLabel("Stone Reward Indiator");
    }
    return lblStoneRewardIndiator;
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
      panel.setLayout(new MigLayout("", "[160px][grow,fill]", "[][][][][][grow][]"));
      panel.add(getAllRuneCheckbox(), "cell 0 0 2 1");
      panel.add(getLegendRuneCheckbox(), "cell 0 1");
      panel.add(getHeroRuneCheckbox(), "cell 0 2");
      panel.add(getSixStarRuneCheckbox(), "flowy,cell 0 3");
      panel.add(getSixStarRuneBoxPicker(), "cell 1 3,grow");
      panel.add(getFiveStarRuneCheckBox(), "cell 0 4");
      panel.add(getFiveStarRuneBoxPicker(), "cell 1 4,grow");
      panel.add(getGrindPercentCheckbox(), "cell 0 5");
      panel.add(getGrindGemBoxPicker(), "cell 1 5,grow");
      panel.add(getRunLogCheckbox(), "cell 0 6");
    }
    return panel;
  }

  private JPanel getRefillTab() {
    if (refillTab == null) {
      refillTab = new JPanel();
      refillTab.setLayout(new MigLayout("", "[150px][grow,fill]", "[][][][][][][]"));
      refillTab.add(getLblRefillTimes(), "cell 0 0");
      refillTab.add(getSpinner(), "cell 1 0");
      refillTab.add(getLblRechargeEnergyLocation(), "cell 0 1");
      refillTab.add(getRechargeYesPointPicker(), "cell 1 1");
      refillTab.add(getLblNotRechargeEnergy(), "cell 0 2");
      refillTab.add(getRechargeNoPointPicker(), "cell 1 2");
      refillTab.add(getLblEnergyOptionLocation(), "cell 0 3");
      refillTab.add(getEnergyShopPointPicker(), "cell 1 3");
      refillTab.add(getLblConfirmRechargeEnergy(), "cell 0 4");
      refillTab.add(getConfirmRechargePointPicker(), "cell 1 4");
      refillTab.add(getLblAckRechargeSuccessful(), "cell 0 5");
      refillTab.add(getAckRefillPointPicker(), "cell 1 5");
      refillTab.add(getLblCloseRefillShop(), "cell 0 6");
      refillTab.add(getCloseShopPointPicker(), "cell 1 6");
    }
    return refillTab;
  }

  private JPanel getRuneTab() {
    if (runeTab == null) {
      runeTab = new JPanel();
      runeTab.setLayout(new MigLayout("", "[170px][grow,fill]",
          "[][][grow][][grow][][grow][][grow][][grow,fill]"));
      runeTab.add(getSellAllRuneCheckbox(), "cell 0 0");
      runeTab.add(getLblSellRuneLocation(), "cell 0 1");
      runeTab.add(getSellRunePointPicker(), "cell 1 1");
      runeTab.add(getLblSellStone(), "cell 0 2");
      runeTab.add(getSellStonePointPicker(), "cell 1 2,grow");
      runeTab.add(getLblSellRuneConfirm(), "cell 0 3");
      runeTab.add(getSellRuneConfirmPointPicker(), "cell 1 3");
      runeTab.add(getLblSellRareStone(), "cell 0 4");
      runeTab.add(getSellStoneConfirmPointPicker(), "cell 1 4,grow");
      runeTab.add(getLblGetRuneLocation(), "cell 0 5");
      runeTab.add(getGetRunePointPicker(), "cell 1 5");
      runeTab.add(getLblGetStone(), "cell 0 6");
      runeTab.add(getGetStonePointPicker(), "cell 1 6,grow");
      runeTab.add(getLblGetRewardLocation(), "cell 0 7");
      runeTab.add(getGetRewardPointPicker(), "cell 1 7");
      runeTab.add(getLblRareBoundary(), "cell 0 8");
      runeTab.add(getRareLevelBoxPicker(), "cell 1 8,grow");
      runeTab.add(getLblRuneRules(), "cell 0 9 2 1");
      runeTab.add(getPanel(), "cell 0 10 2 1,grow");
    }
    return runeTab;
  }

  private JPanel getStatusIndicatorTab() {
    if (statusIndicatorTab == null) {
      statusIndicatorTab = new JPanel();
      statusIndicatorTab
          .setLayout(new MigLayout("", "[150px][grow,fill]", "[][][][][][][][][][][][][][][]"));
      statusIndicatorTab.add(getLblReplayBattleIndicator(), "cell 0 0");
      statusIndicatorTab.add(getReplayBattleBoxPicker(), "cell 1 0,grow");
      statusIndicatorTab.add(getLblStartBattleIndicator(), "cell 0 1");
      statusIndicatorTab.add(getStartBattleBoxPicker(), "cell 1 1,grow");
      statusIndicatorTab.add(getLblBattleEndIndicator(), "cell 0 2");
      statusIndicatorTab.add(getEndBattleBoxPicker(), "cell 1 2,grow");
      statusIndicatorTab.add(getLblSellRuneIndiator(), "cell 0 3");
      statusIndicatorTab.add(getRuneRewardBoxPicker(), "cell 1 3,grow");
      statusIndicatorTab.add(getLblStoneRewardIndiator(), "cell 0 4");
      statusIndicatorTab.add(getStoneRewardImagePicker(), "cell 1 4,grow");
      statusIndicatorTab.add(getLblConfirmRune(), "cell 0 5");
      statusIndicatorTab.add(getConfirmSellRuneBoxPicker(), "cell 1 5,grow");
      statusIndicatorTab.add(getLblConfirmSellStone(), "cell 0 6");
      statusIndicatorTab.add(getConfirmSellStoneImagePicker(), "cell 1 6,grow");
      statusIndicatorTab.add(getLblOtherRewardIndicator(), "cell 0 7");
      statusIndicatorTab.add(getOtherRewardBoxPicker(), "cell 1 7,grow");
      statusIndicatorTab.add(getLblManualAttackIndicator(), "cell 0 8");
      statusIndicatorTab.add(getManualAttBoxPicker(), "cell 1 8,grow");
      statusIndicatorTab.add(getLblNoEnergyIndicator(), "cell 0 9");
      statusIndicatorTab.add(getNoEnergyBoxPicker(), "cell 1 9,grow");
      statusIndicatorTab.add(getLblNetworkDelayIndicator(), "cell 0 10");
      statusIndicatorTab.add(getNetworkDelayBoxPicker(), "cell 1 10,grow");
      statusIndicatorTab.add(getLblUnstableNetworkIndicator(), "cell 0 11");
      statusIndicatorTab.add(getUnstableNetworkBoxPicker(), "cell 1 11,grow");
      statusIndicatorTab.add(getLblInBattleIndicator(), "cell 0 12");
      statusIndicatorTab.add(getInBattleImagePicker(), "cell 1 12,grow");
      statusIndicatorTab.add(getLblReviveIndicator(), "cell 0 13");
      statusIndicatorTab.add(getReviveImagePicker(), "cell 1 13,grow");
      statusIndicatorTab.add(getLblNoCrystalIndicator(), "cell 0 14");
      statusIndicatorTab.add(getNoCrysImagePicker(), "cell 1 14,grow");
    }
    return statusIndicatorTab;
  }

  private JTabbedPane getTabbedPane() {
    if (tabbedPane == null) {
      tabbedPane = new JTabbedPane(SwingConstants.TOP);
      tabbedPane.addTab("General", null, getCommonTab(), null);
      tabbedPane.addTab("Runes & Stones", null, getRuneTab(), null);
      tabbedPane.addTab("Refill", null, getRefillTab(), null);
      tabbedPane.addTab("Game Status", null, getStatusIndicatorTab(), null);
    }
    return tabbedPane;
  }

  private void initGUI() {
    setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Profile Editor");
    getContentPane().setLayout(new MigLayout("", "[400px,grow,fill]", "[grow][]"));
    getContentPane().add(getTabbedPane(), "cell 0 0,grow");
    getContentPane().add(getCreateButton(), "flowx,cell 0 1");
    getContentPane().add(getCancelButton(), "cell 0 1");
  }
}
