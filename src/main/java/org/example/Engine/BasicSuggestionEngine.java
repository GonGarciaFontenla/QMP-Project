package org.example.Engine;

import org.example.Prenda.Categoria;
import org.example.Prenda.Prenda;
import org.example.User;

import java.util.List;

public class BasicSuggestionEngine extends AbstractSuggestionEngine {

  @Override
  protected List<Prenda> filterByCategory(List<Prenda> prendas, Categoria categoria, User user) {
    return prendas.stream()
            .filter(g -> g.getType().getCategory() == categoria)
            .toList();
  }
}

