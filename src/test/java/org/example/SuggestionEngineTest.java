package org.example;

import org.example.Engine.BasicSuggestionEngine;
import org.example.Engine.FormalSuggestionEngine;
import org.example.Engine.SuggestionEngine;
import org.example.Garment.Formality;
import org.example.Garment.Garment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuggestionEngineTest {
  @Test
  void basicEngineShouldGenerateOutfit() {
    ArrayList<Garment> allGarments = Utils.createCloset();
    SuggestionEngine engine = new BasicSuggestionEngine();
    User user = new User(allGarments, 25);

    Optional<Outfit> outfit = engine.generateOneSuggestion(user);

    assertTrue(outfit.isPresent());
  }

  @Test
  void formalEngineShouldFilterInformalForOlderUsers() {
    ArrayList<Garment> allGarments = Utils.createCloset();
    SuggestionEngine engine = new FormalSuggestionEngine();
    User oldUser = new User(allGarments, 60);

    List<Outfit> suggestions = engine.generateSuggestions(oldUser);

    assertFalse(suggestions.isEmpty());

    // Verificamos que ninguna prenda sea informal
    for (Outfit outfit : suggestions) {
      assertNotEquals(Formality.INFORMAL, outfit.getParteSuperior().getFormality());
      assertNotEquals(Formality.INFORMAL, outfit.getParteInferior().getFormality());
      assertNotEquals(Formality.INFORMAL, outfit.getCalzado().getFormality());
    }
  }

  @Test
  void formalEngineShouldAllowInformalForYoungUsers() {
    ArrayList<Garment> allGarments = Utils.createCloset();
    SuggestionEngine engine = new FormalSuggestionEngine();
    User youngUser = new User(allGarments, 30);

    List<Outfit> suggestions = engine.generateSuggestions(youngUser);

    assertFalse(suggestions.isEmpty());

    boolean foundInformal = suggestions.stream().anyMatch(outfit ->
            outfit.getParteSuperior().getFormality() == Formality.INFORMAL ||
                    outfit.getParteInferior().getFormality() == Formality.INFORMAL ||
                    outfit.getCalzado().getFormality() == Formality.INFORMAL
    );

    assertTrue(foundInformal);
  }
}
