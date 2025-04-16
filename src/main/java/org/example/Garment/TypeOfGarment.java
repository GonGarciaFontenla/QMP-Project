package org.example.Garment;

import java.util.Map;
import static java.util.Objects.requireNonNull;

public class TypeOfGarment {
  private static final Map<String, Category> validTypes = Map.of(
      "remera", Category.PARTE_SUPERIOR,
      "camisa", Category.PARTE_SUPERIOR,
      "pantalon", Category.PARTE_INFERIOR,
      "zapatillas", Category.CALZADO,
      "anteojos", Category.ACCESORIO
  );

  private final String name;
  private final Category category;

  public TypeOfGarment(String name, Category category) {
    if (!validTypes.containsKey(name.toLowerCase())) {
      throw new IllegalArgumentException("Tipo de prenda desconocido: " + name);
    }
    if (validTypes.get(name.toLowerCase()) != category) {
      throw new IllegalArgumentException("La categoría no coincide con el tipo: " + name);
    }

    this.name = name;
    this.category = requireNonNull(category, "Category cannot be null");
  }

  public String getName() {
    return name;
  }

  public Category getCategory() {
    return category;
  }
}
