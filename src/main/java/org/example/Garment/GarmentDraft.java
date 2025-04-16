package org.example.Garment;

import org.example.Garment.Material.*;
import org.example.User;

public class GarmentDraft {
  private TypeOfGarment type;
  private Material material;

  public void type(TypeOfGarment type) {
    this.type = type;
  }

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
    return new Garment(type, material);
  }
}