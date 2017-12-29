package com.justin.swbot.util;

import java.io.File;
import java.io.IOException;

/**
 * Created by akivamu on 26/12/17.
 */
public interface MemImageUtil {
    MemImage loadImage(final File imageFile) throws IOException;

    void storeImage(final MemImage image, final File location) throws IOException;
}
