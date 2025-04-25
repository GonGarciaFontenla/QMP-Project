package org.example.Engine;

import org.example.Outfit;
import org.example.User;

import java.util.List;
import java.util.Optional;

public interface SuggestionEngine {
  public Optional<Outfit> generateOneSuggestion(User user);
  public List<Outfit> generateSuggestions(User user);
}
