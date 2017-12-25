/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import java.io.File;

import com.justin.swbot.CommandUtil;
import com.justin.swbot.ImageUtil;
import com.justin.swbot.game.director.ScenarioDirector;
import com.justin.swbot.home.HomeController;

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

  private static final BotEngine ENGINE = new BotEngine();

  /**
   * Get the singleton instance of {@link BotEngine}
   *
   * @return singleton instance.
   */
  public static BotEngine get() {
    return ENGINE;
  }

  private volatile boolean running = false;

  private ScenarioDirector director;

  private BotEngine() {
    // This is the hidden constructor for singleton classes to make sure it can't be instanced by
    // mistake.
  }

  /**
   * <p>
   * Check if the bot engine is running or fall into sleep mode.
   *
   * <p>
   * When the bot engine is in sleep mode, it still keep the infinitive loop but does nothings.
   *
   * @return <code>true</code> if the bot is running.
   */
  public boolean isRunning() {
    return running;
  }

  @Override
  public void run() {
    while (true) {
      final HomeController homeController =
          (HomeController) ControllerRegistry.get(HomeController.class);
      try {
        if (!running || director == null) {
          sleep(5000);
          homeController.updateStatus("Bot is idling, click start to begin...");
          homeController.updateGameStatus(null);
          continue;
        }

        homeController.updateStatus("Detecting new game state...");
        final GameStatus gameStatus = detectGameStatus();
        homeController.updateGameStatus(gameStatus.getGameState());
        director.direct(gameStatus);
      } catch (final Exception e) {
        e.printStackTrace();
        homeController.updateStatus("Error in bot loop: " + e.getMessage());
      }
    }
  }

  public void setDirector(final ScenarioDirector selectedDirector) {
    this.director = selectedDirector;
  }

  /**
   * Set the bot engine as running or fall into sleep mode.
   *
   * @param running <code>true</code> to active the running mode, otherwise fall into sleep mode.
   */
  public void setRunning(final boolean running) {
    this.running = running;

    if(running) {
      director.restart();
    }
  }

  private GameStatus detectGameStatus() {
    final String screenshot = CommandUtil.capturePhoneScreen();
    final GameConfig config = GameConfig.get();

    GameState gameState = GameState.UNKNOWN;
    if (doesStateMatch(screenshot, config.getStartBattleIndicatorFile())) {
      gameState = GameState.START_BATTLE;
    } else if (doesStateMatch(screenshot, config.getReplayBattleIndicatorFile())) {
      gameState = GameState.REPLAY_BATTLE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, config.getRuneRewardIndiatorFile())) {
      gameState = GameState.RUNE_REWARD;
    } else if (doesStateMatch(screenshot, config.getConfirmSellRuneIndicatorFile())) {
      gameState = GameState.SELL_RUNE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, config.getConfirmSellStoneIndicatorFile())) {
      gameState = GameState.SELL_STONE_CONFIRMATION;
    } else if (doesStateMatch(screenshot, config.getStoneRewardIndicatorFile())) {
      gameState = GameState.GEM_REWARD;
    } else if (doesStateMatch(screenshot, config.getOtherRewardIndicatorFile())) {
      gameState = GameState.OTHER_REWARD;
    } else if (doesStateMatch(screenshot, config.getBattleEndIndicatorFile())) {
      gameState = GameState.BATTLE_ENDED;
    } else if (doesStateMatch(screenshot, config.getNoEnergyIndicatorFile())) {
      gameState = GameState.NOT_ENOUGH_ENERGY;
    } else if (doesStateMatch(screenshot, config.getNetworkDelayIndicatorFile())) {
      gameState = GameState.NETWORK_DELAY;
    } else if (doesStateMatch(screenshot, config.getNetworkDelayIndicatorFile())) {
      gameState = GameState.UNSTABLE_NETWORK;
    } else if (doesStateMatch(screenshot, config.getInBattleIndicatorFile())) {
      gameState = GameState.IN_BATTLE;
    } else if (doesStateMatch(screenshot, config.getManualAttackIndicatorFile())) {
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
