package org.example;

import org.example.Engine.BasicSuggestionEngine;
import org.example.Engine.FormalSuggestionEngine;
import org.example.Engine.SuggestionEngine;
import org.example.Prenda.Formalidad;
import org.example.Prenda.Prenda;
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
    ArrayList<Prenda> allPrendas = Utils.createCloset();
    SuggestionEngine engine = new BasicSuggestionEngine();
    User user = new User(allPrendas, 25);

    Optional<Outfit> outfit = engine.generateOneSuggestion(user);

    assertTrue(outfit.isPresent());
  }

  @Test
  void formalEngineShouldFilterInformalForOlderUsers() {
    ArrayList<Prenda> allPrendas = Utils.createCloset();
    SuggestionEngine engine = new FormalSuggestionEngine();
    User oldUser = new User(allPrendas, 60);

    List<Outfit> suggestions = engine.generateSuggestions(oldUser);

    assertFalse(suggestions.isEmpty());

    for (Outfit outfit : suggestions) {
      assertNotEquals(Formalidad.INFORMAL, outfit.getParteSuperior().getFormality());
      assertNotEquals(Formalidad.INFORMAL, outfit.getParteInferior().getFormality());
      assertNotEquals(Formalidad.INFORMAL, outfit.getCalzado().getFormality());
    }
  }

  @Test
  void formalEngineShouldAllowInformalForYoungUsers() {
    ArrayList<Prenda> allPrendas = Utils.createCloset();
    SuggestionEngine engine = new FormalSuggestionEngine();
    User youngUser = new User(allPrendas, 30);

    List<Outfit> suggestions = engine.generateSuggestions(youngUser);

    assertFalse(suggestions.isEmpty());

    boolean foundInformal = suggestions.stream().anyMatch(outfit ->
            outfit.getParteSuperior().getFormality() == Formalidad.INFORMAL ||
                    outfit.getParteInferior().getFormality() == Formalidad.INFORMAL ||
                    outfit.getCalzado().getFormality() == Formalidad.INFORMAL
    );

    assertTrue(foundInformal);
  }
}
