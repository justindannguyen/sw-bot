/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import java.io.File;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import com.justin.swbot.game.Profile;
import com.justin.swbot.game.director.ScenarioDirector;

/**
 * Central place to store all UI data for the {@link HomeUI}.
 *
 * @author tuan3.nguyen@gmail.com
 */
public final class HomeModel extends Observable {
  /**
   * Notification event key.
   */
  public static final String PROFILES_LOADED = "PROFILES_LOADED";
  /**
   * Notification event key.
   */
  public static final String SCENARIOS_LOADED = "SCENARIOS_LOADED";

  public static final String PROFILE_SELECTED = "PROFILE_SELECTED";

  public static final String SCENARIO_SELECTED = "SCENARIO_SELECTED";

  /**
   * Store the target phone to run the bot e.g. samsung galaxy s3, sony z1, etc. User has to
   * configure new phone profile, basically it contains the mouse click location and screen shot
   * identification.
   */
  private final List<String> profiles = new ArrayList<>();
  /**
   * Store all the available {@link ScenarioDirector}.
   */
  private final List<SimpleImmutableEntry<String, ScenarioDirector>> scenarios = new ArrayList<>();

  private String selectedProfile;

  private String selectedScenario;

  /**
   * Get all available profiles.
   *
   * @return the profiles
   */
  public List<String> getProfiles() {
    return profiles;
  }

  /**
   * Get all available scenario director
   *
   * @return the scenarios
   */
  public List<SimpleImmutableEntry<String, ScenarioDirector>> getScenarios() {
    return scenarios;
  }

  public String getSelectedProfile() {
    return selectedProfile;
  }

  public String getSelectedScenario() {
    return selectedScenario;
  }

  /**
   * (Re)load all data for home screen.
   */
  public void loadData() {
    loadScenarios();
    loadProfiles();
  }

  public int setSelectedProfile(final String profile) {
    selectedProfile = profile;
    setChanged();
    notifyObservers(PROFILE_SELECTED);
    return profiles.indexOf(profile);
  }

  public void setSelectedScenario(final String selectedScenario) {
    this.selectedScenario = selectedScenario;
    setChanged();
    notifyObservers(PROFILE_SELECTED);
  }

  /**
   * Load all configured profiles in the store.
   */
  private void loadProfiles() {
    profiles.clear();
    profiles.add("--Select profile--");
    profiles.add("Configure new profile...");
    final File profilesFolder = Profile.get().getProfilesFolder();
    if (profilesFolder.exists()) {
      profiles.addAll(Arrays.stream(profilesFolder.listFiles(file -> file.isDirectory()))
          .map(file -> file.getName()).collect(Collectors.toList()));
    }
    setChanged();
    notifyObservers(PROFILES_LOADED);

    // Configure new profile if there is no
    if (profiles.size() == 2) {
      setSelectedProfile(profiles.get(1));
    }
    final String profileName = Profile.get().getProfileName();
    if (profileName != null && profiles.contains(profileName)) {
      setSelectedProfile(profileName);
    }
  }

  /**
   * Load all installed scenario director in system class path.
   */
  private void loadScenarios() {
    scenarios.clear();
    scenarios.add(new SimpleImmutableEntry<>("--Select scenario--", null));
    ServiceLoader.load(ScenarioDirector.class).forEach(director -> scenarios
        .add(new SimpleImmutableEntry<>(director.getName(), director)));
    setChanged();
    notifyObservers(SCENARIOS_LOADED);
  }
}
