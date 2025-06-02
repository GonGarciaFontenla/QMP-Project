package org.example.Prenda;

import org.example.Prenda.Material.*;

public class BorradorPrenda {
  private TipoDePrenda type;
  private Material material;
  private Formalidad formalidad;

  public void type(TipoDePrenda type) {
    this.type = type;
  }

  public void formalidad(Formalidad formalidad) {this.formalidad = formalidad; }

  public void material(Material material) {
    this.material = material;
  }

  public void typeMaterial(TypeMaterial type) {
    this.material.setTypeMaterial(type);
  }

  public void weft(Weft weft) {
    this.material.setWeft(weft);
  }

  public void mainColor(Color color) {
    this.material.setMainColor(color);
  }

  public void secondaryColor(Color color) {
    this.material.setSecondaryColor(color);
  }

  public boolean isComplete() {
    return type != null && material != null;
  }

  public Prenda build() {
    if (!isComplete()) {
      throw new IllegalStateException("Garment is not complete");
    }
    return new Prenda(type, material, formalidad);
  }
}