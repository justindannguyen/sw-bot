/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import com.justin.swbot.game.director.ScenarioDirector;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ServiceLoader;

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

    /**
     * Store the target phone to run the bot e.g. samsung galaxy s3, sony z1, etc. User has to configure new phone
     * profile, basically it contains the mouse click location and screen shot identification.
     */
    private final List<String> profiles = new ArrayList<>();
    /**
     * Store all the available {@link ScenarioDirector}.
     */
    private final List<SimpleImmutableEntry<String, Object>> scenarios = new ArrayList<>();

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
    public List<SimpleImmutableEntry<String, Object>> getScenarios() {
        return scenarios;
    }

    /**
     * (Re)load all data for home screen.
     */
    public void loadData() {
        loadScenarios();
        loadProfiles();
    }

    /**
     * Load all configured profiles in the store.
     */
    private void loadProfiles() {
        profiles.clear();
        profiles.add("--Select profile--");
        profiles.add("Configure new profile...");
        setChanged();
        notifyObservers(PROFILES_LOADED);
    }

    /**
     * Load all installed scenario director in system class path.
     */
    private void loadScenarios() {
        scenarios.clear();
        scenarios.add(new SimpleImmutableEntry<String, Object>("--Select scenario--", null));
        ServiceLoader.load(ScenarioDirector.class).forEach(
            director -> scenarios.add(new SimpleImmutableEntry<String, Object>(director.getName(), director)));
        setChanged();
        notifyObservers(SCENARIOS_LOADED);
    }
}
