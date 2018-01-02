/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import java.io.File;

import com.justin.swbot.CommandUtil;
import com.justin.swbot.ImageUtil;
import com.justin.swbot.game.director.ScenarioDirector;
import com.justin.swbot.ui.HomeView;

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
    this.profile = new Profile(profileName);
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
    if (doesStateMatch(screenshot, profile.getStartBattleIndicatorFile())) {
      gameState = GameState.START_BATTLE;
    } else if (doesStateMatch(screenshot, profile.getReplayBattleIndicatorFile())) {
      gameState = GameState.REPLAY_BATTLE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, profile.getRuneRewardIndiatorFile())) {
      gameState = GameState.RUNE_REWARD;
    } else if (doesStateMatch(screenshot, profile.getConfirmSellRuneIndicatorFile())) {
      gameState = GameState.SELL_RUNE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, profile.getConfirmSellStoneIndicatorFile())) {
      gameState = GameState.SELL_STONE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, profile.getStoneRewardIndicatorFile())) {
      gameState = GameState.GEM_REWARD;
    } else if (doesStateMatch(screenshot, profile.getOtherRewardIndicatorFile())) {
      gameState = GameState.OTHER_REWARD;
    } else if (doesStateMatch(screenshot, profile.getNoEnergyIndicatorFile())) {
      gameState = GameState.NOT_ENOUGH_ENERGY;
    } else if (doesStateMatch(screenshot, profile.getBattleEndIndicatorFile())) {
      gameState = GameState.BATTLE_ENDED;
    } else if (doesStateMatch(screenshot, profile.getReviveIndicatorFile())) {
      gameState = GameState.BATTLE_ENDED_FAIL;
    } else if (doesStateMatch(screenshot, profile.getNetworkDelayIndicatorFile())) {
      gameState = GameState.NETWORK_DELAY;
    } else if (doesStateMatch(screenshot, profile.getNetworkUnstableIndicatorFile())) {
      gameState = GameState.UNSTABLE_NETWORK;
    } else if (doesStateMatch(screenshot, profile.getInBattleIndicatorFile())) {
      gameState = GameState.IN_BATTLE;
    } else if (doesStateMatch(screenshot, profile.getManualAttackIndicatorFile())) {
      gameState = GameState.BATTLE_MANUAL;
    }
    return GameStatus.create(gameState, screenshot);
  }

  private boolean doesStateMatch(final String screenshot, final File template) {
    if (template == null) {
      return false;
    }
    return ImageUtil.contains(screenshot, template.getAbsolutePath(), 98) != null;
  }
}
