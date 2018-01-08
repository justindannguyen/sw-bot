package com.justin.swbot.game;

import com.justin.swbot.game.director.Director;
import com.justin.swbot.game.profile.Profile;
import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Constructor;

public class AutoSession implements Director.Listener {
  private final Listener listener;
  @Getter
  private Report report;
  private final Director director;

  @Getter
  private State state;
  @Getter
  private Loop loop;

  public AutoSession(Listener listener, Profile profile, Class<? extends Director> directorClass) {
    this.listener = listener;

    // New Director instance
    Director directorInstance = null;
    try {
      Constructor<? extends Director> constructor = directorClass.getConstructor(Director.Listener.class, Profile.class);
      directorInstance = constructor.newInstance(this, profile);
    } catch (Exception e) {
      if (directorClass == null) {
        throw new IllegalArgumentException("Director class is null");
      } else {
        throw new IllegalArgumentException("Can't construct Director class: " + directorClass.getSimpleName());
      }
    }
    this.director = directorInstance;

    init();
  }

  private void init() {
    this.report = new Report();
    this.state = State.INIT;
  }

  public void start() {
    if (!state.equals(State.INIT)) throw new IllegalStateException("Session already started");

    this.loop = new Loop();
    state = State.RUNNING;
    loop.start();
  }

  public void pause() {
    if (!state.equals(State.RUNNING)) throw new IllegalStateException("Session not currently running");

    state = State.PAUSED;
  }

  public void resume() {
    if (!state.equals(State.PAUSED)) throw new IllegalStateException("Session not currently paused");

    state = State.RUNNING;
  }

  public void stop() {
    state = State.STOPPED;
  }

  public void restart() {
    if (!state.equals(State.INIT) && !state.equals(State.STOPPED))
      throw new IllegalStateException("Session already running");

    init();
    start();
  }

  @Override
  public void onStartDetectingGameStatus() {
    listener.onStartDetectingGameStatus();
  }

  @Override
  public void onGameStatusDetected(GameStatus gameStatus) {
    listener.onGameStatusDetected(gameStatus);
  }

  @Override
  public void onStartGivingDirection() {
    listener.onStartGivingDirection();
  }

  @Override
  public void onGameStatusProcessed(GameStatus gameStatus, boolean success) {
    GameState gameState = gameStatus.getGameState();
    switch (gameState) {
      case BATTLE_ENDED:
      case BATTLE_ENDED_FAIL: {
        report.setCompletedRuns(report.getCompletedRuns() + 1);
        if (GameState.BATTLE_ENDED.equals(gameState)) report.setSuccessRuns(report.getSuccessRuns() + 1);
        break;
      }
      case NO_CRYS:
        report.setRefillTimes(report.getRefillTimes() - 1);
        break;
    }

    listener.onGameStatusProcessed(gameStatus, success);
  }

  @Override
  public void onException(Exception e) {
    listener.onException(e);
  }

  @Override
  public void onTryingToRefillEnergy() {
    report.setRefillTimes(report.getRefillTimes() + 1);
  }

  @Override
  public void onNoMoreRun() {
    listener.onNoMoreRun();
  }

  private class Loop extends Thread {
    @Override
    public void run() {
      while (true) {

        switch (state) {
          case INIT: // Should never be here
          case PAUSED:
            break;
          case RUNNING: {
            director.act();
            break;
          }
          case STOPPED:
            listener.onSessionStopped();
            return; // End thread
        }

        try {
          Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
      }
    }
  }

  public enum State {
    INIT,
    RUNNING,
    PAUSED,
    STOPPED
  }

  @Data
  public static class Report {
    private int completedRuns;
    private int successRuns;
    private int refillTimes;
  }

  public interface Listener extends Director.Listener {
    void onSessionStopped();
  }
}
