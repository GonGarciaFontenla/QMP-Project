package org.example.Prenda;


import org.example.Prenda.Material.*;

import static java.util.Objects.requireNonNull;

public class Prenda {
  private final TipoDePrenda type;
  private final Material material;
  private final Formalidad formalidad;

  public Prenda(TipoDePrenda type, Material material, Formalidad formalidad) {
    this.type = requireNonNull(type, "Type cannot be null");
    this.material = requireNonNull(material, "Material cannot be null");
    this.formalidad = (formalidad != null)? formalidad : Formalidad.NEUTRA;
  }

  public TipoDePrenda getType() {
    return type;
  }

  public Material getMaterial() {
    return material;
  }

  public Formalidad getFormality() {
    return formalidad;
  }
}
