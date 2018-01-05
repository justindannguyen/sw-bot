/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.indicator.Indicator;
import com.justin.swbot.game.profile.Profile;
import com.justin.swbot.util.MemImageCache;
import lombok.Data;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Observable;

import static com.justin.swbot.util.PcConverter.fromAwtPoint;
import static com.justin.swbot.util.PcConverter.fromAwtRectangle;
import static com.justin.swbot.util.PcConverter.toAwtRectangle;

/**
 * @author tuan3.nguyen@gmail.com
 */
@Data
public class AddProfileModel extends Observable {
  public static final String MODEL_LOADED = "MODEL_LOADED";

  private String profileName;
  private Point replayBattleLocation;
  private Point startBattleLocation;
  private Point confirmNetworkDelayLocation;
  private Point resendBattleInfoLocation;
  private Point enableAutoAttackLocation;
  private boolean randomClick;
  private Point reviveNoLocation;
  private Point rechargeCrysNoLocation;
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
  private boolean pickGrindSpdPercent;
  private Rectangle grindStatArea;
  private Point sellStoneConfirmLocation;
  private Point rechargeEneryYesLocation;
  private Point rechargeEnergyNoLocation;
  private Point energyLocationOnShop;
  private Point confirmRechargeEnergyLoation;
  private Point ackRefillSuccessLocation;
  private Point closeRefillShopLocation;
  private int refillTimes;

  private BufferedImage sixStarRuneIndicator;
  private BufferedImage fiveStarRuneIndicator;
  private BufferedImage noCrysIndicator;
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

