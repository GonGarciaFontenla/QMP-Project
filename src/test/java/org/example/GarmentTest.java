package org.example;

import org.junit.jupiter.api.Test;
import org.example.outfit.*;

import static org.junit.jupiter.api.Assertions.*;

public class GarmentTest {

  @Test
  void validGarmentCanBeCreated() {
    Garment garment = createGarment("Remera", Category.PARTE_SUPERIOR, Material.ALGODON, "blue", "white");
    assertNotNull(garment);
  }

  @Test
  void garmentWithNullTypeThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(null, Material.ALGODON, "blue", "white"));
  }

  @Test
  void garmentWithInvalidMainColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        createGarment("Remera", Category.PARTE_SUPERIOR, Material.ALGODON, "invalidColor", "white"));
  }

  @Test
  void garmentWithInvalidSecondaryColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        createGarment("Remera", Category.PARTE_SUPERIOR, Material.ALGODON, "blue", "notAColor"));
  }

  @Test
  void garmentWithNullMaterialThrowsException() {
    assertThrows(NullPointerException.class, () ->
        createGarment("Remera", Category.PARTE_SUPERIOR, null, "blue", "white"));
  }

  @Test
  void garmentWithNullMainColorThrowsException() {
    assertThrows(NullPointerException.class, () ->
        createGarment("Remera", Category.PARTE_SUPERIOR, Material.ALGODON, null, "white"));
  }

  @Test
  void garmentWithMismatchedCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        createGarment("Zapatos", Category.PARTE_SUPERIOR, Material.CUERO, "black", null));
  }

  private Garment createGarment(String typeName, Category expectedCategory, Material material,
                                String mainColor, String secondaryColor) {
    TypeOfGarment type = new TypeOfGarment(typeName, expectedCategory);
    return new Garment(type, material, mainColor, secondaryColor);
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