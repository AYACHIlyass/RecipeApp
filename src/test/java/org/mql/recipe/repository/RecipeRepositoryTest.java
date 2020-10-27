package org.mql.recipe.repository;

import org.junit.jupiter.api.Test;
import org.mql.recipe.model.Recipe;
import org.mql.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RecipeServiceTest {

    @Autowired
    RecipeService service ;


    @Test
    public void checkWeGetCorrectListOfRecipes() {
        List<Recipe> recipes = service.getAllRecipes();
        assertNotNull(recipes);
        assertNotNull(recipes.get(0).getCategories());
        assertNotNull(recipes.get(0).getIngredient());
        assertNotNull(recipes.get(0).getNote());
    }

}