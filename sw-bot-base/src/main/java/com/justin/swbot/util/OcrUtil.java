/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import java.io.File;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface OcrUtil {
    String text(final MemImage imageFile, Rectangle box);

    String text(final File imageFile, Rectangle box);
}
