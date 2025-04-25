package org.example;

import org.example.Engine.BasicSuggestionEngine;
import org.example.Engine.FormalSuggestionEngine;
import org.example.Engine.SuggestionEngine;
import org.example.Garment.*;
import org.example.Garment.Material.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GarmentTest {

  @Test
  void validGarmentCanBeCreated() {
    Garment garment = Utils.createGarment("Remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL);
    assertNotNull(garment);
  }

  @Test
  void garmentWithNullTypeThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(null, Utils.buildValidMaterial(TypeMaterial.ALGODON, Weft.LISA, new Color("blue"), new Color("white")), Formality.FORMAL));
  }

  @Test
  void garmentWithInvalidMainColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("invalidColor"), new Color("white"), Weft.CUADROS, Formality.FORMAL));
  }

  @Test
  void garmentWithInvalidSecondaryColorThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("notAColor"), Weft.LISA, Formality.FORMAL));
  }

  @Test
  void garmentWithNullMaterialThrowsException() {
    assertThrows(NullPointerException.class, () ->
        new Garment(new TypeOfGarment("Remera", Category.PARTE_SUPERIOR), null, Formality.FORMAL));
  }

  @Test
  void garmentWithNullMainColorThrowsException() {
    assertThrows(NullPointerException.class, () ->
        Utils.buildValidMaterial(TypeMaterial.ALGODON, Weft.CUADROS, null, new Color("white"))); // Probamos al construir el material
  }

  @Test
  void garmentWithMismatchedCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        Utils.createGarment("Zapatos", Category.PARTE_SUPERIOR, TypeMaterial.CUERO, new Color("black"), null, Weft.LISA, Formality.FORMAL));
  }

  @Test
  void garmentWithNoWeftCreatesWithLISA() {
    Garment garment = Utils.createGarment("Remera",Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), null, Formality.FORMAL);
    assertEquals(Weft.LISA, garment.getMaterial().getWeft());
  }

  @Test
  void draftIsIncompleteWhenOnlyTypeIsSet() {
    GarmentDraft draft = new GarmentDraft();
    draft.type(new TypeOfGarment("Remera", Category.PARTE_SUPERIOR));

    assertFalse(draft.isComplete());
    assertThrows(IllegalStateException.class, draft::build);
  }

  @Test
  void draftBuildsGarmentWhenComplete() {
    GarmentDraft draft = new GarmentDraft();
    draft.type(new TypeOfGarment("Remera", Category.PARTE_SUPERIOR));
    draft.material(new Material(TypeMaterial.ALGODON, Weft.LISA, new Color("blue"), new Color("white")));
    assertTrue(draft.isComplete());
    Garment garment = draft.build();
    assertEquals("Remera", garment.getType().getName());
    assertEquals(TypeMaterial.ALGODON, garment.getMaterial().getTypeMaterial());
  }


}
class TypeOfGarmentTest {

  @Test
  void validTypeIsAccepted() {
    assertDoesNotThrow(() -> new TypeOfGarment("Remera", Category.PARTE_SUPERIOR));
  }

  @Test
  void mismatchedTypeAndCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        new TypeOfGarment("Zapatos", Category.PARTE_SUPERIOR)); // Debería ser CALZADO
  }

  @Test
  void nullNameThrowsException() {
    assertThrows(NullPointerException.class, () -> new TypeOfGarment(null, Category.ACCESORIO));
  }

  @Test
  void nullCategoryThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new TypeOfGarment("Pañuelo", null));
  }
}

class UserTest {
  @Test
  void addOnlyValidItem() {
    User user = new User(new ArrayList<>(),18);
    Garment garment1 = Utils.createGarment("Remera", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL);
    user.UploadItem(garment1);
    try {
      Garment garment2 = Utils.createGarment("Camisa",Category.PARTE_SUPERIOR,  TypeMaterial.ALGODON, new Color("Invalid color"), new Color("white"), Weft.LISA, Formality.FORMAL);
      user.UploadItem(garment2);
    } catch (IllegalArgumentException e) {
      // Se ignora la excepción para que el test continúe
    }
    assertEquals(1, user.GetItems().size());
  }

  @Test
  void userCanSaveGarmentFromDraft() {
    User user = new User(new ArrayList<>(),18);

    user.GetDraft().type(new TypeOfGarment("Camisa", Category.PARTE_SUPERIOR));
    user.GetDraft().material(new Material(TypeMaterial.LANA, Weft.LISA, new Color("white"), null));

    assertTrue(user.GetDraft().isComplete());

    user.ConfirmDraft();
    assertEquals(1, user.GetItems().size());
    assertFalse(user.GetDraft().isComplete()); // borrador vacío
  }
}

  class OutfitSuggesterTest {

    @Test
    void testGenerateSuggestionsReturnsAllCombinations() {
      User user = new User(new ArrayList<>(),18);
      user.UploadItem(Utils.createGarment("camisa", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL));
      user.UploadItem(Utils.createGarment("pantalon", Category.PARTE_INFERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL));
      user.UploadItem(Utils.createGarment("zapatillas", Category.CALZADO, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL));

      BasicSuggestionEngine suggester = new BasicSuggestionEngine();
      List<Outfit> outfits = suggester.generateSuggestions(user);

      assertEquals(1, outfits.size(), "Debería generar una sola combinación válida");
    }

    @Test
    void testGenerateSuggestionsReturnsAllFourCombinations() {
      User user = new User(new ArrayList<>(),18);
      user.UploadItem(Utils.createGarment("camisa", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL));
      user.UploadItem(Utils.createGarment("REMERA", Category.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL));
      user.UploadItem(Utils.createGarment("pantalon", Category.PARTE_INFERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL));
      user.UploadItem(Utils.createGarment("falda", Category.PARTE_INFERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL));
      user.UploadItem(Utils.createGarment("zapatillas", Category.CALZADO, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formality.FORMAL));

      BasicSuggestionEngine suggester = new BasicSuggestionEngine();
      List<Outfit> outfits = suggester.generateSuggestions(user);

      assertEquals(4, outfits.size(), "Debería generar una sola combinación válida");
    }
  }

class SuggestionEngineTest {
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