/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class AddProfileModel extends Observable {
  private String profileName;
  private int refillTimes;
  private Point replayBattleLocation;
  private Point startBattleLocation;
  private Point sellRuneLocation;
  private Point sellRuneConfirmLocation;
  private Point getRuneRewardLocation;
  private Point getRewardLocation;
  private Point enableAutoAttackLocation;
  private Point rechargeEneryYesLocation;
  private Point rechargeEnergyNoLocation;
  private Point energyLocationOnShop;
  private Point confirmRechargeEnergyLoation;
  private Point ackRefillSuccessLocation;
  private Point closeRefillShopLocation;
  private Point confirmNetworkDelayLocation;
  private Point resendBattleInfoLocation;

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
