package org.example.outfit;

public class Color {
  public static boolean isInvalidColor(String colorName) {
    try {
      java.awt.Color.decode(colorName);
      return false;
    } catch (NumberFormatException e) {
      try {
        java.awt.Color color = (java.awt.Color) java.awt.Color.class.getField(colorName.toLowerCase()).get(null);
        return color == null;
      } catch (Exception ex) {
        return true;
      }
    }
  }
}
