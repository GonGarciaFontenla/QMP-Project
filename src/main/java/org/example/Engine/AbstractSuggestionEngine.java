package org.example.Engine;

import org.example.Garment.Category;
import org.example.Garment.Garment;
import org.example.Outfit;
import org.example.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class AbstractSuggestionEngine implements SuggestionEngine {

  @Override
  public Optional<Outfit> generateOneSuggestion(User user) {
    List<Garment> superiorParts = filterByCategory(user.GetItems(), Category.PARTE_SUPERIOR, user);
    List<Garment> inferiorParts = filterByCategory(user.GetItems(), Category.PARTE_INFERIOR, user);
    List<Garment> footwears = filterByCategory(user.GetItems(), Category.CALZADO, user);

    if (superiorParts.isEmpty() || inferiorParts.isEmpty() || footwears.isEmpty()) {
      return Optional.empty();
    }

    Random rand = new Random();
    Garment superior = superiorParts.get(rand.nextInt(superiorParts.size()));
    Garment inferior = inferiorParts.get(rand.nextInt(inferiorParts.size()));
    Garment footwear = footwears.get(rand.nextInt(footwears.size()));

    return Optional.of(new Outfit(footwear, superior, inferior));
  }

  @Override
  public List<Outfit> generateSuggestions(User user) {
    List<Garment> superiorParts = filterByCategory(user.GetItems(), Category.PARTE_SUPERIOR, user);
    List<Garment> inferiorParts = filterByCategory(user.GetItems(), Category.PARTE_INFERIOR, user);
    List<Garment> footwears = filterByCategory(user.GetItems(), Category.CALZADO, user);

    List<Outfit> outfits = new ArrayList<>();
    for (Garment superior : superiorParts) {
      for (Garment inferior : inferiorParts) {
        for (Garment footwear : footwears) {
          outfits.add(new Outfit(footwear, superior, inferior));
        }
      }
    }
    return outfits;
  }

  protected abstract List<Garment> filterByCategory(List<Garment> garments, Category category, User user);
}

