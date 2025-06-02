package org.example;

import org.example.Engine.BasicSuggestionEngine;
import org.example.Prenda.Categoria;
import org.example.Prenda.Formalidad;
import org.example.Prenda.Material.Color;
import org.example.Prenda.Material.TypeMaterial;
import org.example.Prenda.Material.Weft;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutfitSuggesterTest {

    @Test
    void testGenerateSuggestionsReturnsAllCombinations() {
      User user = new User(new ArrayList<>(),18);
      user.UploadItem(Utils.createGarment("camisa", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL));
      user.UploadItem(Utils.createGarment("pantalon", Categoria.PARTE_INFERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL));
      user.UploadItem(Utils.createGarment("zapatillas", Categoria.CALZADO, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL));

      BasicSuggestionEngine suggester = new BasicSuggestionEngine();
      List<Outfit> outfits = suggester.generateSuggestions(user);

      assertEquals(1, outfits.size(), "Debería generar una sola combinación válida");
    }

    @Test
    void testGenerateSuggestionsReturnsAllFourCombinations() {
      User user = new User(new ArrayList<>(),18);
      user.UploadItem(Utils.createGarment("camisa", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL));
      user.UploadItem(Utils.createGarment("REMERA", Categoria.PARTE_SUPERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL));
      user.UploadItem(Utils.createGarment("pantalon", Categoria.PARTE_INFERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL));
      user.UploadItem(Utils.createGarment("falda", Categoria.PARTE_INFERIOR, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL));
      user.UploadItem(Utils.createGarment("zapatillas", Categoria.CALZADO, TypeMaterial.ALGODON, new Color("blue"), new Color("white"), Weft.LISA, Formalidad.FORMAL));

      BasicSuggestionEngine suggester = new BasicSuggestionEngine();
      List<Outfit> outfits = suggester.generateSuggestions(user);

      assertEquals(4, outfits.size(), "Debería generar una sola combinación válida");
    }
  }
