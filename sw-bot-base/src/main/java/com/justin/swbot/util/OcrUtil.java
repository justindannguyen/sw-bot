/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.util;

import java.io.File;

/**
 * @author tuan3.nguyen@gmail.com
 */
public interface OcrUtil {
    /**
     * @deprecated Avoid using MemImage, due to platform dependent.
     */
    @Deprecated
    String text(final MemImage imageFile, Rectangle box);

    String text(final File imageFile, Rectangle box);
}