  public void populateDataFromProfile(Profile profile) {
    profileName = profile.getName();
    refillTimes = profile.getRefillTimes();
    replayBattleLocation = getLocation(profile.getReplayBattle());
    startBattleLocation = getLocation(profile.getStartBattle());
    sellRuneLocation = getLocation(profile.getSellRuneLocation());
    sellRuneConfirmLocation = getLocation(profile.getSellRuneConfirmation());
    sellStoneConfirmLocation = getLocation(profile.getSellStoneConfirmation());
    getRuneRewardLocation = getLocation(profile.getGetRuneLocation());
    getRewardLocation = getLocation(profile.getGetRewardLocation());
    enableAutoAttackLocation = getLocation(profile.getEnableAutoMode());
    rechargeEneryYesLocation = getLocation(profile.getRechargeEnergyYes());
    rechargeEnergyNoLocation = getLocation(profile.getRechargeEnergyNo());
    energyLocationOnShop = getLocation(profile.getRechargeEnergy());
    confirmRechargeEnergyLoation = getLocation(profile.getConfirmRechargeEnergy());
    ackRefillSuccessLocation = getLocation(profile.getAckRechargeEnergyOk());
    closeRefillShopLocation = getLocation(profile.getCloseRechargeEnergy());
    confirmNetworkDelayLocation = getLocation(profile.getConfirmNetworkDelay());
    resendBattleInfoLocation = getLocation(profile.getResendBattleInfo());
    getStoneRewardLocation = getLocation(profile.getGetGemLocation());
    sellStoneLocation = getLocation(profile.getSellGemLocation());
    rareLevelArea = toAwtRectangle(profile.getRareLevelBox());
    grindStatArea = toAwtRectangle(profile.getGrindstoneStatBox());
    reviveNoLocation = getLocation(profile.getReviveNo());
    rechargeCrysNoLocation = getLocation(profile.getRechargeCrysNo());
    randomClick = profile.isRandomClick();
    sellAllRune = profile.isSellAllRunes();
    runLog = profile.isRunLogging();
    pickAllRune = profile.isPickAllRunes();
    pickLegendRune = profile.isPickLegendRunes();
    pickHeroRune = profile.isPickHeroRunes();
    pickSixStarRune = profile.isPickSixStarRunes();
    pickFiveStarRune = profile.isPickFiveStarRunes();
    pickGrindSpdPercent = profile.isPickSpdPercentGrind();

    MemImageCache memImageCache = MemImageCache.getInstance();
    if (profile.isSaved()) {
      sixStarRuneIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.sixStarRuneIndicator));
      fiveStarRuneIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.fiveStarRuneIndicator));
      replayBattleIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.replayBattleIndicator));
      startBattleIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.startBattleIndicator));
      battleEndIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.battleEndIndicator));
      runeRewardIndiator = memImageCache.get(profile.getIndicatorFile(Indicator.runeRewardIndiator));
      confirmSellRuneIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.confirmSellRuneIndicator));
      otherRewardIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.otherRewardIndicator));
      manualAttackIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.manualAttackIndicator));
      noEnergyIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.noEnergyIndicator));
      networkDelayIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.networkDelayIndicator));
      networkUnstableIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.networkUnstableIndicator));
      stoneRewardIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.stoneRewardIndicator));
      inBattleIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.inBattleIndicator));
      confirmSellStoneIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.confirmSellStoneIndicator));
      reviveIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.reviveIndicator));
      noCrysIndicator = memImageCache.get(profile.getIndicatorFile(Indicator.noCrysIndicator));
    }

    setChanged();
    notifyObservers(MODEL_LOADED);
  }

  public Profile toProfile() {
    Profile profile = DependenciesRegistry.profileManager.createEmptyProfile();
    profile.setName(profileName);
    profile.setRefillTimes(getRefillTimes());
    profile.setReplayBattle(fromAwtPoint(getReplayBattleLocation()));
    profile.setStartBattle(fromAwtPoint(getStartBattleLocation()));
    profile.setSellRuneLocation(fromAwtPoint(getSellRuneLocation()));
    profile.setSellRuneConfirmation(fromAwtPoint(getSellRuneConfirmLocation()));
    profile.setGetRuneLocation(fromAwtPoint(getGetRuneRewardLocation()));
    profile.setGetRewardLocation(fromAwtPoint(getGetRewardLocation()));
    profile.setEnableAutoMode(fromAwtPoint(getEnableAutoAttackLocation()));
    profile.setRechargeEnergyYes(fromAwtPoint(getRechargeEneryYesLocation()));
    profile.setRechargeEnergyNo(fromAwtPoint(getRechargeEnergyNoLocation()));
    profile.setRechargeEnergy(fromAwtPoint(getEnergyLocationOnShop()));
    profile.setConfirmRechargeEnergy(fromAwtPoint(getConfirmRechargeEnergyLoation()));
    profile.setAckRechargeEnergyOk(fromAwtPoint(getAckRefillSuccessLocation()));
    profile.setCloseRechargeEnergy(fromAwtPoint(getCloseRefillShopLocation()));
    profile.setConfirmNetworkDelay(fromAwtPoint(getConfirmNetworkDelayLocation()));
    profile.setResendBattleInfo(fromAwtPoint(getResendBattleInfoLocation()));
    profile.setGetGemLocation(fromAwtPoint(getGetStoneRewardLocation()));
    profile.setSellGemLocation(fromAwtPoint(getSellStoneLocation()));
    profile.setSellStoneConfirmation(fromAwtPoint(getSellStoneConfirmLocation()));
    profile.setRandomClick(isRandomClick());
    profile.setSellAllRunes(isSellAllRune());
    profile.setRunLogging(isRunLog());
    profile.setPickAllRunes(isPickAllRune());
    profile.setPickLegendRunes(isPickLegendRune());
    profile.setPickHeroRunes(isPickHeroRune());
    profile.setRareLevelBox(fromAwtRectangle(getRareLevelArea()));
    profile.setPickFiveStarRunes(isPickFiveStarRune());
    profile.setPickSixStarRunes(isPickSixStarRune());
    profile.setPickSpdPercentGrind(isPickGrindSpdPercent());
    profile.setGrindstoneStatBox(fromAwtRectangle(getGrindStatArea()));
    profile.setReviveNo(fromAwtPoint(getReviveNoLocation()));
    profile.setRechargeCrysNo(fromAwtPoint(getRechargeCrysNoLocation()));

    return profile;
  }

  private Point getLocation(com.justin.swbot.util.Point point) {
    if (point == null) return null;
    return new Point(point.x, point.y);
  }
}
