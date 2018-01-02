/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import java.io.File;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface OcrUtil {
  String text(final File imageFile);

  String text(final File imageFile, final Rectangle box);
}
