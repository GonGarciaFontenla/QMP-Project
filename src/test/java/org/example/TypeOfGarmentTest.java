package org.example;

import org.example.Garment.Category;
import org.example.Garment.TypeOfGarment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeOfGarmentTest {

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
