/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import com.justin.swbot.CommandUtil;
import com.justin.swbot.ImageUtil;
import com.justin.swbot.game.director.ScenarioDirector;
import com.justin.swbot.game.indicator.Indicator;
import com.justin.swbot.ui.HomeView;

import java.io.File;

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
 * <p>
 * General, main purpose is to hold the bot loop.
 * <p>
 * Bot engine contains the infinitive loop in order to identify the current game state. It asks
 * suggestion, command from the directors for what to do, where to click.
 *
 * @author tuan3.nguyen@gmail.com
 */
public final class BotEngine extends Thread {
  private final ScenarioDirector director;
  private final HomeView homeView;
  private final Profile profile;
  private boolean isRunning;

  public BotEngine(ScenarioDirector director, String profileName, HomeView homeView) {
    super();

    this.director = director;
    this.homeView = homeView;
    this.profile = new Profile();
    this.profile.setName(profileName);
    this.profile.load();
    this.director.setProfile(profile);

    this.director.restart();
  }

  @Override
  public void run() {
    isRunning = true;

    while (isRunning) {
      try {
        homeView.updateStatus("Detecting new game state...");
        final GameStatus gameStatus = detectGameStatus();
        homeView.updateGameStatus(gameStatus.getGameState());
        director.direct(gameStatus);
        sleep(1000);
      } catch (final Exception e) {
        e.printStackTrace();
        homeView.updateStatus("Error in bot loop: " + e.getMessage());
      }
    }
  }

  public void stopEngine() {
    isRunning = false;
  }

  private GameStatus detectGameStatus() {
    final String screenshot = CommandUtil.capturePhoneScreen();

    GameState gameState = GameState.UNKNOWN;
    if (doesStateMatch(screenshot, startBattleIndicator)) {
      gameState = GameState.START_BATTLE;
    } else if (doesStateMatch(screenshot, replayBattleIndicator)) {
      gameState = GameState.REPLAY_BATTLE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, runeRewardIndiator)) {
      gameState = GameState.RUNE_REWARD;
    } else if (doesStateMatch(screenshot, confirmSellRuneIndicator)) {
      gameState = GameState.SELL_RUNE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, confirmSellStoneIndicator)) {
      gameState = GameState.SELL_STONE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, stoneRewardIndicator)) {
      gameState = GameState.GEM_REWARD;
    } else if (doesStateMatch(screenshot, otherRewardIndicator)) {
      gameState = GameState.OTHER_REWARD;
    } else if (doesStateMatch(screenshot, noEnergyIndicator)) {
      gameState = GameState.NOT_ENOUGH_ENERGY;
    } else if (doesStateMatch(screenshot, battleEndIndicator)) {
      gameState = GameState.BATTLE_ENDED;
    } else if (doesStateMatch(screenshot, reviveIndicator)) {
      gameState = GameState.BATTLE_ENDED_FAIL;
    } else if (doesStateMatch(screenshot, networkDelayIndicator)) {
      gameState = GameState.NETWORK_DELAY;
    } else if (doesStateMatch(screenshot, networkUnstableIndicator)) {
      gameState = GameState.UNSTABLE_NETWORK;
    } else if (doesStateMatch(screenshot, inBattleIndicator)) {
      gameState = GameState.IN_BATTLE;
    } else if (doesStateMatch(screenshot, manualAttackIndicator)) {
      gameState = GameState.BATTLE_MANUAL;
    }
    return GameStatus.create(gameState, screenshot);
  }

  private boolean doesStateMatch(final String screenshot, Indicator indicator) {
    File indicatorFile = profile.getIndicatorFile(indicator);
    if (indicatorFile == null) {
      return false;
    }
    return ImageUtil.contains(screenshot, indicatorFile.getAbsolutePath(), 98) != null;
  }
}
