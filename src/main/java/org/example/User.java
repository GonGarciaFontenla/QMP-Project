package org.example;

import org.example.Garment.*;
import java.util.ArrayList;

public class User {
  private ArrayList<Garment> closet;
  private GarmentDraft draft;

  public User() {
    this.closet = new ArrayList<>();
  }

  public void UploadItem(Garment prenda) {
    this.closet.add(prenda);
  }

  public ArrayList<Garment> GetItems() {
    return closet;
  }

  public void ConfirmDraft() {
    if (draft != null && draft.isComplete()) {
      Garment garment = draft.build();
      this.closet.add(garment);
      ClearDraft(); // Clear the draft after confirming
    } else {
      throw new IllegalStateException("Draft is not complete");
    }
  }

  public GarmentDraft GetDraft() {
    if (draft == null) {
      this.draft = new GarmentDraft();
    }
    return this.draft;
  }

  public void ClearDraft() {
    this.draft = new GarmentDraft();
  }
}