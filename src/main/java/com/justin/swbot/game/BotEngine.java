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
    start();
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

  /**
   * (non-Javadoc)
   *
   * @see java.lang.Thread#run()
   */
  @Override
  public void run() {
    while (true) {
      final HomeController homeController =
          (HomeController) ControllerRegistry.get(HomeController.class);
      try {
        if (!running || director == null) {
          sleep(500);
          homeController.updateStatus("Bot is idling, click start to begin...");
          homeController.updateGameStatus(null);
          continue;
        }

        homeController.updateStatus("Detecting new game state...");
        final GameState gameState = detectGameState();
        homeController.updateGameStatus(gameState);
        director.direct(gameState);
      } catch (final Exception e) {
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
  }

  private GameState detectGameState() {
    final String screenshot = CommandUtil.capturePhoneScreen();
    final GameConfig config = GameConfig.get();
    if (screenshot == null) {
      return GameState.UNKNOWN;
    }
    if (doesStateMatch(screenshot, config.getManualAttackIndicatorFile())) {
      return GameState.BATTLE_MANUAL;
    }
    if (doesStateMatch(screenshot, config.getReplayBattleIndicatorFile())) {
      return GameState.REPLAY_BATTLE_CONFIRMATION;
    }
    if (doesStateMatch(screenshot, config.getStartBattleIndicatorFile())) {
      return GameState.START_BATTLE;
    }
    if (doesStateMatch(screenshot, config.getRuneRewardIndiatorFile())) {
      return GameState.RUNE_REWARD;
    }
    if (doesStateMatch(screenshot, config.getOtherRewardIndicatorFile())) {
      return GameState.OTHER_REWARD;
    }
    if (doesStateMatch(screenshot, config.getConfirmSellRuneIndicatorFile())) {
      return GameState.SELL_RUNE_CONFIRMATION;
    }
    if (doesStateMatch(screenshot, config.getNoEnergyIndicatorFile())) {
      return GameState.NOT_ENOUGH_ENERGY;
    }
    if (doesStateMatch(screenshot, config.getNetworkDelayIndicatorFile())) {
      return GameState.NETWORK_DELAY;
    }
    if (doesStateMatch(screenshot, config.getNetworkDelayIndicatorFile())) {
      return GameState.UNSTABLE_NETWORK;
    }
    if (doesStateMatch(screenshot, config.getBattleEndIndicatorFile())) {
      return GameState.BATTLE_ENDED;
    }
    return GameState.UNKNOWN;
  }

  private boolean doesStateMatch(final String screenshot, final File template) {
    if (template == null) {
      return false;
    }
    return ImageUtil.contains(screenshot, template.getAbsolutePath(), 95) != null;
  }
}
