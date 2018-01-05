/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.profile;

import com.justin.swbot.game.indicator.Indicator;
import com.justin.swbot.util.Point;
import com.justin.swbot.util.Rectangle;
import lombok.Builder;
import lombok.Data;

import java.io.File;

/**
 * @author tuan3.nguyen@gmail.com
 */

@Data
public class Profile {
  private transient String path;

  private String name;
  private int refillTimes;
  @Builder.Default
  private boolean randomClick = true;
  private boolean sellAllRunes;
  @Builder.Default
  private boolean runLogging = true;
  @Builder.Default
  private boolean pickAllRunes = true;
  private boolean pickLegendRunes;
  private boolean pickHeroRunes;
  private boolean pickSixStarRunes;
  private boolean pickFiveStarRunes;
  private boolean pickSpdPercentGrind;
  private Point sellRuneLocation;
  private Point getRuneLocation;
  private Point sellGemLocation;
  private Point getGemLocation;
  private Point getRewardLocation;
  private Point enableAutoMode;
  private Point replayBattle;
  private Point startBattle;
  private Point sellRuneConfirmation;
  private Point sellStoneConfirmation;
  private Point rechargeEnergyYes;
  private Point rechargeEnergyNo;
  private Point rechargeEnergy;
  private Point confirmRechargeEnergy;
  private Point ackRechargeEnergyOk;
  private Point closeRechargeEnergy;
  private Point confirmNetworkDelay;
  private Point resendBattleInfo;
  private Point reviveNo;
  private Point rechargeCrysNo;
  private Rectangle rareLevelBox;
  private Rectangle grindstoneStatBox;

  Profile() {

  }

  public File getIndicatorFile(Indicator indicator) {
    if (!isSaved()) throw new IllegalStateException("Profile must be saved before access indicator files");
    return new File(path + "/" + indicator.name());
  }

  public boolean isSaved() {
    return path != null;
  }
}
