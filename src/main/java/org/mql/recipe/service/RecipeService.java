package org.mql.recipe.service;

import org.mql.recipe.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe addRecipe(Recipe recipe);
    Recipe updateRecipe(Recipe recipe);
    Recipe findById(Long id);
    void delete(Long id);
    boolean updateImage(Long id, MultipartFile file);
    byte[] getImage(Long id);
}
