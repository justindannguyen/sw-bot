package com.justin.swbot.game.profile;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;

import static com.justin.swbot.util.FileUtil.readTextFile;
import static com.justin.swbot.util.FileUtil.writeTextFile;

/**
 * Created by akivamu on 05/01/18.
 */
public class ProfileManager {
  private String profilesLocation;

  public void setLocation(String path) {
    if (path == null || path.length() == 0) {
      throw new IllegalArgumentException("Invalid profiles folder: " + path);
    }

    this.profilesLocation = path;
  }

  private File getProfileDir(String profileName) {
    if (profileName == null || profileName.length() == 0) {
      throw new IllegalArgumentException("Invalid profile name: " + profileName);
    }

    if (profilesLocation == null || profilesLocation.length() == 0) {
      throw new IllegalStateException("Must set profiles folder first");
    }

    return new File(profilesLocation + "/" + profileName);
  }

  public Profile createEmptyProfile() {
    return new Profile();
  }

  public Profile load(String profileName) {
    File profileDir = getProfileDir(profileName);
    if (!profileDir.exists()) return null;

    Profile profile = null;

    // Load props
    File propFile = new File(profileDir.getAbsolutePath() + "/props.json");
    if (propFile.exists()) {
      String content = readTextFile(propFile.getAbsolutePath());
      Gson gson = new Gson();
      try {
        profile = gson.fromJson(content, Profile.class);
      } catch (JsonSyntaxException ignored) {
      }
    }
    if (profile == null) profile = new Profile();
    profile.setName(profileName);
    profile.setPath(profileDir.getAbsolutePath());
    return profile;
  }

  public void saveProfile(Profile profile) {
    if (profile == null) {
      throw new IllegalArgumentException("Null profile");
    }

    if (profile.getName() == null || profile.getName().length() == 0) {
      throw new IllegalStateException("Profile name is empty");
    }

    File profileDir = getProfileDir(profile.getName());
    if (profile.getPath() == null) {
      profile.setPath(profileDir.getAbsolutePath());
    }

    if (!profileDir.exists()) profileDir.mkdirs();

    String json = new Gson().toJson(profile);

    writeTextFile(profileDir.getAbsolutePath() + "/props.json", json);
  }
}
