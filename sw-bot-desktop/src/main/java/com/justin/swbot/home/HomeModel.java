/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import com.justin.swbot.dependencies.DependenciesRegistry;
import com.justin.swbot.game.director.Director;
import com.justin.swbot.game.director.SupportedDirectors;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

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
  public static final String DIRECTORS_LOADED = "DIRECTORS_LOADED";

  public static final String PROFILE_SELECTED = "PROFILE_SELECTED";

  public static final String DIRECTOR_SELECTED = "DIRECTOR_SELECTED";

  /**
   * Store the target phone to run the bot e.g. samsung galaxy s3, sony z1, etc. User has to
   * configure new phone profile, basically it contains the mouse click location and screen shot
   * identification.
   */
  private final List<String> profiles = new ArrayList<>();
  /**
   * Store all the available {@link Director}.
   */
  @Getter
  private final Map<String, Class<? extends Director>> directors = new HashMap<>();

  private String selectedProfile;

  private String selectedDirector;

  /**
   * Get all available profiles.
   *
   * @return the profiles
   */
  public List<String> getProfiles() {
    return profiles;
  }

  public String getSelectedProfile() {
    return selectedProfile;
  }

  public String getSelectedDirector() {
    return selectedDirector;
  }

  /**
   * (Re)load all data for home screen.
   */
  public void loadData() {
    loadDirectorNames();
    loadProfiles();
  }

  public int setSelectedProfile(final String profile) {
    selectedProfile = profile;
    setChanged();
    notifyObservers(PROFILE_SELECTED);
    return profiles.indexOf(profile);
  }

  public void setSelectedDirector(final String selectedDirector) {
    this.selectedDirector = selectedDirector;
    setChanged();
    notifyObservers(DIRECTOR_SELECTED);
  }

  /**
   * Load all configured profiles in the store.
   */
  private void loadProfiles() {
    profiles.clear();
    profiles.add("--Select profile--");
    profiles.add("Configure new profile...");
    profiles.addAll(DependenciesRegistry.profileManager.getProfileNames());
    setChanged();
    notifyObservers(PROFILES_LOADED);

    // Configure new profile if there is no
    if (profiles.size() == 2) {
      setSelectedProfile(profiles.get(1));
    }
  }

  /**
   * Load all installed scenario director in system class path.
   */
  private void loadDirectorNames() {
    directors.clear();
    directors.putAll(SupportedDirectors.get());
    setChanged();
    notifyObservers(DIRECTORS_LOADED);
  }
}
