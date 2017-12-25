/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class GameConfig {
  public static final String SELL_RUNE_LOC_X = "sellRuneLocationX";
  public static final String SELL_RUNE_LOC_Y = "sellRuneLocationY";
  public static final String GET_RUNE_LOC_X = "getRuneLocationX";
  public static final String GET_RUNE_LOC_Y = "getRuneLocationY";
  public static final String SELL_GEM_LOC_X = "sellGemLocationX";
  public static final String SELL_GEM_LOC_Y = "sellGemLocationY";
  public static final String GET_GEM_LOC_X = "getGemLocationX";
  public static final String GET_GEM_LOC_Y = "getGemLocationY";
  public static final String GET_REWARD_LOC_X = "getRewardLocationX";
  public static final String GET_REWARD_LOC_Y = "getRewardLocationY";
  public static final String ENABLE_AUTO_MODE_X = "enableAutoModeX";
  public static final String ENABLE_AUTO_MODE_Y = "enableAutoModeY";
  public static final String REPLAY_BATTLE_X = "replayBattleX";
  public static final String REPLAY_BATTLE_Y = "replayBattleY";
  public static final String START_BATTLE_X = "startBattleX";
  public static final String START_BATTLE_Y = "startBattleY";
  public static final String SELL_RUNE_CONFIRM_X = "sellRuneConfirmationX";
  public static final String SELL_RUNE_CONFIRM_Y = "sellRuneConfirmationY";
  public static final String SELL_GEM_CONFIRM_X = "sellStoneConfirmationX";
  public static final String SELL_GEM_CONFIRM_Y = "sellStoneConfirmationY";
  public static final String RECHARGE_ENGERGY_YES_X = "rechargeEnergyYesX";
  public static final String RECHARGE_ENGERGY_YES_Y = "rechargeEnergyYesY";
  public static final String RECHARGE_ENGERGY_NO_X = "rechargeEnergyNoX";
  public static final String RECHARGE_ENGERGY_NO_Y = "rechargeEnergyNoY";
  public static final String RECHARGE_ENERGY_X = "rechargeEnergyX";
  public static final String RECHARGE_ENERGY_Y = "rechargeEnergyY";
  public static final String CONFIRM_RECHARGE_ENERGY_X = "confirmRechargeEnergyX";
  public static final String CONFIRM_RECHARGE_ENERGY_Y = "confirmRechargeEnergyY";
  public static final String ACK_RECHARGE_ENERGY_OK_X = "ackRechargeEnergyOkX";
  public static final String ACK_RECHARGE_ENERGY_OK_Y = "ackRechargeEnergyOkY";
  public static final String CLOSE_RECHARGE_ENERGY_X = "closeRechargeEnergyX";
  public static final String CLOSE_RECHARGE_ENERGY_Y = "closeRechargeEnergyY";
  public static final String REFILL_TIMES = "refillTimes";
  public static final String CONFIRM_NETWORK_DELAY_X = "confirmNetworkDelayX";
  public static final String CONFIRM_NETWORK_DELAY_Y = "confirmNetworkDelayY";
  public static final String RESEND_BATTLE_INFO_X = "resendBattleInfoX";
  public static final String RESEND_BATTLE_INFO_Y = "resendBattleInfoY";
  public static final String RANDOM_CLICK = "randomClick";
  public static final String SELL_ALL_RUNE = "sellAllRunes";
  public static final String RUNE_LOG = "runLogging";
  public static final String PICK_ALL_RUNE = "pickAllRunes";
  public static final String PICK_LEGEND_RUNE = "pickLegendRunes";
  public static final String PICK_HERO_RUNE = "pickHeroRunes";
  public static final String RARE_LEVEL_AREA = "rareLevelBox";
  public static final String PICK_6_STAR_RUNE = "pickSixStarRunes";
  public static final String PICK_5_STAR_RUNE = "pickFiveStarRunes";
  public static final String PICK_SPD_PERCENT_GRIND = "pickSpdPercentGrind";
  public static final String GRINDSTONE_STAT_AREA = "grindstoneStatBox";

  private static final GameConfig INSTANCE = new GameConfig();
  private static final String IMAGE_FORMAT = "png";

  public static GameConfig get() {
    return INSTANCE;
  }

  private final Properties props = new Properties();
  private String profileName;
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
  private BufferedImage sixStarRuneIndicator;
  private BufferedImage fiveStarRuneIndicator;
  private BufferedImage stoneRewardIndicator;
  private BufferedImage inBattleIndicator;
  private BufferedImage confirmSellStoneIndicator;

  public void clear() {
    this.profileName = null;
    this.props.clear();
    replayBattleIndicator = null;
    startBattleIndicator = null;
    battleEndIndicator = null;
    runeRewardIndiator = null;
    confirmSellRuneIndicator = null;
    otherRewardIndicator = null;
    manualAttackIndicator = null;
    noEnergyIndicator = null;
    networkDelayIndicator = null;
    networkUnstableIndicator = null;
    sixStarRuneIndicator = null;
    fiveStarRuneIndicator = null;
    stoneRewardIndicator = null;
    inBattleIndicator = null;
    confirmSellStoneIndicator = null;
  }

  public String getAckRechargeEnergyOkX() {
    return props.getProperty(ACK_RECHARGE_ENERGY_OK_X);
  }

  public String getAckRechargeEnergyOkY() {
    return props.getProperty(ACK_RECHARGE_ENERGY_OK_Y);
  }

  public BufferedImage getBattleEndIndicator() {
    return battleEndIndicator;
  }

  public File getBattleEndIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "battleEndIndicator");
  }

  public String getCloseRechargeEnergyX() {
    return props.getProperty(CLOSE_RECHARGE_ENERGY_X);
  }

  public String getCloseRechargeEnergyY() {
    return props.getProperty(CLOSE_RECHARGE_ENERGY_Y);
  }

  public String getConfirmNetworkDelayX() {
    return props.getProperty(CONFIRM_NETWORK_DELAY_X);
  }

  public String getConfirmNetworkDelayY() {
    return props.getProperty(CONFIRM_NETWORK_DELAY_Y);
  }

  public String getConfirmRechargeEnergyX() {
    return props.getProperty(CONFIRM_RECHARGE_ENERGY_X);
  }

  public String getConfirmRechargeEnergyY() {
    return props.getProperty(CONFIRM_RECHARGE_ENERGY_Y);
  }

  public BufferedImage getConfirmSellRuneIndicator() {
    return confirmSellRuneIndicator;
  }

  public File getConfirmSellRuneIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "confirmSellRuneIndicator");
  }

  public BufferedImage getConfirmSellStoneIndicator() {
    return confirmSellStoneIndicator;
  }

  public File getConfirmSellStoneIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "confirmSellStoneIndicator");
  }

  public String getEnableAutoModeX() {
    return props.getProperty(ENABLE_AUTO_MODE_X);
  }

  public String getEnableAutoModeY() {
    return props.getProperty(ENABLE_AUTO_MODE_Y);
  }

  public BufferedImage getFiveStarRuneIndicator() {
    return fiveStarRuneIndicator;
  }

  public File getFiveStarRuneIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "fiveStarRuneIndicator");
  }

  public String getGetGemLocationX() {
    return props.getProperty(GET_GEM_LOC_X);
  }

  public String getGetGemLocationY() {
    return props.getProperty(GET_GEM_LOC_Y);
  }

  public String getGetRewardLocationX() {
    return props.getProperty(GET_REWARD_LOC_X);
  }

  public String getGetRewardLocationY() {
    return props.getProperty(GET_REWARD_LOC_Y);
  }

  public String getGetRuneLocationX() {
    return props.getProperty(GET_RUNE_LOC_X);
  }

  public String getGetRuneLocationY() {
    return props.getProperty(GET_RUNE_LOC_Y);
  }

  public String getGrindstoneStatArea() {
    return props.getProperty(GRINDSTONE_STAT_AREA, "0,0,0,0");
  }

  public Rectangle getGrindstoneStatAreaBox() {
    final String[] box = getGrindstoneStatArea().split(",");
    return new Rectangle(Integer.valueOf(box[0]), Integer.valueOf(box[1]), Integer.valueOf(box[2]),
        Integer.valueOf(box[3]));
  }

  public BufferedImage getInBattleIndicator() {
    return inBattleIndicator;
  }

  public File getInBattleIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "inBattleIndicator");
  }

  public BufferedImage getManualAttackIndicator() {
    return manualAttackIndicator;
  }

  public File getManualAttackIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "manualAttackIndicator");
  }

  public BufferedImage getNetworkDelayIndicator() {
    return networkDelayIndicator;
  }

  public File getNetworkDelayIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "networkDelayIndicator");
  }

  public BufferedImage getNetworkUnstableIndicator() {
    return networkUnstableIndicator;
  }

  public File getNetworkUnstableIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "networkUnstableIndicator");
  }

  public BufferedImage getNoEnergyIndicator() {
    return noEnergyIndicator;
  }

  public File getNoEnergyIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "noEnergyIndicator");
  }

  public BufferedImage getOtherRewardIndicator() {
    return otherRewardIndicator;
  }

  public File getOtherRewardIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "otherRewardIndicator");
  }

  public String getProfileName() {
    return profileName;
  }

  public File getProfilesFolder() {
    return new File("profiles");
  }

  public String getRareLevelArea() {
    return props.getProperty(RARE_LEVEL_AREA, "0,0,0,0");
  }

  public Rectangle getRareLevelAreaBox() {
    final String[] box = getRareLevelArea().split(",");
    return new Rectangle(Integer.valueOf(box[0]), Integer.valueOf(box[1]), Integer.valueOf(box[2]),
        Integer.valueOf(box[3]));
  }

  public String getRechargeEnergyNoX() {
    return props.getProperty(RECHARGE_ENGERGY_NO_X);
  }

  public String getRechargeEnergyNoY() {
    return props.getProperty(RECHARGE_ENGERGY_NO_Y);
  }

  public String getRechargeEnergyX() {
    return props.getProperty(RECHARGE_ENERGY_X);
  }

  public String getRechargeEnergyY() {
    return props.getProperty(RECHARGE_ENERGY_Y);
  }

  public String getRechargeEnergyYesX() {
    return props.getProperty(RECHARGE_ENGERGY_YES_X);
  }

  public String getRechargeEnergyYesY() {
    return props.getProperty(RECHARGE_ENGERGY_YES_Y);
  }

  public String getRefillTimes() {
    return props.getProperty(REFILL_TIMES);
  }

  public BufferedImage getReplayBattleIndicator() {
    return replayBattleIndicator;
  }

  public File getReplayBattleIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "replayBattleIndicator");
  }

  public String getReplayBattleX() {
    return props.getProperty(REPLAY_BATTLE_X);
  }

  public String getReplayBattleY() {
    return props.getProperty(REPLAY_BATTLE_Y);
  }

  public String getResendBattleInfoX() {
    return props.getProperty(RESEND_BATTLE_INFO_X);
  }

  public String getResendBattleInfoY() {
    return props.getProperty(RESEND_BATTLE_INFO_Y);
  }

  public BufferedImage getRuneRewardIndiator() {
    return runeRewardIndiator;
  }

  public File getRuneRewardIndiatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "runeRewardIndiator");
  }

  public String getSellGemLocationX() {
    return props.getProperty(SELL_GEM_LOC_X);
  }

  public String getSellGemLocationY() {
    return props.getProperty(SELL_GEM_LOC_Y);
  }

  public String getSellRuneConfirmationX() {
    return props.getProperty(SELL_RUNE_CONFIRM_X);
  }

  public String getSellRuneConfirmationY() {
    return props.getProperty(SELL_RUNE_CONFIRM_Y);
  }

  public String getSellRuneLocationX() {
    return props.getProperty(SELL_RUNE_LOC_X);
  }

  public String getSellRuneLocationY() {
    return props.getProperty(SELL_RUNE_LOC_Y);
  }

  public String getSellStoneConfirmationX() {
    return props.getProperty(SELL_GEM_CONFIRM_X);
  }

  public String getSellStoneConfirmationY() {
    return props.getProperty(SELL_GEM_CONFIRM_Y);
  }

  public BufferedImage getSixStarRuneIndicator() {
    return sixStarRuneIndicator;
  }

  public File getSixStarRuneIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "sixStarRuneIndicator");
  }

  public BufferedImage getStartBattleIndicator() {
    return startBattleIndicator;
  }

  public File getStartBattleIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "startBattleIndicator");
  }

  public String getStartBattleX() {
    return props.getProperty(START_BATTLE_X);
  }

  public String getStartBattleY() {
    return props.getProperty(START_BATTLE_Y);
  }

  public BufferedImage getStoneRewardIndicator() {
    return stoneRewardIndicator;
  }

  public File getStoneRewardIndicatorFile() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    return new File(profileFolder, "stoneRewardIndicator");
  }

  public boolean isClickRandom() {
    return Boolean.valueOf(props.getProperty(RANDOM_CLICK, "true"));
  }

  public boolean isEmpty() {
    return profileName == null && props.isEmpty();
  }

  public boolean isPick5StarRune() {
    return Boolean.valueOf(props.getProperty(PICK_5_STAR_RUNE, "false"));
  }

  public boolean isPick6StarRune() {
    return Boolean.valueOf(props.getProperty(PICK_6_STAR_RUNE, "false"));
  }

  public boolean isPickAllRune() {
    return Boolean.valueOf(props.getProperty(PICK_ALL_RUNE, "true"));
  }

  public boolean isPickHeroRune() {
    return Boolean.valueOf(props.getProperty(PICK_HERO_RUNE, "false"));
  }

  public boolean isPickLegendRune() {
    return Boolean.valueOf(props.getProperty(PICK_LEGEND_RUNE, "false"));
  }

  public boolean isPickSpdPercentGrindstone() {
    return Boolean.valueOf(props.getProperty(PICK_SPD_PERCENT_GRIND, "false"));
  }

  public boolean isRuneLog() {
    return Boolean.valueOf(props.getProperty(RUNE_LOG, "true"));
  }

  public boolean isSellAllRune() {
    return Boolean.valueOf(props.getProperty(SELL_ALL_RUNE, "false"));
  }

  public void load(final String file) {
    profileName = file;
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, profileName);
    if (!profileFolder.exists()) {
      profileFolder.mkdirs();
    }
    try {
      props.load(new FileInputStream(new File(profileFolder, "index.properties")));
      replayBattleIndicator = loadImage(getReplayBattleIndicatorFile());
      startBattleIndicator = loadImage(getStartBattleIndicatorFile());
      battleEndIndicator = loadImage(getBattleEndIndicatorFile());
      runeRewardIndiator = loadImage(getRuneRewardIndiatorFile());
      confirmSellRuneIndicator = loadImage(getConfirmSellRuneIndicatorFile());
      otherRewardIndicator = loadImage(getOtherRewardIndicatorFile());
      manualAttackIndicator = loadImage(getManualAttackIndicatorFile());
      noEnergyIndicator = loadImage(getNoEnergyIndicatorFile());
      networkDelayIndicator = loadImage(getNetworkDelayIndicatorFile());
      networkUnstableIndicator = loadImage(getNetworkUnstableIndicatorFile());
      sixStarRuneIndicator = loadImage(getSixStarRuneIndicatorFile());
      fiveStarRuneIndicator = loadImage(getFiveStarRuneIndicatorFile());
      stoneRewardIndicator = loadImage(getStoneRewardIndicatorFile());
      inBattleIndicator = loadImage(getInBattleIndicatorFile());
      confirmSellStoneIndicator = loadImage(getConfirmSellStoneIndicatorFile());
    } catch (final IOException ex) {
      System.err.println("Can't load the game configuration");
      ex.printStackTrace();
    }
  }

  public void save() {
    final File profilesFolder = getProfilesFolder();
    final File profileFolder = new File(profilesFolder, getProfileName());
    if (!profileFolder.exists()) {
      profileFolder.mkdirs();
    }

    try (FileOutputStream fos = new FileOutputStream(new File(profileFolder, "index.properties"))) {
      props.store(fos, "");
      storeImage(replayBattleIndicator, getReplayBattleIndicatorFile());
      storeImage(startBattleIndicator, getStartBattleIndicatorFile());
      storeImage(battleEndIndicator, getBattleEndIndicatorFile());
      storeImage(runeRewardIndiator, getRuneRewardIndiatorFile());
      storeImage(confirmSellRuneIndicator, getConfirmSellRuneIndicatorFile());
      storeImage(otherRewardIndicator, getOtherRewardIndicatorFile());
      storeImage(manualAttackIndicator, getManualAttackIndicatorFile());
      storeImage(noEnergyIndicator, getNoEnergyIndicatorFile());
      storeImage(networkDelayIndicator, getNetworkDelayIndicatorFile());
      storeImage(networkUnstableIndicator, getNetworkUnstableIndicatorFile());
      storeImage(sixStarRuneIndicator, getSixStarRuneIndicatorFile());
      storeImage(fiveStarRuneIndicator, getFiveStarRuneIndicatorFile());
      storeImage(stoneRewardIndicator, getStoneRewardIndicatorFile());
      storeImage(inBattleIndicator, getInBattleIndicatorFile());
      storeImage(confirmSellStoneIndicator, getConfirmSellStoneIndicatorFile());
    } catch (final IOException ex) {
      throw new RuntimeException("Could not store the profile", ex);
    }
  }

  public void setAckRechargeEnergyOk(final Point point) {
    props.setProperty(ACK_RECHARGE_ENERGY_OK_X, String.valueOf(point.x));
    props.setProperty(ACK_RECHARGE_ENERGY_OK_Y, String.valueOf(point.y));
  }

  public void setBattleEndIndicator(final BufferedImage battleEndIndicator) {
    this.battleEndIndicator = battleEndIndicator;
  }

  public void setClickRandom(final boolean value) {
    props.setProperty(RANDOM_CLICK, String.valueOf(value));
  }

  public void setCloseRechargeEnergy(final Point point) {
    props.setProperty(CLOSE_RECHARGE_ENERGY_X, String.valueOf(point.x));
    props.setProperty(CLOSE_RECHARGE_ENERGY_Y, String.valueOf(point.y));
  }

  public void setConfirmNetworkDelay(final Point point) {
    props.setProperty(CONFIRM_NETWORK_DELAY_X, String.valueOf(point.x));
    props.setProperty(CONFIRM_NETWORK_DELAY_Y, String.valueOf(point.y));
  }

  public void setConfirmRechargeEnergy(final Point point) {
    props.setProperty(CONFIRM_RECHARGE_ENERGY_X, String.valueOf(point.x));
    props.setProperty(CONFIRM_RECHARGE_ENERGY_Y, String.valueOf(point.y));
  }

  public void setConfirmSellRuneIndicator(final BufferedImage confirmSellRuneIndicator) {
    this.confirmSellRuneIndicator = confirmSellRuneIndicator;
  }

  public void setConfirmSellStoneIndicator(final BufferedImage confirmSellStoneIndicator) {
    this.confirmSellStoneIndicator = confirmSellStoneIndicator;
  }

  public void setEnableAutoMode(final Point point) {
    props.setProperty(ENABLE_AUTO_MODE_X, String.valueOf(point.x));
    props.setProperty(ENABLE_AUTO_MODE_Y, String.valueOf(point.y));
  }

  public void setFiveStarRuneIndicator(final BufferedImage fiveStarRuneIndicator) {
    this.fiveStarRuneIndicator = fiveStarRuneIndicator;
  }

  public void setGetGemLocation(final Point point) {
    props.setProperty(GET_GEM_LOC_X, String.valueOf(point.x));
    props.setProperty(GET_GEM_LOC_Y, String.valueOf(point.y));
  }

  public void setGetRewardLocation(final Point point) {
    props.setProperty(GET_REWARD_LOC_X, String.valueOf(point.x));
    props.setProperty(GET_REWARD_LOC_Y, String.valueOf(point.y));
  }

  public void setGetRuneLocation(final Point point) {
    props.setProperty(GET_RUNE_LOC_X, String.valueOf(point.x));
    props.setProperty(GET_RUNE_LOC_Y, String.valueOf(point.y));
  }

  public void setGrindstoneStatArea(final int x, final int y, final int w, final int h) {
    props.setProperty(GRINDSTONE_STAT_AREA, String.format("%s,%s,%s,%s", x, y, w, h));
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

  public void setNoEnergyIndicator(final BufferedImage noEnergyIndicator) {
    this.noEnergyIndicator = noEnergyIndicator;
  }

  public void setOtherRewardIndicator(final BufferedImage otherRewardIndicator) {
    this.otherRewardIndicator = otherRewardIndicator;
  }

  public void setPick5StarRune(final boolean value) {
    props.setProperty(PICK_5_STAR_RUNE, String.valueOf(value));
  }

  public void setPick6StarRune(final boolean value) {
    props.setProperty(PICK_6_STAR_RUNE, String.valueOf(value));
  }

  public void setPickAllRune(final boolean value) {
    props.setProperty(PICK_ALL_RUNE, String.valueOf(value));
  }

  public void setPickHeroRune(final boolean value) {
    props.setProperty(PICK_HERO_RUNE, String.valueOf(value));
  }

  public void setPickLegendRune(final boolean value) {
    props.setProperty(PICK_LEGEND_RUNE, String.valueOf(value));
  }

  public void setPickSpdPercentGridstone(final boolean value) {
    props.setProperty(PICK_SPD_PERCENT_GRIND, String.valueOf(value));
  }

  public void setProfileName(final String profileName) {
    this.profileName = profileName;
  }

  public void setRareLevelArea(final int x, final int y, final int w, final int h) {
    props.setProperty(RARE_LEVEL_AREA, String.format("%s,%s,%s,%s", x, y, w, h));
  }

  public void setRechargeEnergy(final Point point) {
    props.setProperty(RECHARGE_ENERGY_X, String.valueOf(point.x));
    props.setProperty(RECHARGE_ENERGY_Y, String.valueOf(point.y));
  }

  public void setRechargeEnergyNo(final Point point) {
    props.setProperty(RECHARGE_ENGERGY_NO_X, String.valueOf(point.x));
    props.setProperty(RECHARGE_ENGERGY_NO_Y, String.valueOf(point.y));
  }

  public void setRechargeEnergyYes(final Point point) {
    props.setProperty(RECHARGE_ENGERGY_YES_X, String.valueOf(point.x));
    props.setProperty(RECHARGE_ENGERGY_YES_Y, String.valueOf(point.y));
  }

  public void setRefillTimes(final int refillTimes) {
    props.setProperty(REFILL_TIMES, String.valueOf(refillTimes));
  }

  public void setReplayBattle(final Point point) {
    props.setProperty(REPLAY_BATTLE_X, String.valueOf(point.x));
    props.setProperty(REPLAY_BATTLE_Y, String.valueOf(point.y));
  }

  public void setReplayBattleIndicator(final BufferedImage replayBattleIndicator) {
    this.replayBattleIndicator = replayBattleIndicator;
  }

  public void setResendBattleInfoX(final Point point) {
    props.setProperty(RESEND_BATTLE_INFO_X, String.valueOf(point.x));
    props.setProperty(RESEND_BATTLE_INFO_Y, String.valueOf(point.y));
  }

  public void setRuneLog(final boolean value) {
    props.setProperty(RUNE_LOG, String.valueOf(value));
  }

  public void setRuneRewardIndiator(final BufferedImage runeRewardIndiator) {
    this.runeRewardIndiator = runeRewardIndiator;
  }

  public void setSellAllRune(final boolean value) {
    props.setProperty(SELL_ALL_RUNE, String.valueOf(value));
  }

  public void setSellGemLocation(final Point point) {
    props.setProperty(SELL_GEM_LOC_X, String.valueOf(point.x));
    props.setProperty(SELL_GEM_LOC_Y, String.valueOf(point.y));
  }

  public void setSellRuneConfirmation(final Point point) {
    props.setProperty(SELL_RUNE_CONFIRM_X, String.valueOf(point.x));
    props.setProperty(SELL_RUNE_CONFIRM_Y, String.valueOf(point.y));
  }

  public void setSellRuneLocation(final Point point) {
    props.setProperty(SELL_RUNE_LOC_X, String.valueOf(point.x));
    props.setProperty(SELL_RUNE_LOC_Y, String.valueOf(point.y));
  }

  public void setSellStoneConfirmation(final Point point) {
    props.setProperty(SELL_GEM_CONFIRM_X, String.valueOf(point.x));
    props.setProperty(SELL_GEM_CONFIRM_Y, String.valueOf(point.y));
  }

  public void setSixStarRuneIndicator(final BufferedImage sixStarRuneIndicator) {
    this.sixStarRuneIndicator = sixStarRuneIndicator;
  }

  public void setStartBattle(final Point point) {
    props.setProperty(START_BATTLE_X, String.valueOf(point.x));
    props.setProperty(START_BATTLE_Y, String.valueOf(point.y));
  }

  public void setStartBattleIndicator(final BufferedImage startBattleIndicator) {
    this.startBattleIndicator = startBattleIndicator;
  }

  public void setStoneRewardIndicator(final BufferedImage stoneRewardIndicator) {
    this.stoneRewardIndicator = stoneRewardIndicator;
  }

  private BufferedImage loadImage(final File imageFile) throws IOException {
    if (imageFile != null && imageFile.exists()) {
      return ImageIO.read(imageFile);
    }
    return null;
  }

  private void storeImage(final BufferedImage image, final File location) throws IOException {
    if (image != null) {
      ImageIO.write(image, IMAGE_FORMAT, location);
    }
  }
}
