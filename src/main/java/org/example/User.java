package org.example;

import org.example.outfit.*;

import java.util.ArrayList;

public class User {
  private ArrayList<Garment> items;

  public void UploadItem(TypeOfGarment type, Material material, String mainColor, String secondaryColor) {
    Garment item = new Garment(type, material, mainColor, secondaryColor);
    items.add(item);
  }

  public void uploadItem(TypeOfGarment type, Material material, String mainColor) {
    UploadItem(type, material, mainColor, null);
  }
}