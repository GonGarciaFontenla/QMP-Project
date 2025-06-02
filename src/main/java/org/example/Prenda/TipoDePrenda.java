package org.example.Prenda;

import java.util.Map;
import static java.util.Objects.requireNonNull;

public class TipoDePrenda {
  private static final Map<String, Categoria> validTypes = Map.of(
      "remera", Categoria.PARTE_SUPERIOR,
      "camisa", Categoria.PARTE_SUPERIOR,
      "pantalon", Categoria.PARTE_INFERIOR,
      "falda", Categoria.PARTE_INFERIOR,
      "short", Categoria.PARTE_INFERIOR,
      "zapatillas", Categoria.CALZADO,
      "anteojos", Categoria.ACCESORIO
  );

  private final String name;
  private final Categoria categoria;

  public TipoDePrenda(String name, Categoria categoria) {
    if (!validTypes.containsKey(name.toLowerCase())) {
      throw new IllegalArgumentException("Tipo de prenda desconocido: " + name);
    }
    if (validTypes.get(name.toLowerCase()) != categoria) {
      throw new IllegalArgumentException("La categoría no coincide con el tipo: " + name);
    }

    this.name = name;
    this.categoria = requireNonNull(categoria, "Category cannot be null");
  }

  public String getName() {
    return name;
  }

  public Categoria getCategory() {
    return categoria;
  }
}
