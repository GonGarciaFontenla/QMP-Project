package org.example.Engine;

import org.example.Prenda.Categoria;
import org.example.Prenda.Prenda;
import org.example.Outfit;
import org.example.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class AbstractSuggestionEngine implements SuggestionEngine {

  @Override
  public Optional<Outfit> generateOneSuggestion(List<Prenda> prendas, int age) {
    List<Prenda> superiorParts = filterByCategory(prendas, Categoria.PARTE_SUPERIOR, age);
    List<Prenda> inferiorParts = filterByCategory(prendas, Categoria.PARTE_INFERIOR, age);
    List<Prenda> footwears = filterByCategory(prendas, Categoria.CALZADO, age);

    if (superiorParts.isEmpty() || inferiorParts.isEmpty() || footwears.isEmpty()) {
      return Optional.empty();
    }

    Random rand = new Random();
    Prenda superior = superiorParts.get(rand.nextInt(superiorParts.size()));
    Prenda inferior = inferiorParts.get(rand.nextInt(inferiorParts.size()));
    Prenda footwear = footwears.get(rand.nextInt(footwears.size()));

    return Optional.of(new Outfit(footwear, superior, inferior));
  }

  @Override
  public List<Outfit> generateSuggestions(List<Prenda> prendas, int age) {
    List<Prenda> superiorParts = filterByCategory(prendas, Categoria.PARTE_SUPERIOR, age);
    List<Prenda> inferiorParts = filterByCategory(prendas, Categoria.PARTE_INFERIOR, age);
    List<Prenda> footwears = filterByCategory(prendas, Categoria.CALZADO, age);

    List<Outfit> outfits = new ArrayList<>();
    for (Prenda superior : superiorParts) {
      for (Prenda inferior : inferiorParts) {
        for (Prenda footwear : footwears) {
          outfits.add(new Outfit(footwear, superior, inferior));
        }
      }
    }
    return outfits;
  }

  protected abstract List<Prenda> filterByCategory(List<Prenda> prendas, Categoria categoria, int age);
}

