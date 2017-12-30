package com.justin.swbot.util;

/**
 * Created by akivamu on 25/12/17.
 */

public class MemImage<T> {
    private final T image;

    public MemImage(T src) {
        this.image = src;
    }

    public T get() {
        return image;
    }
}
