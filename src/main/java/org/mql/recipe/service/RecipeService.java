package org.mql.recipe.service;

import org.mql.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe addRecipe(Recipe recipe);
    Recipe updateRecipe(Recipe recipe);
    Recipe findById(Long id);
}
