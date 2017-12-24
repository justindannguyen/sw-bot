/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Observable;

import com.justin.swbot.game.GameConfig;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class AddProfileModel extends Observable {
  public static final String MODEL_LOADED = "MODEL_LOADED";

  private String profileName;
  private Point replayBattleLocation;
  private Point startBattleLocation;
  private Point confirmNetworkDelayLocation;
  private Point resendBattleInfoLocation;
  private Point enableAutoAttackLocation;
  private boolean randomClick;

  private int refillTimes;

  private boolean sellAllRune;
  private Point sellRuneLocation;
  private Point sellRuneConfirmLocation;
  private Point getRuneRewardLocation;
  private Point getRewardLocation;

  private Point rechargeEneryYesLocation;
  private Point rechargeEnergyNoLocation;
  private Point energyLocationOnShop;
  private Point confirmRechargeEnergyLoation;
  private Point ackRefillSuccessLocation;
  private Point closeRefillShopLocation;
  private BufferedImage replayBattleIndicator;
  private BufferedImage startBattleIndicator;

  private BufferedImage battleEndIndicator;
  private BufferedImage runeRewardIndiator;
  private BufferedImage confirmSellRuneIndicator;
  private BufferedImage otherRewardIndicator;
  private BufferedImage manualAttackIndicator;
  private BufferedImage noEnergyIndicator;
  private BufferedImage networkDelayIndicator;
  private BufferedImage networkUnstableIndicator;

  public Point getAckRefillSuccessLocation() {
    return ackRefillSuccessLocation;
  }

  public BufferedImage getBattleEndIndicator() {
    return battleEndIndicator;
  }

  public Point getCloseRefillShopLocation() {
    return closeRefillShopLocation;
  }

  public Point getConfirmNetworkDelayLocation() {
    return confirmNetworkDelayLocation;
  }

  public Point getConfirmRechargeEnergyLoation() {
    return confirmRechargeEnergyLoation;
  }

  public BufferedImage getConfirmSellRuneIndicator() {
    return confirmSellRuneIndicator;
  }

  public Point getEnableAutoAttackLocation() {
    return enableAutoAttackLocation;
  }

  public Point getEnergyLocationOnShop() {
    return energyLocationOnShop;
  }

  public Point getGetRewardLocation() {
    return getRewardLocation;
  }

  public Point getGetRuneRewardLocation() {
    return getRuneRewardLocation;
  }

  public BufferedImage getManualAttackIndicator() {
    return manualAttackIndicator;
  }

  public BufferedImage getNetworkDelayIndicator() {
    return networkDelayIndicator;
  }

  public BufferedImage getNetworkUnstableIndicator() {
    return networkUnstableIndicator;
  }

  public BufferedImage getNoEnergyIndicator() {
    return noEnergyIndicator;
  }

  public BufferedImage getOtherRewardIndicator() {
    return otherRewardIndicator;
  }

  public String getProfileName() {
    return profileName;
  }

  public Point getRechargeEnergyNoLocation() {
    return rechargeEnergyNoLocation;
  }

  public Point getRechargeEneryYesLocation() {
    return rechargeEneryYesLocation;
  }

  public int getRefillTimes() {
    return refillTimes;
  }

  public BufferedImage getReplayBattleIndicator() {
    return replayBattleIndicator;
  }

  public Point getReplayBattleLocation() {
    return replayBattleLocation;
  }

  public Point getResendBattleInfoLocation() {
    return resendBattleInfoLocation;
  }

  public BufferedImage getRuneRewardIndiator() {
    return runeRewardIndiator;
  }

  public Point getSellRuneConfirmLocation() {
    return sellRuneConfirmLocation;
  }

  public Point getSellRuneLocation() {
    return sellRuneLocation;
  }

  public BufferedImage getStartBattleIndicator() {
    return startBattleIndicator;
  }

  public Point getStartBattleLocation() {
    return startBattleLocation;
  }

  public boolean isRandomClick() {
    return randomClick;
  }

  public boolean isSellAllRune() {
    return sellAllRune;
  }

  public void loadData() {
    final GameConfig config = GameConfig.get();
    profileName = config.getProfileName();
    refillTimes = config.isEmpty() ? 0 : Integer.valueOf(config.getRefillTimes());
    replayBattleLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getReplayBattleX()),
        Integer.valueOf(config.getReplayBattleY()));
    startBattleLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getStartBattleX()),
        Integer.valueOf(config.getStartBattleY()));
    sellRuneLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getSellRuneLocationX()),
        Integer.valueOf(config.getSellRuneLocationY()));
    sellRuneConfirmLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getSellRuneConfirmationX()),
        Integer.valueOf(config.getSellRuneConfirmationY()));
    getRuneRewardLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getGetRuneLocationX()),
        Integer.valueOf(config.getGetRuneLocationY()));
    getRewardLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getGetRewardLocationX()),
        Integer.valueOf(config.getGetRewardLocationY()));
    enableAutoAttackLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getEnableAutoModeX()),
        Integer.valueOf(config.getEnableAutoModeY()));
    rechargeEneryYesLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getRechargeEnergyYesX()),
        Integer.valueOf(config.getRechargeEnergyYesY()));
    rechargeEnergyNoLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getRechargeEnergyNoX()),
        Integer.valueOf(config.getRechargeEnergyNoY()));
    energyLocationOnShop = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getRechargeEnergyX()),
        Integer.valueOf(config.getRechargeEnergyY()));
    confirmRechargeEnergyLoation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getConfirmRechargeEnergyX()),
        Integer.valueOf(config.getConfirmRechargeEnergyY()));
    ackRefillSuccessLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getAckRechargeEnergyOkX()),
        Integer.valueOf(config.getAckRechargeEnergyOkY()));
    closeRefillShopLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getCloseRechargeEnergyX()),
        Integer.valueOf(config.getCloseRechargeEnergyY()));
    confirmNetworkDelayLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getConfirmNetworkDelayX()),
        Integer.valueOf(config.getConfirmNetworkDelayY()));
    resendBattleInfoLocation = config.isEmpty() ? null
        : new Point(Integer.valueOf(config.getResendBattleInfoX()),
        Integer.valueOf(config.getResendBattleInfoY()));
    randomClick = config.isClickRandom();
    sellAllRune = config.isSellAllRune();

    replayBattleIndicator = config.getReplayBattleIndicator();
    startBattleIndicator = config.getStartBattleIndicator();
    battleEndIndicator = config.getBattleEndIndicator();
    runeRewardIndiator = config.getRuneRewardIndiator();
    confirmSellRuneIndicator = config.getConfirmSellRuneIndicator();
    otherRewardIndicator = config.getOtherRewardIndicator();
    manualAttackIndicator = config.getManualAttackIndicator();
    noEnergyIndicator = config.getNoEnergyIndicator();
    networkDelayIndicator = config.getNetworkDelayIndicator();
    networkUnstableIndicator = config.getNetworkUnstableIndicator();

    setChanged();
    notifyObservers(MODEL_LOADED);
  }

  public void setAckRefillSuccessLocation(final Point ackRefillSuccessLocation) {
    this.ackRefillSuccessLocation = ackRefillSuccessLocation;
  }

  public void setBattleEndIndicator(final BufferedImage battleEndIndicator) {
    this.battleEndIndicator = battleEndIndicator;
  }

  public void setCloseRefillShopLocation(final Point closeRefillShopLocation) {
    this.closeRefillShopLocation = closeRefillShopLocation;
  }

  public void setConfirmNetworkDelayLocation(final Point confirmNetworkDelayLocation) {
    this.confirmNetworkDelayLocation = confirmNetworkDelayLocation;
  }

  public void setConfirmRechargeEnergyLoation(final Point confirmRechargeEnergyLoation) {
    this.confirmRechargeEnergyLoation = confirmRechargeEnergyLoation;
  }

  public void setConfirmSellRuneIndicator(final BufferedImage confirmSellRuneIndicator) {
    this.confirmSellRuneIndicator = confirmSellRuneIndicator;
  }

  public void setEnableAutoAttackLocation(final Point enableAutoAttackLocation) {
    this.enableAutoAttackLocation = enableAutoAttackLocation;
  }

  public void setEnergyLocationOnShop(final Point energyLocationOnShop) {
    this.energyLocationOnShop = energyLocationOnShop;
  }

  public void setGetRewardLocation(final Point getRewardLocation) {
    this.getRewardLocation = getRewardLocation;
  }

  public void setGetRuneRewardLocation(final Point getRuneRewardLocation) {
    this.getRuneRewardLocation = getRuneRewardLocation;
  }

  public void setManualAttackIndicator(final BufferedImage manualAttackIndicator) {
    this.manualAttackIndicator = manualAttackIndicator;
  }

  public void setNetworkDelayIndicator(final BufferedImage networkDelayIndicator) {
    this.networkDelayIndicator = networkDelayIndicator;
  }

  public void setNetworkUnstableIndicator(final BufferedImage networkUnstableIndicator) {
    this.networkUnstableIndicator = networkUnstableIndicator;
  }

  public void setNoEnergyIndicator(final BufferedImage noEnergyIndicator) {
    this.noEnergyIndicator = noEnergyIndicator;
  }

  public void setOtherRewardIndicator(final BufferedImage otherRewardIndicator) {
    this.otherRewardIndicator = otherRewardIndicator;
  }

  public void setProfileName(final String profileName) {
    this.profileName = profileName;
  }

  public void setRandomClick(final boolean randomClick) {
    this.randomClick = randomClick;
  }

  public void setRechargeEnergyNoLocation(final Point rechargeEnergyNoLocation) {
    this.rechargeEnergyNoLocation = rechargeEnergyNoLocation;
  }

  public void setRechargeEneryYesLocation(final Point rechargeEneryYesLocation) {
    this.rechargeEneryYesLocation = rechargeEneryYesLocation;
  }

  public void setRefillTimes(final int refillTimes) {
    this.refillTimes = refillTimes;
  }

  public void setReplayBattleIndicator(final BufferedImage replayBattleIndicator) {
    this.replayBattleIndicator = replayBattleIndicator;
  }

  public void setReplayBattleLocation(final Point replayBattleLocation) {
    this.replayBattleLocation = replayBattleLocation;
  }

  public void setResendBattleInfoLocation(final Point resendBattleInfoLocation) {
    this.resendBattleInfoLocation = resendBattleInfoLocation;
  }

  public void setRuneRewardIndiator(final BufferedImage runeRewardIndiator) {
    this.runeRewardIndiator = runeRewardIndiator;
  }

  public void setSellAllRune(final boolean sellAllRune) {
    this.sellAllRune = sellAllRune;
  }

  public void setSellRuneConfirmLocation(final Point sellRuneConfirmLocation) {
    this.sellRuneConfirmLocation = sellRuneConfirmLocation;
  }

  public void setSellRuneLocation(final Point sellRuneLocation) {
    this.sellRuneLocation = sellRuneLocation;
  }

  public void setStartBattleIndicator(final BufferedImage startBattleIndicator) {
    this.startBattleIndicator = startBattleIndicator;
  }

  public void setStartBattleLocation(final Point startBattleLocation) {
    this.startBattleLocation = startBattleLocation;
  }
}
