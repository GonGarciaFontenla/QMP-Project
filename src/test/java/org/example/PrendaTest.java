package org.example;

import org.example.Prenda.*;
import org.example.Prenda.Material.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrendaTest {

  @Test
  void validGarmentCanBeCreated() {
    Prenda prenda = Utils.createGarment("Remera", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL);
    assertNotNull(prenda);
  }

  @Test
  void garmentWithNullTypeThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Prenda(null, Utils.buildValidMaterial(TypeMaterial.ALGODON, Weft.LISA, new Color("blue"), new Color("white")), Formalidad.FORMAL));
  }

  @Test
  void garmentWithInvalidMainColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Remera", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("invalidColor"), new Color("white"), Weft.CUADROS, Formalidad.FORMAL));
  }

  @Test
  void garmentWithInvalidSecondaryColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Remera", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("notAColor"), Weft.LISA, Formalidad.FORMAL));
  }

  @Test
  void garmentWithNullMaterialThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Prenda(new TipoDePrenda("Remera", Categoria.PARTE_SUPERIOR), null, Formalidad.FORMAL));
  }

  @Test
  void garmentWithNullMainColorThrowsException() {
    assertThrows(NullPointerException.class, () ->
        Utils.buildValidMaterial(TypeMaterial.ALGODON, Weft.CUADROS, null, new Color("white"))); // Probamos al construir el material
  }

  @Test
  void garmentWithMismatchedCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Zapatos", Categoria.PARTE_SUPERIOR, TypeMaterial.CUERO, new Color("black"), null, Weft.LISA, Formalidad.FORMAL));
  }

  @Test
  void garmentWithNoWeftCreatesWithLISA() {
    Prenda prenda = Utils.createGarment("Remera", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), null, Formalidad.FORMAL);
    assertEquals(Weft.LISA, prenda.getMaterial().getWeft());
  }

  @Test
  void draftIsIncompleteWhenOnlyTypeIsSet() {
    BorradorPrenda draft = new BorradorPrenda();
    draft.type(new TipoDePrenda("Remera", Categoria.PARTE_SUPERIOR));

    assertFalse(draft.isComplete());
    assertThrows(IllegalStateException.class, draft::build);
  }

  @Test
  void draftBuildsGarmentWhenComplete() {
    BorradorPrenda draft = new BorradorPrenda();
    draft.type(new TipoDePrenda("Remera", Categoria.PARTE_SUPERIOR));
    draft.material(new Material(TypeMaterial.ALGODON, Weft.LISA, new Color("blue"), new Color("white")));
    assertTrue(draft.isComplete());
    Prenda prenda = draft.build();
    assertEquals("Remera", prenda.getType().getName());
    assertEquals(TypeMaterial.ALGODON, prenda.getMaterial().getTypeMaterial());
  }
}

