/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import com.justin.swbot.util.CommandUtil;
import com.justin.swbot.util.ImageUtil;
import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.director.ScenarioDirector;
import com.justin.swbot.ui.HomeView;
import lombok.Setter;

import java.io.File;

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

    private volatile boolean running = false;

    private final CommandUtil commandUtil;

    private final GameConfig config;

    private ScenarioDirector director;

    @Setter
    private HomeView homeView;

    private static BotEngine ENGINE;

    /**
     * Get the singleton instance of {@link BotEngine}
     *
     * @return singleton instance.
     */
    public static BotEngine get() {
        if (ENGINE == null) ENGINE = new BotEngine();
        return ENGINE;
    }

    private BotEngine() {
        // This is the hidden constructor for singleton classes to make sure it can't be instanced by
        // mistake.
        this.commandUtil = DependenciesRegistry.commandUtil;
        this.config = GameConfig.get();
    }

    /**
     * <p>
     * Check if the bot engine is running or fall into sleep mode.
     * <p>
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
            try {
                if (!running || director == null) {
                    sleep(5000);
                    homeView.updateStatus("Bot is idling, click start to begin...");
                    homeView.updateGameStatus(null);
                    continue;
                }

                homeView.updateStatus("Detecting new game state...");
                final GameStatus gameStatus = detectGameStatus();
                homeView.updateGameStatus(gameStatus.getGameState());
                director.direct(gameStatus);
            } catch (final Exception e) {
                e.printStackTrace();
                homeView.updateStatus("Error in bot loop: " + e.getMessage());
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

        if (running && director != null) {
            director.restart();
        }
    }

    private GameStatus detectGameStatus() {
        final String screenshot = commandUtil.capturePhoneScreen();
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
        } else if (doesStateMatch(screenshot, config.getReviveToContinueIndicatorFile())) {
            gameState = GameState.REVIVE_TO_CONTINUE;
        }
        return GameStatus.create(gameState, screenshot);
    }

    private boolean doesStateMatch(final String screenshot, final File template) {
        if (template == null) {
            return false;
        }
        return ImageUtil.contains(screenshot, template.getAbsolutePath(), 99) != null;
    }
}
