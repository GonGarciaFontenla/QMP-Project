package org.example;

import org.example.Prenda.Categoria;
import org.example.Prenda.Formalidad;
import org.example.Prenda.Prenda;
import org.example.Prenda.Material.Color;
import org.example.Prenda.Material.Material;
import org.example.Prenda.Material.TypeMaterial;
import org.example.Prenda.Material.Weft;
import org.example.Prenda.TipoDePrenda;

import java.util.ArrayList;

public class Utils {
  public static Prenda createGarment(String typeName, Categoria categoria, TypeMaterial material,
                                     Color mainColor, Color secondaryColor, Weft weft, Formalidad formalidad) {
    TipoDePrenda type = new TipoDePrenda(typeName, categoria);
    Material mat = buildValidMaterial(material, weft, mainColor, secondaryColor);
    return new Prenda(type, mat, formalidad);
  }

  public static Material buildValidMaterial(TypeMaterial materialType, Weft weft, Color mainColor, Color secondaryColor) {
    if (mainColor == null) throw new NullPointerException("Main color cannot be null");
    if (Color.isInvalidColor(mainColor.toString())) throw new IllegalArgumentException("Invalid main color");
    if (secondaryColor != null && Color.isInvalidColor(secondaryColor.toString())) {
      throw new IllegalArgumentException("Invalid secondary color");
    }

    return new Material(materialType, weft, mainColor, secondaryColor);
  }

  public static ArrayList<Prenda> createCloset() {
    ArrayList<Prenda> allPrendas = new ArrayList<>();

    allPrendas.add(Utils.createGarment("remera", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), null, null, Formalidad.INFORMAL));
    allPrendas.add(Utils.createGarment("pantalon", Categoria.PARTE_INFERIOR, TypeMaterial.CUERO, new Color("blue"), null, null, Formalidad.INFORMAL));
    allPrendas.add(Utils.createGarment("zapatillas", Categoria.CALZADO, TypeMaterial.CUERO, new Color("blue"), null, null, Formalidad.NEUTRA));
    allPrendas.add(Utils.createGarment("remera", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), null, null, Formalidad.FORMAL));
    allPrendas.add(Utils.createGarment("pantalon", Categoria.PARTE_INFERIOR, TypeMaterial.ACETATO, new Color("blue"), null, null, Formalidad.FORMAL));
    allPrendas.add(Utils.createGarment("zapatillas", Categoria.CALZADO, TypeMaterial.CUERO, new Color("blue"), null, null, Formalidad.FORMAL));

    return allPrendas;
  }
}
