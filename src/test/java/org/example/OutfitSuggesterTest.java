package org.example;

import org.example.Engine.BasicSuggestionEngine;
import org.example.Garment.Category;
import org.example.Garment.Formality;
import org.example.Garment.Material.Color;
import org.example.Garment.Material.TypeMaterial;
import org.example.Garment.Material.Weft;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutfitSuggesterTest {

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
