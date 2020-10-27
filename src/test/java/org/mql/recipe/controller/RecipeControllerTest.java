package org.mql.recipe.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mql.recipe.model.Recipe;
import org.mql.recipe.service.RecipeService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService service;
    @InjectMocks
    RecipeController recipeController;

    @Test
    public void checkGetRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(service.findById(1L)).thenReturn(recipe);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/view?id=1"))
                .andExpect(status().isOk())
                //.andExpect(model().attribute("recipe", recipe))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("viewRecipe"));
        verify(service).findById(1L);

    }

}