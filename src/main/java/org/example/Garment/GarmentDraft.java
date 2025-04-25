package org.example.Garment;

import org.example.Garment.Material.*;

public class GarmentDraft {
  private TypeOfGarment type;
  private Material material;
  private Formality formalidad;

  public void type(TypeOfGarment type) {
    this.type = type;
  }

  public void formalidad(Formality formalidad) {this.formalidad = formalidad; }

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

  public Garment build() {
    if (!isComplete()) {
      throw new IllegalStateException("Garment is not complete");
    }
    return new Garment(type, material, formalidad);
  }
}