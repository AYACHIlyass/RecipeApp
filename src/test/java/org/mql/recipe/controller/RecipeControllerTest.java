package org.mql.recipe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mql.recipe.model.Ingredient;
import org.mql.recipe.model.Recipe;
import org.mql.recipe.service.CategoryService;
import org.mql.recipe.service.RecipeService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService service;
    @Mock
    CategoryService categoryService ;
    @InjectMocks
    RecipeController recipeController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void checkGetRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(service.findById(1L)).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/view?id=1"))
                .andExpect(status().isOk())
                //.andExpect(model().attribute("recipe", recipe))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("/recipes/viewRecipe"));
        verify(service).findById(1L);

    }
    @Test
    public void checkAddRecipeForewordWorks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/addRecipe"))
                .andExpect(status().isOk())
                //.andExpect(model().attribute("recipe", recipe))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("/recipes/addRecipe"));
    }

    @Test
    void removeIngredient() {
        Recipe recipe = new Recipe();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        recipe.setIngredients(ingredients);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("removeIngredient")).thenReturn("0");
        recipeController.removeIngredient(recipe , request);
    }
}