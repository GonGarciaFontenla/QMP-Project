package org.example.Engine;

import org.example.Garment.Category;
import org.example.Garment.Garment;
import org.example.User;

import java.util.List;

public class BasicSuggestionEngine extends AbstractSuggestionEngine {

  @Override
  protected List<Garment> filterByCategory(List<Garment> garments, Category category, User user) {
    return garments.stream()
            .filter(g -> g.getType().getCategory() == category)
            .toList();
  }
}

