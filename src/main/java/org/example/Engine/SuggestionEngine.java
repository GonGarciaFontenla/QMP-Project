package org.example.Engine;

import org.example.Outfit;
import org.example.Prenda.Prenda;
import org.example.User;

import java.util.List;
import java.util.Optional;

public interface SuggestionEngine {
  public Optional<Outfit> generateOneSuggestion(List<Prenda> prendas, int age);
  public List<Outfit> generateSuggestions(List<Prenda> prendas, int age);
}
