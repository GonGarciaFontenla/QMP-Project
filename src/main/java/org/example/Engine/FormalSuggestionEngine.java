package org.example.Engine;

import org.example.Garment.*;
import org.example.User;

import java.util.List;

public class FormalSuggestionEngine extends AbstractSuggestionEngine {
  @Override
  protected List<Garment> filterByCategory(List<Garment> garments, Category category, User user) {
    return garments.stream()
            .filter(g -> g.getType().getCategory() == category)
            .filter(g -> !(user.getAge() > 55 && g.getFormality() == Formality.INFORMAL))
            .toList();
  }
}
