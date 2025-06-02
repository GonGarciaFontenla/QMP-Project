package org.example;

import org.example.Garment.*;
import org.example.Garment.Material.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GarmentTest {

  @Test
  void validGarmentCanBeCreated() {
    Garment garment = Utils.createGarment("Remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL);
    assertNotNull(garment);
  }

  @Test
  void garmentWithNullTypeThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(null, Utils.buildValidMaterial(TypeMaterial.ALGODON, Weft.LISA, new Color("blue"), new Color("white")), Formality.FORMAL));
  }

  @Test
  void garmentWithInvalidMainColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("invalidColor"), new Color("white"), Weft.CUADROS, Formality.FORMAL));
  }

  @Test
  void garmentWithInvalidSecondaryColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("notAColor"), Weft.LISA, Formality.FORMAL));
  }

  @Test
  void garmentWithNullMaterialThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(new TypeOfGarment("Remera", Category.PARTE_SUPERIOR), null, Formality.FORMAL));
  }

  @Test
  void garmentWithNullMainColorThrowsException() {
    assertThrows(NullPointerException.class, () ->
        Utils.buildValidMaterial(TypeMaterial.ALGODON, Weft.CUADROS, null, new Color("white"))); // Probamos al construir el material
  }

  @Test
  void garmentWithMismatchedCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Zapatos", Category.PARTE_SUPERIOR, TypeMaterial.CUERO, new Color("black"), null, Weft.LISA, Formality.FORMAL));
  }

  @Test
  void garmentWithNoWeftCreatesWithLISA() {
    Garment garment = Utils.createGarment("Remera",Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), null, Formality.FORMAL);
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

