package org.example.Garment.Material;

public class Color {
  private final java.awt.Color awtColor;

  public Color(String colorName) {
    java.awt.Color parsedColor;
    try {
      parsedColor = parseColor(colorName);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid color: " + colorName);
    }
    this.awtColor = parsedColor;
  }

  public java.awt.Color getAwtColor() {
    return awtColor;
  }

  public static boolean isInvalidColor(String colorName) {
    try {
      parseColor(colorName);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static java.awt.Color parseColor(String colorName) throws Exception {
    try {
      return java.awt.Color.decode(colorName); // #RRGGBB or 0xRRGGBB
    } catch (NumberFormatException e) {
      return (java.awt.Color) java.awt.Color.class.getField(colorName.toLowerCase()).get(null);
    }
  }

  @Override
  public String toString() {
    return awtColor.toString();
  }
}

