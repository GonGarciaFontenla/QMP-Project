package org.example;

import org.example.Prenda.*;
import java.util.ArrayList;
import java.util.UUID;

public class User {
  private final String id;
  private final RepositorioGuardaRopa closet;
  private final int age;

  public User(RepositorioGuardaRopa closet, int age) {
    this.id = UUID.randomUUID().toString();
    this.closet = closet;
    this.age = age;
  }

  public void UploadItem(Prenda prenda, String idGuardaRopa) {
        RepositorioGuardaRopa
  }

  public RepositorioGuardaRopa GetItems() {
    return closet;
  }

  public void ConfirmDraft() {
    if (draft != null && draft.isComplete()) {
      Prenda prenda = draft.build();
      this.closet.add(prenda);
      ClearDraft();
    } else {
      throw new IllegalStateException("Draft is not complete");
    }
  }

  public BorradorPrenda GetDraft() {
    if (draft == null) {
      this.draft = new BorradorPrenda();
    }
    return this.draft;
  }

  public void ClearDraft() {
    this.draft = new BorradorPrenda();
  }

  public int getAge() {
    return age;
  }
}