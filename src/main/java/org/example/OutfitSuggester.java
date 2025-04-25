package org.example;

import org.example.Engine.*;

import java.util.List;

public class OutfitSuggester {
  private final SuggestionEngineProvider engineProvider;

  public OutfitSuggester(SuggestionEngineProvider engineProvider) {
    this.engineProvider = engineProvider;
  }

  public List<Outfit> suggestOutfitsFor(User user) {
    SuggestionEngine engine = engineProvider.getEngine();
    return engine.generateSuggestions(user);
  }
}