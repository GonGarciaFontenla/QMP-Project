package org.example.Garment;


import org.example.Garment.Material.Color;
import org.example.Garment.Material.*;

import static java.util.Objects.requireNonNull;

public class Garment {
  private final TypeOfGarment type;
  private final Material material;
  private final Formality formalidad;

  public Garment(TypeOfGarment type, Material material, Formality formalidad) {
    this.type = requireNonNull(type, "Type cannot be null");
    this.material = requireNonNull(material, "Material cannot be null");
    this.formalidad = (formalidad != null)? formalidad : Formality.NEUTRA;
  }

  public TypeOfGarment getType() {
    return type;
  }

  public Material getMaterial() {
    return material;
  }

  public Formality getFormality() {
    return formalidad;
  }
}
