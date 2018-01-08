/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game.director;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.GameState;
import com.justin.swbot.game.GameStatus;
import com.justin.swbot.game.analyzer.Analyzer;
import com.justin.swbot.game.profile.Profile;
import com.justin.swbot.util.CommandUtil;
import com.justin.swbot.util.OcrUtil;
import lombok.Getter;

/**
 * <p>
 * The game will contain multiple scenario e.g. rune farming, level farming, material dungeon
 * farming, etc and director is required for each scenario to provide what to do, where to click.
 * <p>
 * <p>
 * The director requires the game state as input so that it can give the correct command to the bot
 * engine e.g. if we are farming rune and game state is at the end of battle, director should give
 * command to keep all item.
 * <p>
 * <p>
 * The director alone can't decide everything and it may need help from the data secretary in order
 * to have phone profile, location and screen image.
 * <p>
 * <p>
 * The director will be registered in {@link SupportedDirectors}.
 * Therefore, if you want to auto new scenario, following steps is a must:
 * <ul>
 * <li>Create new director class which implement this interface.</li>
 * <li>Register new director into the {@link SupportedDirectors}
 * <li>Implement the give directive logic</li>
 * </ul>
 *
 * @author tuan3.nguyen@gmail.com
 */
public abstract class Director {
  protected final Listener listener;
  protected final Profile profile;
  protected final Analyzer analyzer;

  @Getter
  protected GameStatus currentGameStatus;
  protected final CommandUtil commandUtil;
  protected final OcrUtil ocrUtil;

  public Director(Listener listener, Profile profile) {
    this.listener = listener;
    this.profile = profile;
    this.analyzer = getDefaultAnalyzer();
    this.analyzer.setIndicatorsDir(profile.getPath());

    this.commandUtil = DependenciesRegistry.commandUtil;
    this.ocrUtil = DependenciesRegistry.ocrUtil;
  }

  public String getName() {
    return getClass().getSimpleName();
  }

  protected abstract Analyzer getDefaultAnalyzer();

  public abstract boolean direct();

  public void detectGameStatus() {
    String screenFilePath = commandUtil.capturePhoneScreen();
    GameState gameState = analyzer.detectGameState(screenFilePath);

    currentGameStatus = GameStatus.create(gameState, screenFilePath);
  }

  public void act() {
    try {
      listener.onStartDetectingGameStatus();
      detectGameStatus();
      listener.onGameStatusDetected(currentGameStatus);

      listener.onStartGivingDirection();
      boolean directSuccess = direct();
      listener.onGameStatusProcessed(currentGameStatus, directSuccess);
    } catch (Exception e) {
      listener.onException(e);
    }
  }

  public interface Listener {
    void onStartDetectingGameStatus();

    void onGameStatusDetected(GameStatus gameStatus);

    void onStartGivingDirection();

    void onGameStatusProcessed(GameStatus gameStatus, boolean success);

    void onException(Exception e);

    void onTryingToRefillEnergy();

    void onNoMoreRun();
  }
}
