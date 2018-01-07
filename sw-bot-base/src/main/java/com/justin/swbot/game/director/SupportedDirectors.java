package com.justin.swbot.game.director;

import java.util.HashMap;
import java.util.Map;

public class SupportedDirectors {
  private static final Map<String, Class<? extends Director>> directors = new HashMap<String, Class<? extends Director>>();

  static {
    add(RuneFarmingDirector.class);
    add(RiftDungeonDirector.class);
  }

  private static void add(Class<? extends Director> clazz) {
    directors.put(clazz.getSimpleName(), clazz);
  }

  public static Map<String, Class<? extends Director>> get() {
    return directors;
  }
}
