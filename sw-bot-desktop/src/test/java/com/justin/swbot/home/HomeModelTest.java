/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import com.justin.swbot.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.List;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class HomeModelTest extends BaseTest {
  private HomeModel instanceUnderTest;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    instanceUnderTest = new HomeModel();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    instanceUnderTest = null;
  }

  /**
   * Test method for {@link com.justin.swbot.home.HomeModel#getProfiles()}.
   */
  @Test
  public void testGetProfiles_emptyByDefault() {
    Assert.assertTrue(instanceUnderTest.getProfiles().isEmpty());
  }

  /**
   * Test method for {@link HomeModel#getDirectors()} ()}.
   */
  @Test
  public void testGetScenarios_emptyByDefault() {
    Assert.assertTrue(instanceUnderTest.getDirectors().isEmpty());
  }

  @Test
  public void testLoadData_notifyObserverNotCall_whenNoObservers() {
    final HomeModelListener observer = Mockito.mock(HomeModelListener.class);
    instanceUnderTest.loadData();
    Mockito.verify(observer, Mockito.never()).update(ArgumentMatchers.eq(instanceUnderTest),
        ArgumentMatchers.anyString());
  }

  @Test
  public void testLoadData_oldProfilesAreCleared_onReloaded() {
    instanceUnderTest.loadData();
    instanceUnderTest.loadData();
    final List<String> profiles = instanceUnderTest.getProfiles();
    final String duplicatedProfile = profiles.stream()
        .reduce(null,
            (duplicated, profile) -> duplicated != null ?
                duplicated
                :
                searchDuplicatedProfile(profiles, profile));

    Assert.assertNull(duplicatedProfile);
  }

  /**
   * Test method for {@link com.justin.swbot.home.HomeModel#loadData()}.
   */
  @Test
  public void testLoadData_profiles() {
    instanceUnderTest.loadData();
    Assert.assertFalse(instanceUnderTest.getProfiles().isEmpty());
  }

  @Test
  public void testLoadData_profiles_notifyObserver() {
    final HomeModelListener observer = Mockito.mock(HomeModelListener.class);
    instanceUnderTest.addObserver(observer);
    instanceUnderTest.loadData();

    Mockito.verify(observer).update(instanceUnderTest, HomeModel.PROFILES_LOADED);
  }

  @Test
  public void testLoadData_scenarios() {
    instanceUnderTest.loadData();
    Assert.assertFalse(instanceUnderTest.getDirectors().isEmpty());
  }

  @Test
  public void testLoadData_scenarios_notifyObserver() {
    final HomeModelListener observer = Mockito.mock(HomeModelListener.class);
    instanceUnderTest.addObserver(observer);
    instanceUnderTest.loadData();

    Mockito.verify(observer).update(instanceUnderTest, HomeModel.DIRECTORS_LOADED);
  }

  /**
   * Search in the profiles store to check if given profile exist twice or not.
   *
   * @param profiles        profile store.
   * @param profileToSearch profile to search
   * @return the duplicated profile if exist twice in store
   */
  private String searchDuplicatedProfile(final List<String> profiles, final String profileToSearch) {
    final boolean duplicated =
        profiles.stream().filter(profile -> profile.equals(profileToSearch)).count() > 1;
    return duplicated ? profileToSearch : null;
  }
}
