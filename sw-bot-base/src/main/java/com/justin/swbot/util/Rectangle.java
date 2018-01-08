package com.justin.swbot.util;


public class Rectangle {
  public int x, y, width, height;

  public Rectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public int getCenterX() {
    return x + width / 2;
  }

  public int getCenterY() {
    return y + height / 2;
  }
}
