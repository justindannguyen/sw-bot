package com.justin.swbot.util;

public class PcConverter {
  public static Point fromAwtPoint(java.awt.Point point) {
    return new Point(point.x, point.y);
  }

  public static java.awt.Point toAwtPoint(Point point) {
    return new java.awt.Point(point.x, point.y);
  }

  public static java.awt.Rectangle toAwtRectangle(Rectangle rectangle) {
    return new java.awt.Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
  }

  public static Rectangle fromAwtRectangle(java.awt.Rectangle rectangle) {
    return new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
  }
}
