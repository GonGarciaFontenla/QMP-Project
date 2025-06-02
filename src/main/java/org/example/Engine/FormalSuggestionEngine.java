package org.example.Engine;

import org.example.Prenda.*;
import org.example.User;

import java.util.List;

public class FormalSuggestionEngine extends AbstractSuggestionEngine {
  @Override
  protected List<Prenda> filterByCategory(List<Prenda> prendas, Categoria categoria, User user) {
    return prendas.stream()
            .filter(g -> g.getType().getCategory() == categoria)
            .filter(g -> !(user.getAge() > 55 && g.getFormality() == Formalidad.INFORMAL))
            .toList();
  }
}
