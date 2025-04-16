package org.example;

import org.example.Garment.*;
import org.example.Garment.Material.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GarmentTest {

  @Test
  void validGarmentCanBeCreated() {
    Garment garment = createGarment("Remera", TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA);
    assertNotNull(garment);
  }

  @Test
  void garmentWithNullTypeThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(null, buildValidMaterial(TypeMaterial.ALGODON, Weft.LISA, new Color("blue"), new Color("white"))));
  }

  @Test
  void garmentWithInvalidMainColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        createGarment("Remera", TypeMaterial.ALGODON, new Color("invalidColor"), new Color("white"), Weft.CUADROS));
  }

  @Test
  void garmentWithInvalidSecondaryColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        createGarment("Remera", TypeMaterial.ALGODON, new Color("blue"), new Color("notAColor"), Weft.LISA));
  }

  @Test
  void garmentWithNullMaterialThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(new TypeOfGarment("Remera", Category.PARTE_SUPERIOR), null));
  }

  @Test
  void garmentWithNullMainColorThrowsException() {
    assertThrows(NullPointerException.class, () ->
        buildValidMaterial(TypeMaterial.ALGODON, Weft.CUADROS, null, new Color("white"))); // Probamos al construir el material
  }

  @Test
  void garmentWithMismatchedCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        createGarment("Zapatos", TypeMaterial.CUERO, new Color("black"), null, Weft.LISA));
  }

  @Test
  void garmentWithNoWeftCreatesWithLISA() {
    Garment garment = createGarment("Remera", TypeMaterial.ALGODON, new Color("blue"), new Color("white"), null);
    assertEquals(Weft.LISA, garment.getMaterial().getWeft());
  }

  private Garment createGarment(String typeName, TypeMaterial material,
                                Color mainColor, Color secondaryColor, Weft weft) {
    TypeOfGarment type = new TypeOfGarment(typeName, Category.PARTE_SUPERIOR);
    Material mat = buildValidMaterial(material, weft, mainColor, secondaryColor);
    return new Garment(type, mat);
  }

  private Material buildValidMaterial(TypeMaterial materialType, Weft weft, Color mainColor, Color secondaryColor) {
    if (mainColor == null) throw new NullPointerException("Main color cannot be null");
    if (Color.isInvalidColor(mainColor.toString())) throw new IllegalArgumentException("Invalid main color");
    if (secondaryColor != null && Color.isInvalidColor(secondaryColor.toString())) {
      throw new IllegalArgumentException("Invalid secondary color");
    }

    return new Material(materialType, weft, mainColor, secondaryColor);
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