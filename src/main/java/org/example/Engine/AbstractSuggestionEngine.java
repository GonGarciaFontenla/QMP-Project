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
  public Optional<Outfit> generateOneSuggestion(User user) {
    List<Prenda> superiorParts = filterByCategory(user.GetItems(), Categoria.PARTE_SUPERIOR, user);
    List<Prenda> inferiorParts = filterByCategory(user.GetItems(), Categoria.PARTE_INFERIOR, user);
    List<Prenda> footwears = filterByCategory(user.GetItems(), Categoria.CALZADO, user);

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
  public List<Outfit> generateSuggestions(User user) {
    List<Prenda> superiorParts = filterByCategory(user.GetItems(), Categoria.PARTE_SUPERIOR, user);
    List<Prenda> inferiorParts = filterByCategory(user.GetItems(), Categoria.PARTE_INFERIOR, user);
    List<Prenda> footwears = filterByCategory(user.GetItems(), Categoria.CALZADO, user);

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

  protected abstract List<Prenda> filterByCategory(List<Prenda> prendas, Categoria categoria, User user);
}

