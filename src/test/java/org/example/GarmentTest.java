package org.example;

import org.example.Garment.*;
import org.example.Garment.Material.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GarmentTest {

  @Test
  void validGarmentCanBeCreated() {
    Garment garment = Utils.createGarment("Remera", TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA);
    assertNotNull(garment);
  }

  @Test
  void garmentWithNullTypeThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(null, Utils.buildValidMaterial(TypeMaterial.ALGODON, Weft.LISA, new Color("blue"), new Color("white"))));
  }

  @Test
  void garmentWithInvalidMainColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Remera", TypeMaterial.ALGODON, new Color("invalidColor"), new Color("white"), Weft.CUADROS));
  }

  @Test
  void garmentWithInvalidSecondaryColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Remera", TypeMaterial.ALGODON, new Color("blue"), new Color("notAColor"), Weft.LISA));
  }

  @Test
  void garmentWithNullMaterialThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(new TypeOfGarment("Remera", Category.PARTE_SUPERIOR), null));
  }

  @Test
  void garmentWithNullMainColorThrowsException() {
    assertThrows(NullPointerException.class, () ->
        Utils.buildValidMaterial(TypeMaterial.ALGODON, Weft.CUADROS, null, new Color("white"))); // Probamos al construir el material
  }

  @Test
  void garmentWithMismatchedCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Zapatos", TypeMaterial.CUERO, new Color("black"), null, Weft.LISA));
  }

  @Test
  void garmentWithNoWeftCreatesWithLISA() {
    Garment garment = Utils.createGarment("Remera", TypeMaterial.ALGODON, new Color("blue"), new Color("white"), null);
    assertEquals(Weft.LISA, garment.getMaterial().getWeft());
  }

  @Test
  void draftIsIncompleteWhenOnlyTypeIsSet() {
    GarmentDraft draft = new GarmentDraft();
    draft.type(new TypeOfGarment("Remera", Category.PARTE_SUPERIOR));

    assertFalse(draft.isComplete());
    assertThrows(IllegalStateException.class, draft::build);
  }

  @Test
  void draftBuildsGarmentWhenComplete() {
    GarmentDraft draft = new GarmentDraft();
    draft.type(new TypeOfGarment("Remera", Category.PARTE_SUPERIOR));
    draft.material(new Material(TypeMaterial.ALGODON, Weft.LISA, new Color("blue"), new Color("white")));
    assertTrue(draft.isComplete());
    Garment garment = draft.build();
    assertEquals("Remera", garment.getType().getName());
    assertEquals(TypeMaterial.ALGODON, garment.getMaterial().getTypeMaterial());
  }


}
class TypeOfGarmentTest {

  @Test
  void validTypeIsAccepted() {
    assertDoesNotThrow(() -> new TypeOfGarment("Remera", Category.PARTE_SUPERIOR));
  }

  @Test
  void mismatchedTypeAndCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        new TypeOfGarment("Zapatos", Category.PARTE_SUPERIOR)); // Debería ser CALZADO
  }

  @Test
  void nullNameThrowsException() {
    assertThrows(NullPointerException.class, () -> new TypeOfGarment(null, Category.ACCESORIO));
  }

  @Test
  void nullCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new TypeOfGarment("Pañuelo", null));
  }
}

class UserTest {
  @Test
  void addOnlyValidItem() {
    User user = new User();
    Garment garment1 = Utils.createGarment("Remera", TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA);
    user.UploadItem(garment1);
    try {
      Garment garment2 = Utils.createGarment("Camisa", TypeMaterial.ALGODON, new Color("Invalid color"), new Color("white"), Weft.LISA);
      user.UploadItem(garment2);
    } catch (IllegalArgumentException e) {
      // Se ignora la excepción para que el test continúe
    }
    assertEquals(1, user.GetItems().size());
  }

  @Test
  void userCanSaveGarmentFromDraft() {
    User user = new User();

    user.GetDraft().type(new TypeOfGarment("Camisa", Category.PARTE_SUPERIOR));
    user.GetDraft().material(new Material(TypeMaterial.LANA, Weft.LISA, new Color("white"), null));

    assertTrue(user.GetDraft().isComplete());

    user.ConfirmDraft();
    assertEquals(1, user.GetItems().size());
    assertFalse(user.GetDraft().isComplete()); // borrador vacío
  }
}