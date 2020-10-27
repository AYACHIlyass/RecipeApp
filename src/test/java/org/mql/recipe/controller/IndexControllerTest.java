package org.mql.recipe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mql.recipe.exception.BadRequest;
import org.mql.recipe.exception.ExceptionHandlerCenter;
import org.mql.recipe.model.Recipe;
import org.mql.recipe.service.RecipeServiceJPAImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {
    @Mock
    RecipeServiceJPAImpl serviceJPA;
    @Mock
    Model model;
    @InjectMocks
    IndexController controller;

    @BeforeEach
    void setUp() {
        List list = new ArrayList();
        list.add(new Recipe());
        list.add(new Recipe());
        list.add(new Recipe());
        list.add(new Recipe());
        when(serviceJPA.getAllRecipes()).thenReturn(list);
    }

    @Test
    public void checkIndexUrlReturnsListOfRecipes() throws Exception {

        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
        mvc.perform(MockMvcRequestBuilders.get("/recipe/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
        verify(serviceJPA).getAllRecipes();

    }

    @Test
    public void checkIndexUrlThrowsAnError() throws Exception {
        when(serviceJPA.getAllRecipes()).thenThrow(BadRequest.class);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionHandlerCenter())
                .build();
        mvc.perform(MockMvcRequestBuilders.get("/recipe/"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

      //  verify(serviceJPA).getAllRecipes();

    }

}