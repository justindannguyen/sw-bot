/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import com.justin.swbot.game.Profile;
import com.justin.swbot.game.indicator.Indicator;
import lombok.Getter;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class AddProfileModel extends Observable {
  public static final String MODEL_LOADED = "MODEL_LOADED";

  @Getter
  private Profile profile;
  private String profileName;
  private Point replayBattleLocation;
  private Point startBattleLocation;
  private Point confirmNetworkDelayLocation;
  private Point resendBattleInfoLocation;
  private Point enableAutoAttackLocation;
  private boolean randomClick;
  private Point reviveNoLocation;
  private Point rechargeCrysNoLocation;
  private BufferedImage noCrysIndicator;

  private boolean sellAllRune;
  private Point sellRuneLocation;
  private Point sellStoneLocation;
  private Point sellRuneConfirmLocation;
  private Point getRuneRewardLocation;
  private Point getStoneRewardLocation;
  private Point getRewardLocation;
  private Rectangle rareLevelArea;
  private boolean runLog;
  private boolean pickAllRune;
  private boolean pickLegendRune;
  private boolean pickHeroRune;
  private boolean pickSixStarRune;
  private boolean pickFiveStarRune;
  private BufferedImage sixStarRuneIndicator;
  private BufferedImage fiveStarRuneIndicator;
  private boolean pickGrindSpdPercent;
  private Rectangle grindStatArea;
  private Point sellStoneConfirmLocation;

  private int refillTimes;
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
  private BufferedImage stoneRewardIndicator;
  private BufferedImage inBattleIndicator;
  private BufferedImage confirmSellStoneIndicator;
  private BufferedImage reviveIndicator;

  public AddProfileModel(String profileName) {
    this.profileName = profileName;
    this.profile = new Profile();

    if (profileName != null && profileName.length() > 0) {
      this.profile.setName(profileName);
      this.profile.load();
    }
  }

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

  public BufferedImage getConfirmSellStoneIndicator() {
    return confirmSellStoneIndicator;
  }

  public Point getEnableAutoAttackLocation() {
    return enableAutoAttackLocation;
  }

  public Point getEnergyLocationOnShop() {
    return energyLocationOnShop;
  }

  public BufferedImage getFiveStarRuneIndicator() {
    return fiveStarRuneIndicator;
  }

  public Point getGetRewardLocation() {
    return getRewardLocation;
  }

  public Point getGetRuneRewardLocation() {
    return getRuneRewardLocation;
  }

  public Point getGetStoneRewardLocation() {
    return getStoneRewardLocation;
  }

  public Rectangle getGrindStatArea() {
    return grindStatArea;
  }

  public BufferedImage getInBattleIndicator() {
    return inBattleIndicator;
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

  public BufferedImage getNoCrysIndicator() {
    return noCrysIndicator;
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

  public Rectangle getRareLevelArea() {
    return rareLevelArea;
  }

  public Point getRechargeCrysNoLocation() {
    return rechargeCrysNoLocation;
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

  public BufferedImage getReviveIndicator() {
    return reviveIndicator;
  }

  public Point getReviveNoLocation() {
    return reviveNoLocation;
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

  public Point getSellStoneConfirmLocation() {
    return sellStoneConfirmLocation;
  }

  public Point getSellStoneLocation() {
    return sellStoneLocation;
  }

  public BufferedImage getSixStarRuneIndicator() {
    return sixStarRuneIndicator;
  }

  public BufferedImage getStartBattleIndicator() {
    return startBattleIndicator;
  }

  public Point getStartBattleLocation() {
    return startBattleLocation;
  }

  public BufferedImage getStoneRewardIndicator() {
    return stoneRewardIndicator;
  }

  public boolean isPickAllRune() {
    return pickAllRune;
  }

  public boolean isPickFiveStarRune() {
    return pickFiveStarRune;
  }

  public boolean isPickGrindSpdPercent() {
    return pickGrindSpdPercent;
  }

  public boolean isPickHeroRune() {
    return pickHeroRune;
  }

  public boolean isPickLegendRune() {
    return pickLegendRune;
  }

  public boolean isPickSixStarRune() {
    return pickSixStarRune;
  }

  public boolean isRandomClick() {
    return randomClick;
  }

  public boolean isRunLog() {
    return runLog;
  }

  public boolean isSellAllRune() {
    return sellAllRune;
  }

  public void loadData() {
    refillTimes = profile.isEmpty() ? 0 : Integer.valueOf(profile.getRefillTimes());
    replayBattleLocation = getLocation(profile.getReplayBattleX(), profile.getReplayBattleY());
    startBattleLocation = getLocation(profile.getStartBattleX(), profile.getStartBattleY());
    sellRuneLocation = getLocation(profile.getSellRuneLocationX(), profile.getSellRuneLocationY());
    sellRuneConfirmLocation =
        getLocation(profile.getSellRuneConfirmationX(), profile.getSellRuneConfirmationY());
    sellStoneConfirmLocation =
        getLocation(profile.getSellStoneConfirmationX(), profile.getSellStoneConfirmationY());
    getRuneRewardLocation = getLocation(profile.getGetRuneLocationX(), profile.getGetRuneLocationY());
    getRewardLocation = getLocation(profile.getGetRewardLocationX(), profile.getGetRewardLocationY());
    enableAutoAttackLocation =
        getLocation(profile.getEnableAutoModeX(), profile.getEnableAutoModeY());
    rechargeEneryYesLocation =
        getLocation(profile.getRechargeEnergyYesX(), profile.getRechargeEnergyYesY());
    rechargeEnergyNoLocation =
        getLocation(profile.getRechargeEnergyNoX(), profile.getRechargeEnergyNoY());
    energyLocationOnShop = getLocation(profile.getRechargeEnergyX(), profile.getRechargeEnergyY());
    confirmRechargeEnergyLoation =
        getLocation(profile.getConfirmRechargeEnergyX(), profile.getConfirmRechargeEnergyY());
    ackRefillSuccessLocation =
        getLocation(profile.getAckRechargeEnergyOkX(), profile.getAckRechargeEnergyOkY());
    closeRefillShopLocation =
        getLocation(profile.getCloseRechargeEnergyX(), profile.getCloseRechargeEnergyY());
    confirmNetworkDelayLocation =
        getLocation(profile.getConfirmNetworkDelayX(), profile.getConfirmNetworkDelayY());
    resendBattleInfoLocation =
        getLocation(profile.getResendBattleInfoX(), profile.getResendBattleInfoY());
    getStoneRewardLocation = getLocation(profile.getGetGemLocationX(), profile.getGetGemLocationY());
    sellStoneLocation = getLocation(profile.getSellGemLocationX(), profile.getSellGemLocationY());
    randomClick = profile.isClickRandom();
    sellAllRune = profile.isSellAllRune();
    runLog = profile.isRuneLog();
    pickAllRune = profile.isPickAllRune();
    pickLegendRune = profile.isPickLegendRune();
    pickHeroRune = profile.isPickHeroRune();
    rareLevelArea = profile.getRareLevelAreaBox();
    pickSixStarRune = profile.isPick6StarRune();
    pickFiveStarRune = profile.isPick5StarRune();
    sixStarRuneIndicator = (BufferedImage) profile.getIndicator(Indicator.sixStarRuneIndicator);
    fiveStarRuneIndicator = (BufferedImage) profile.getIndicator(Indicator.fiveStarRuneIndicator);
    pickGrindSpdPercent = profile.isPickSpdPercentGrindstone();
    grindStatArea = profile.getGrindstoneStatAreaBox();
    replayBattleIndicator = (BufferedImage) profile.getIndicator(Indicator.replayBattleIndicator);
    startBattleIndicator = (BufferedImage) profile.getIndicator(Indicator.startBattleIndicator);
    battleEndIndicator = (BufferedImage) profile.getIndicator(Indicator.battleEndIndicator);
    runeRewardIndiator = (BufferedImage) profile.getIndicator(Indicator.runeRewardIndiator);
    confirmSellRuneIndicator = (BufferedImage) profile.getIndicator(Indicator.confirmSellRuneIndicator);
    otherRewardIndicator = (BufferedImage) profile.getIndicator(Indicator.otherRewardIndicator);
    manualAttackIndicator = (BufferedImage) profile.getIndicator(Indicator.manualAttackIndicator);
    noEnergyIndicator = (BufferedImage) profile.getIndicator(Indicator.noEnergyIndicator);
    networkDelayIndicator = (BufferedImage) profile.getIndicator(Indicator.networkDelayIndicator);
    networkUnstableIndicator = (BufferedImage) profile.getIndicator(Indicator.networkUnstableIndicator);
    stoneRewardIndicator = (BufferedImage) profile.getIndicator(Indicator.stoneRewardIndicator);
    inBattleIndicator = (BufferedImage) profile.getIndicator(Indicator.inBattleIndicator);
    confirmSellStoneIndicator = (BufferedImage) profile.getIndicator(Indicator.confirmSellStoneIndicator);
    reviveIndicator = (BufferedImage) profile.getIndicator(Indicator.reviveIndicator);
    noCrysIndicator = (BufferedImage) profile.getIndicator(Indicator.noCrysIndicator);
    reviveNoLocation = getLocation(profile.getReviveNoX(), profile.getReviveNoY());
    rechargeCrysNoLocation = getLocation(profile.getRechargeCrysNoX(), profile.getRechargeCrysNoY());

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

  public void setConfirmSellStoneIndicator(final BufferedImage confirmSellStoneIndicator) {
    this.confirmSellStoneIndicator = confirmSellStoneIndicator;
  }

  public void setEnableAutoAttackLocation(final Point enableAutoAttackLocation) {
    this.enableAutoAttackLocation = enableAutoAttackLocation;
  }

  public void setEnergyLocationOnShop(final Point energyLocationOnShop) {
    this.energyLocationOnShop = energyLocationOnShop;
  }

  public void setFiveStarRuneIndicator(final BufferedImage fiveStarRuneIndicator) {
    this.fiveStarRuneIndicator = fiveStarRuneIndicator;
  }

  public void setGetRewardLocation(final Point getRewardLocation) {
    this.getRewardLocation = getRewardLocation;
  }

  public void setGetRuneRewardLocation(final Point getRuneRewardLocation) {
    this.getRuneRewardLocation = getRuneRewardLocation;
  }

  public void setGetStoneRewardLocation(final Point getStoneRewardLocation) {
    this.getStoneRewardLocation = getStoneRewardLocation;
  }

  public void setGrindStatArea(final Rectangle grindStatArea) {
    this.grindStatArea = grindStatArea;
  }

  public void setInBattleIndicator(final BufferedImage inBattleIndicator) {
    this.inBattleIndicator = inBattleIndicator;
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

  public void setNoCrysIndicator(final BufferedImage noCrysIndicator) {
    this.noCrysIndicator = noCrysIndicator;
  }

  public void setNoEnergyIndicator(final BufferedImage noEnergyIndicator) {
    this.noEnergyIndicator = noEnergyIndicator;
  }

  public void setOtherRewardIndicator(final BufferedImage otherRewardIndicator) {
    this.otherRewardIndicator = otherRewardIndicator;
  }

  public void setPickAllRune(final boolean pickAllRune) {
    this.pickAllRune = pickAllRune;
  }

  public void setPickFiveStarRune(final boolean pickFiveStarRune) {
    this.pickFiveStarRune = pickFiveStarRune;
  }

  public void setPickGrindSpdPercent(final boolean pickGrindSpdPercent) {
    this.pickGrindSpdPercent = pickGrindSpdPercent;
  }

  public void setPickHeroRune(final boolean pickHeroRune) {
    this.pickHeroRune = pickHeroRune;
  }

  public void setPickLegendRune(final boolean pickLegendRune) {
    this.pickLegendRune = pickLegendRune;
  }

  public void setPickSixStarRune(final boolean pickSixStarRune) {
    this.pickSixStarRune = pickSixStarRune;
  }

  public void setProfileName(final String profileName) {
    this.profileName = profileName;
  }

  public void setRandomClick(final boolean randomClick) {
    this.randomClick = randomClick;
  }

  public void setRareLevelArea(final Rectangle rareLevelArea) {
    this.rareLevelArea = rareLevelArea;
  }

  public void setRechargeCrysNoLocation(final Point rechargeCrysNoLocation) {
    this.rechargeCrysNoLocation = rechargeCrysNoLocation;
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

  public void setReviveIndicator(final BufferedImage reviveIndicator) {
    this.reviveIndicator = reviveIndicator;
  }

  public void setReviveNoLocation(final Point reviveNoLocation) {
    this.reviveNoLocation = reviveNoLocation;
  }

  public void setRuneRewardIndiator(final BufferedImage runeRewardIndiator) {
    this.runeRewardIndiator = runeRewardIndiator;
  }

  public void setRunLog(final boolean runLog) {
    this.runLog = runLog;
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

  public void setSellStoneConfirmLocation(final Point sellStoneConfirmLocation) {
    this.sellStoneConfirmLocation = sellStoneConfirmLocation;
  }

  public void setSellStoneLocation(final Point sellStoneLocation) {
    this.sellStoneLocation = sellStoneLocation;
  }

  public void setSixStarRuneIndicator(final BufferedImage sixStarRuneIndicator) {
    this.sixStarRuneIndicator = sixStarRuneIndicator;
  }

  public void setStartBattleIndicator(final BufferedImage startBattleIndicator) {
    this.startBattleIndicator = startBattleIndicator;
  }

  public void setStartBattleLocation(final Point startBattleLocation) {
    this.startBattleLocation = startBattleLocation;
  }

  public void setStoneRewardIndicator(final BufferedImage stoneRewardIndicator) {
    this.stoneRewardIndicator = stoneRewardIndicator;
  }

  private Point getLocation(final String x, final String y) {
    return x == null || y == null ? null : new Point(Integer.valueOf(x), Integer.valueOf(y));
  }
}
