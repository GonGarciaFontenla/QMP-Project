package org.example;

import org.example.Prenda.Categoria;
import org.example.Prenda.TipoDePrenda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeOfPrendaTest {

  @Test
  void validTypeIsAccepted() {
    assertDoesNotThrow(() -> new TipoDePrenda("Remera", Categoria.PARTE_SUPERIOR));
  }

  @Test
  void mismatchedTypeAndCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        new TipoDePrenda("Zapatos", Categoria.PARTE_SUPERIOR)); // Debería ser CALZADO
  }

  @Test
  void nullNameThrowsException() {
    assertThrows(NullPointerException.class, () -> new TipoDePrenda(null, Categoria.ACCESORIO));
  }

  @Test
  void nullCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new TipoDePrenda("Pañuelo", null));
  }
}
