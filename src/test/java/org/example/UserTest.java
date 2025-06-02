package org.example;

import org.example.Garment.Category;
import org.example.Garment.Formality;
import org.example.Garment.Garment;
import org.example.Garment.Material.Color;
import org.example.Garment.Material.Material;
import org.example.Garment.Material.TypeMaterial;
import org.example.Garment.Material.Weft;
import org.example.Garment.TypeOfGarment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
  @Test
  void addOnlyValidItem() {
    User user = new User(new ArrayList<>(),18);
    Garment garment1 = Utils.createGarment("Remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL);
    user.UploadItem(garment1);
    try {
      Garment garment2 = Utils.createGarment("Camisa",Category.PARTE_SUPERIOR,  TypeMaterial.ALGODON, new Color("Invalid color"), new Color("white"), Weft.LISA, Formality.FORMAL);
      user.UploadItem(garment2);
    } catch (IllegalArgumentException e) {
      // Se ignora la excepción para que el test continúe
    }
    assertEquals(1, user.GetItems().size());
  }

  @Test
  void userCanSaveGarmentFromDraft() {
    User user = new User(new ArrayList<>(),18);

    user.GetDraft().type(new TypeOfGarment("Camisa", Category.PARTE_SUPERIOR));
    user.GetDraft().material(new Material(TypeMaterial.LANA, Weft.LISA, new Color("white"), null));

    assertTrue(user.GetDraft().isComplete());

    user.ConfirmDraft();
    assertEquals(1, user.GetItems().size());
    assertFalse(user.GetDraft().isComplete()); // borrador vacío
  }
}
