package org.example;

import org.example.Garment.*;
import org.example.Garment.Material.*;

import java.util.ArrayList;

public class User {
  private ArrayList<Garment> items;

  public void UploadItem(TypeOfGarment type, TypeMaterial typeMaterial, Color mainColor, Color secondaryColor, Weft weft) {
    Material material = new Material(typeMaterial, weft, mainColor, secondaryColor);
    Garment item = new Garment(type, material);
    items.add(item);
  }

  public void uploadItem(TypeOfGarment type, TypeMaterial typeMaterial, Color mainColor, Weft weft) {
    UploadItem(type, typeMaterial,mainColor, null, weft );
  }
}