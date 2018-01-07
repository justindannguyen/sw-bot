package com.justin.swbot.game.analyzer;

import com.justin.swbot.game.GameState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by akivamu on 07/01/18.
 */
public class DungeonAnalyzerTest {
  private static final String INDICATORS_DIR_PATH = "src/test/resources/profiles/testProfile";
  private Analyzer analyzer = new Analyzer();

  @Before
  public void setup() {
    analyzer.setIndicatorsDir(INDICATORS_DIR_PATH);
  }

  @Test
  public void testDetectFails() {
    Analyzer analyzer = new Analyzer();

    // Not setup indicators path
    Assert.assertEquals(GameState.UNKNOWN, analyzer.detectGameState("src/test/resources/sampleRuneReward.png"));

    // Non exist screen shot file
    Assert.assertEquals(GameState.UNKNOWN, analyzer.detectGameState("src/test/resources/NOT_EXIST_FILE"));
  }

  @Test
  public void testDetectRuneRewardState() {
    Assert.assertEquals(GameState.RUNE_REWARD, analyzer.detectGameState("src/test/resources/sampleRuneReward.png"));
  }
}
