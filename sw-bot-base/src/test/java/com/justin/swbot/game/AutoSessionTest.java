package com.justin.swbot.game;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.director.Director;
import com.justin.swbot.game.director.RuneFarmingDirector;
import com.justin.swbot.game.profile.Profile;
import com.justin.swbot.util.CommandUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AutoSessionTest {
  private AutoSession.Listener listener = new AutoSession.Listener() {

    @Override
    public void onGameStateUpdate(GameState gameState) {

    }

    @Override
    public void onMessageUpdate(String message) {

    }

    @Override
    public void onSessionStopped() {

    }
  };

  private Profile profile = mock(Profile.class);

  @Before
  public void setup() {
    profile.setPath("src/test/resources/profiles/testProfile");
  }

  @Test
  public void testInit() {

    // null director class
    try {
      new AutoSession(listener, profile, null);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }

    // Invalid director class
    try {
      new AutoSession(listener, profile, Director.class);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }

  }

  @Test
  public void testStates() throws InterruptedException {
    AutoSession autoSession = new AutoSession(listener, profile, RuneFarmingDirector.class);

    // init
    Assert.assertEquals(AutoSession.State.INIT, autoSession.getState());

    // not started
    try {
      autoSession.pause();
      Assert.fail();
    } catch (IllegalStateException ignored) {
    }

    try {
      autoSession.resume();
      Assert.fail();
    } catch (IllegalStateException ignored) {
    }

    // start
    autoSession.start();
    Assert.assertEquals(AutoSession.State.RUNNING, autoSession.getState());

    Thread loopThread = autoSession.getLoop();
    Assert.assertTrue(loopThread.isAlive());

    // pause
    autoSession.pause();
    Assert.assertEquals(AutoSession.State.PAUSED, autoSession.getState());
    Assert.assertTrue(loopThread.isAlive());

    // resume
    autoSession.resume();
    Assert.assertEquals(AutoSession.State.RUNNING, autoSession.getState());
    Assert.assertTrue(loopThread.isAlive());

    // start again failed
    try {
      autoSession.start();
      Assert.fail();
    } catch (IllegalStateException ignored) {
    }

    // stop
    autoSession.stop();
    Assert.assertEquals(AutoSession.State.STOPPED, autoSession.getState());
    Thread.sleep(1000);
    Assert.assertFalse(loopThread.isAlive());

    // start again failed
    try {
      autoSession.start();
      Assert.fail();
    } catch (IllegalStateException ignored) {
    }

    // restart
    autoSession.restart();
    Assert.assertEquals(AutoSession.State.RUNNING, autoSession.getState());
  }

  @Test
  public void testSampleState() throws InterruptedException {
    String screenFilePath = "src/test/resources/sampleRuneReward.png";

    // Setup - CommandUtil
    CommandUtil commandUtil = mock(CommandUtil.class);
    when(commandUtil.capturePhoneScreen()).thenReturn(screenFilePath);
    DependenciesRegistry.commandUtil = commandUtil;

    // Start session
    AutoSession autoSession = new AutoSession(listener, profile, RuneFarmingDirector.class);
    autoSession.start();
    Thread.sleep(1000);

    verify(commandUtil, atLeastOnce()).capturePhoneScreen();
  }
}
