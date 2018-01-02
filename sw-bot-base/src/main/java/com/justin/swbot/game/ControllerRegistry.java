/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tuan3.nguyen@gmail.com
 */
public class ControllerRegistry {
  private static Map<String, Controller> controllers = new HashMap<String, Controller>();

  public static Controller get(final Class<? extends Controller> clazz) {
    return controllers.get(clazz.getSimpleName());
  }

  public static void register(final Controller controller) {
    controllers.put(controller.getClass().getSimpleName(), controller);
  }

  public static void unregister(final Controller controller) {
    controllers.remove(controller.getClass().getSimpleName());
  }
}
