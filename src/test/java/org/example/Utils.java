package org.example;

import org.example.Garment.Category;
import org.example.Garment.Formality;
import org.example.Garment.Garment;
import org.example.Garment.Material.Color;
import org.example.Garment.Material.Material;
import org.example.Garment.Material.TypeMaterial;
import org.example.Garment.Material.Weft;
import org.example.Garment.TypeOfGarment;

import java.util.ArrayList;

public class Utils {
  public static Garment createGarment(String typeName, Category categoria, TypeMaterial material,
                                Color mainColor, Color secondaryColor, Weft weft, Formality formalidad) {
    TypeOfGarment type = new TypeOfGarment(typeName, categoria);
    Material mat = buildValidMaterial(material, weft, mainColor, secondaryColor);
    return new Garment(type, mat, formalidad);
  }

  public static Material buildValidMaterial(TypeMaterial materialType, Weft weft, Color mainColor, Color secondaryColor) {
    if (mainColor == null) throw new NullPointerException("Main color cannot be null");
    if (Color.isInvalidColor(mainColor.toString())) throw new IllegalArgumentException("Invalid main color");
    if (secondaryColor != null && Color.isInvalidColor(secondaryColor.toString())) {
      throw new IllegalArgumentException("Invalid secondary color");
    }

    return new Material(materialType, weft, mainColor, secondaryColor);
  }

  public static ArrayList<Garment> createCloset() {
    ArrayList<Garment> allGarments = new ArrayList<>();

    allGarments.add(Utils.createGarment("remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), null, null, Formality.INFORMAL));
    allGarments.add(Utils.createGarment("pantalon", Category.PARTE_INFERIOR, TypeMaterial.CUERO, new Color("blue"), null, null, Formality.INFORMAL));
    allGarments.add(Utils.createGarment("zapatillas", Category.CALZADO, TypeMaterial.CUERO, new Color("blue"), null, null, Formality.NEUTRA));
    allGarments.add(Utils.createGarment("remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), null, null, Formality.FORMAL));
    allGarments.add(Utils.createGarment("pantalon", Category.PARTE_INFERIOR, TypeMaterial.ACETATO, new Color("blue"), null, null, Formality.FORMAL));
    allGarments.add(Utils.createGarment("zapatillas", Category.CALZADO, TypeMaterial.CUERO, new Color("blue"), null, null, Formality.FORMAL));

    return allGarments;
  }
}
