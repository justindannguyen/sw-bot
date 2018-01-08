package com.justin.swbot.game.analyzer;

import com.justin.swbot.game.GameState;
import com.justin.swbot.game.indicator.Indicator;
import com.justin.swbot.util.ImageUtil;
import lombok.Setter;

import static com.justin.swbot.game.indicator.Indicator.battleEndIndicator;
import static com.justin.swbot.game.indicator.Indicator.confirmSellRuneIndicator;
import static com.justin.swbot.game.indicator.Indicator.confirmSellStoneIndicator;
import static com.justin.swbot.game.indicator.Indicator.inBattleIndicator;
import static com.justin.swbot.game.indicator.Indicator.manualAttackIndicator;
import static com.justin.swbot.game.indicator.Indicator.networkDelayIndicator;
import static com.justin.swbot.game.indicator.Indicator.networkUnstableIndicator;
import static com.justin.swbot.game.indicator.Indicator.noEnergyIndicator;
import static com.justin.swbot.game.indicator.Indicator.otherRewardIndicator;
import static com.justin.swbot.game.indicator.Indicator.replayBattleIndicator;
import static com.justin.swbot.game.indicator.Indicator.reviveIndicator;
import static com.justin.swbot.game.indicator.Indicator.runeRewardIndiator;
import static com.justin.swbot.game.indicator.Indicator.startBattleIndicator;
import static com.justin.swbot.game.indicator.Indicator.stoneRewardIndicator;

/**
 * Created by akivamu on 07/01/18.
 */
public class Analyzer {
  @Setter
  private String indicatorsDir;

  public GameState detectGameState(String screenFilePath) {
    GameState gameState = GameState.UNKNOWN;

    if (containIndicators(screenFilePath, startBattleIndicator)) {
      gameState = GameState.START_BATTLE;
    } else if (containIndicators(screenFilePath, replayBattleIndicator)) {
      gameState = GameState.REPLAY_BATTLE_CONFIRMATION;
    } else if (containIndicators(screenFilePath, runeRewardIndiator)) {
      gameState = GameState.RUNE_REWARD;
    } else if (containIndicators(screenFilePath, confirmSellRuneIndicator)) {
      gameState = GameState.SELL_RUNE_CONFIRMATION;
    } else if (containIndicators(screenFilePath, confirmSellStoneIndicator)) {
      gameState = GameState.SELL_STONE_CONFIRMATION;
    } else if (containIndicators(screenFilePath, stoneRewardIndicator)) {
      gameState = GameState.GEM_REWARD;
    } else if (containIndicators(screenFilePath, otherRewardIndicator)) {
      gameState = GameState.OTHER_REWARD;
    } else if (containIndicators(screenFilePath, noEnergyIndicator)) {
      gameState = GameState.NOT_ENOUGH_ENERGY;
    } else if (containIndicators(screenFilePath, battleEndIndicator)) {
      gameState = GameState.BATTLE_ENDED;
    } else if (containIndicators(screenFilePath, reviveIndicator)) {
      gameState = GameState.BATTLE_ENDED_FAIL;
    } else if (containIndicators(screenFilePath, networkDelayIndicator)) {
      gameState = GameState.NETWORK_DELAY;
    } else if (containIndicators(screenFilePath, networkUnstableIndicator)) {
      gameState = GameState.UNSTABLE_NETWORK;
    } else if (containIndicators(screenFilePath, inBattleIndicator)) {
      gameState = GameState.IN_BATTLE;
    } else if (containIndicators(screenFilePath, manualAttackIndicator)) {
      gameState = GameState.BATTLE_MANUAL;
    }
    return gameState;
  }

  protected boolean containIndicators(String screenFilePath, Indicator... indicators) {
    if (indicators == null || indicators.length == 0) return false;

    for (Indicator indicator : indicators) {
      if (ImageUtil.contains(screenFilePath, getIndicatorFilePath(indicator), 98) == null) {
        return false;
      }
    }

    return true;
  }

  protected String getIndicatorFilePath(Indicator indicator) {
    return indicatorsDir + "/" + indicator.name();
  }
}
