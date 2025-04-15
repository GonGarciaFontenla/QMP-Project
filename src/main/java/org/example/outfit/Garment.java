package org.example.outfit;


import static java.util.Objects.requireNonNull;

public class Garment {
  private final TypeOfGarment type;
  private final Material material;
  private final String mainColor;
  private final String secondaryColor;

  public Garment(TypeOfGarment type, Material material, String mainColor, String secondaryColor) {
    if (Color.isInvalidColor(mainColor)) {
      throw new IllegalArgumentException("Invalid main color");
    }

    if (secondaryColor != null && Color.isInvalidColor(secondaryColor)) {
      throw new IllegalArgumentException("Invalid secondary color");
    }

    this.type = requireNonNull(type, "Type cannot be null");
    this.material = requireNonNull(material, "Material cannot be null");
    this.mainColor = requireNonNull(mainColor, "Main color cannot be null");
    this.secondaryColor = secondaryColor;
  }

  public TypeOfGarment getType() {
    return type;
  }

  public Material getMaterial() {
    return material;
  }

  public String getMainColor() {
    return mainColor;
  }

  public String getSecondaryColor() {
    return secondaryColor;
  }
}
