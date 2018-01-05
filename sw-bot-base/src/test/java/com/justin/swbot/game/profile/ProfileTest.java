package com.justin.swbot.game.profile;

import com.justin.swbot.game.indicator.Indicator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ProfileTest {
  private static final String PROFILES_FOLDER = "src/test/resources/profiles";

  @Test
  public void testGetIndicator() {
    ProfileManager profileManager = new ProfileManager();
    profileManager.setLocation(PROFILES_FOLDER);

    // Fail if profile not saved first
    Profile profile = profileManager.createEmptyProfile();
    try {
      profile.getIndicatorFile(Indicator.fiveStarRuneIndicator);
      Assert.fail("Should throw IllegalStateException");
    } catch (IllegalStateException ignored) {
    }

    // Load empty profile
    profile = profileManager.load("testProfileEmpty");
    File file = profile.getIndicatorFile(Indicator.fiveStarRuneIndicator);
    Assert.assertNotNull(file);
    Assert.assertEquals(Indicator.fiveStarRuneIndicator.name(), file.getName());

    // Load profile
    profile = profileManager.load("testProfile");
    file = profile.getIndicatorFile(Indicator.fiveStarRuneIndicator);
    Assert.assertNotNull(file);
    Assert.assertEquals(Indicator.fiveStarRuneIndicator.name(), file.getName());
  }
}
