package org.example;

import org.example.Garment.Category;
import org.example.Garment.Garment;
import org.example.Garment.Material.Color;
import org.example.Garment.Material.Material;
import org.example.Garment.Material.TypeMaterial;
import org.example.Garment.Material.Weft;
import org.example.Garment.TypeOfGarment;

public class Utils {
  public static Garment createGarment(String typeName, TypeMaterial material,
                                Color mainColor, Color secondaryColor, Weft weft) {
    TypeOfGarment type = new TypeOfGarment(typeName, Category.PARTE_SUPERIOR);
    Material mat = buildValidMaterial(material, weft, mainColor, secondaryColor);
    return new Garment(type, mat);
  }

  public static Material buildValidMaterial(TypeMaterial materialType, Weft weft, Color mainColor, Color secondaryColor) {
    if (mainColor == null) throw new NullPointerException("Main color cannot be null");
    if (Color.isInvalidColor(mainColor.toString())) throw new IllegalArgumentException("Invalid main color");
    if (secondaryColor != null && Color.isInvalidColor(secondaryColor.toString())) {
      throw new IllegalArgumentException("Invalid secondary color");
    }

    return new Material(materialType, weft, mainColor, secondaryColor);
  }
}
