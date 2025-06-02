package org.example;

import org.example.Prenda.Categoria;
import org.example.Prenda.Formalidad;
import org.example.Prenda.Prenda;
import org.example.Prenda.Material.Color;
import org.example.Prenda.Material.Material;
import org.example.Prenda.Material.TypeMaterial;
import org.example.Prenda.Material.Weft;
import org.example.Prenda.TipoDePrenda;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
  @Test
  void addOnlyValidItem() {
    User user = new User(new ArrayList<>(),18);
    Prenda prenda1 = Utils.createGarment("Remera", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL);
    user.UploadItem(prenda1);
    try {
      Prenda prenda2 = Utils.createGarment("Camisa", Categoria.PARTE_SUPERIOR,  TypeMaterial.ALGODON, new Color("Invalid color"), new Color("white"), Weft.LISA, Formalidad.FORMAL);
      user.UploadItem(prenda2);
    } catch (IllegalArgumentException e) {
      // Se ignora la excepción para que el test continúe
    }
    assertEquals(1, user.GetItems().size());
  }

  @Test
  void userCanSaveGarmentFromDraft() {
    User user = new User(new ArrayList<>(),18);

    user.GetDraft().type(new TipoDePrenda("Camisa", Categoria.PARTE_SUPERIOR));
    user.GetDraft().material(new Material(TypeMaterial.LANA, Weft.LISA, new Color("white"), null));

    assertTrue(user.GetDraft().isComplete());

    user.ConfirmDraft();
    assertEquals(1, user.GetItems().size());
    assertFalse(user.GetDraft().isComplete()); // borrador vacío
  }
}
