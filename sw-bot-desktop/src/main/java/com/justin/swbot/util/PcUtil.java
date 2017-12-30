package com.justin.swbot.util;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PcUtil {
  public static Rectangle castToRectangle(Object object) {
    if (object instanceof Rectangle) {
      return (Rectangle) object;
    } else if (object instanceof com.justin.swbot.util.Rectangle) {
      com.justin.swbot.util.Rectangle value = (com.justin.swbot.util.Rectangle) object;
      return new Rectangle(value.x, value.y, value.width, value.height);
    }
    return null;
  }

  public static com.justin.swbot.util.Rectangle toGeneric(Rectangle src) {
    return new com.justin.swbot.util.Rectangle(src.x, src.y, src.width, src.height);
  }

  public static Point castToPoint(Object object) {
    if (object instanceof Point) {
      return (Point) object;
    } else if (object instanceof com.justin.swbot.util.Point) {
      com.justin.swbot.util.Point value = (com.justin.swbot.util.Point) object;
      return new Point(value.x, value.y);
    }
    return null;
  }

  public static com.justin.swbot.util.Point toGeneric(Point src) {
    return new com.justin.swbot.util.Point(src.x, src.y);
  }

  public static BufferedImage castToBufferedImage(Object object) {
    if (object instanceof BufferedImage) {
      return (BufferedImage) object;
    } else if (object instanceof MemImage) {
      MemImage value = (MemImage) object;
      return (BufferedImage) value.get();
    }
    return null;
  }

  public static MemImage toGeneric(BufferedImage src) {
    return new MemImage<>(src);
  }
}
