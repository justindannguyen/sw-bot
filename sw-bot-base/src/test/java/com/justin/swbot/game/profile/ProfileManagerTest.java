package com.justin.swbot.game.profile;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ProfileManagerTest {
  private static final String PROFILES_FOLDER = "src/test/resources/profiles";

  @Test
  public void testSetLocation() {
    ProfileManager profileManager = new ProfileManager();
    try {
      profileManager.setLocation(null);
      Assert.fail("Should throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      profileManager.setLocation("");
      Assert.fail("Should throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {
    }

    profileManager.setLocation(PROFILES_FOLDER);
  }

  @Test
  public void testCreateEmptyProfile() {
    ProfileManager profileManager = new ProfileManager();
    Profile profile = profileManager.createEmptyProfile();

    Assert.assertNull(profile.getName());
    Assert.assertNull(profile.getPath());
    Assert.assertNull(profile.getSellGemLocation());
    Assert.assertNull(profile.getRareLevelBox());
    Assert.assertEquals(0, profile.getRefillTimes());

    // Default values
    Assert.assertEquals(true, profile.isRandomClick());
    Assert.assertEquals(true, profile.isRunLogging());
    Assert.assertEquals(true, profile.isPickAllRunes());
  }

  @Test
  public void testStates() {
    ProfileManager profileManager = new ProfileManager();

    // location not set
    try {
      profileManager.load("test");
      Assert.fail("Should throw IllegalStateException");
    } catch (IllegalStateException ignored) {
    }

    profileManager.setLocation(".");
    profileManager.load("test");
  }

  @Test
  public void testGetListProfileNames() {
    ProfileManager profileManager = new ProfileManager();
    profileManager.setLocation(PROFILES_FOLDER);

    List<String> names = profileManager.getProfileNames();
    Assert.assertEquals(2, names.size());
  }

  @Test
  public void testLoad() {
    ProfileManager profileManager = new ProfileManager();
    profileManager.setLocation(PROFILES_FOLDER);

    // Load invalid name
    try {
      profileManager.load(null);
      Assert.fail("Should throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      profileManager.load("");
      Assert.fail("Should throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {
    }

    // Load non exist profile
    Assert.assertNull(profileManager.load("blahblah"));

    // Load test profile (empty)
    Profile profile = profileManager.load("testProfileEmpty");
    Assert.assertNotNull(profile);
    Assert.assertEquals("testProfileEmpty", profile.getName());
    Assert.assertEquals(new File(PROFILES_FOLDER + "/testProfileEmpty").getAbsolutePath(), profile.getPath());
    Assert.assertEquals(0, profile.getRefillTimes());
    Assert.assertEquals(true, profile.isRandomClick());
    Assert.assertEquals(true, profile.isRunLogging());
    Assert.assertEquals(true, profile.isPickAllRunes());

    // Load test profile
    profile = profileManager.load("testProfile");
    Assert.assertNotNull(profile);
    Assert.assertEquals("testProfile", profile.getName());
    Assert.assertEquals(new File(PROFILES_FOLDER + "/testProfile").getAbsolutePath(), profile.getPath());
    Assert.assertEquals(10, profile.getRefillTimes());
    Assert.assertEquals(false, profile.isRandomClick());
    Assert.assertEquals(true, profile.isRunLogging());
    Assert.assertEquals(true, profile.isPickAllRunes());
  }

  @Test
  public void testSave() {
    ProfileManager profileManager = new ProfileManager();
    profileManager.setLocation(PROFILES_FOLDER);

    // Null check
    try {
      profileManager.saveProfile(null);
      Assert.fail("Should throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {
    }

    Profile profile = profileManager.createEmptyProfile();

    // Before set name
    try {
      profileManager.saveProfile(profile);
      Assert.fail("Should throw IllegalStateException");
    } catch (IllegalStateException ignored) {
    }

    // Empty name
    try {
      profile.setName("");
      profileManager.saveProfile(profile);
      Assert.fail("Should throw IllegalStateException");
    } catch (IllegalStateException ignored) {
    }

    // Valid infos
    String profileName = "testNewProfile";
    profile.setName(profileName);
    profile.setRandomClick(false);
    profile.setRunLogging(true);
    profile.setRefillTimes(100);

    profileManager.saveProfile(profile);

    // Saved profile has path
    Assert.assertEquals(new File(PROFILES_FOLDER + "/" + profileName).getAbsolutePath(), profile.getPath());

    // Load profiles
    Profile loadedProfile = profileManager.load(profileName);
    Assert.assertEquals(profileName, loadedProfile.getName());
    Assert.assertEquals(false, loadedProfile.isRandomClick());
    Assert.assertEquals(true, loadedProfile.isRunLogging());
    Assert.assertEquals(100, loadedProfile.getRefillTimes());

    // Clean up
    new File(PROFILES_FOLDER + "/" + profileName + "/props.json").delete();
    new File(PROFILES_FOLDER + "/" + profileName).delete();
  }
}
