/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import com.justin.swbot.util.MemImage;

import java.io.File;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface OcrUtil {
    String text(final MemImage imageFile);

    String text(final File imageFile);
}
