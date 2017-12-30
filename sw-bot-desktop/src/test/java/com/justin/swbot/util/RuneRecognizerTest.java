package com.justin.swbot.util;

import com.justin.swbot.BaseTest;
import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.GameConfig;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class RuneRecognizerTest extends BaseTest {

  private RuneRecognizer runeRecognizer;

  @Before
  public void setup() {
    runeRecognizer = DependenciesRegistry.runeRecognizer;
    GameConfig.get().setRareLevelArea(1176, 350, 1325 - 1176, 405 - 350);

    // Ocr has native library and support only windows
    Assume.assumeTrue(System.getProperty("os.name").toLowerCase().startsWith("win"));
  }

  @Test
  public void testRareLevel() {
    final String sourceFile = "src/test/resources/sampleRuneReward.png";
    Assert.assertEquals("Rare", runeRecognizer.readRareLevel(sourceFile, GameConfig.get().getRareLevelAreaBox()));
  }
}
