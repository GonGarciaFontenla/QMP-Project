package org.example.Engine;

public class SuggestionEngineProvider {
  private SuggestionEngine engine;

  public SuggestionEngineProvider(SuggestionEngine defaultEngine) {
    this.engine = defaultEngine;
  }

  public SuggestionEngine getEngine() {
    return engine;
  }

  public void setEngine(SuggestionEngine engine) {
    if (engine == null) {
      throw new IllegalArgumentException("Suggestion engine cannot be null");
    }
    this.engine = engine;
  }
}
