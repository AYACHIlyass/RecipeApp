package org.mql.recipe.controller;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mql.recipe.service.UnitOfMeasureService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class UnitOfMeasureControllerTest {

    @Mock
    UnitOfMeasureService unitOfMeasureService;
    @Mock
    Model model;
    @InjectMocks
    UnitOfMeasureController unitOfMeasureController;


    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkControllerReturnsUnits() {
        String viewName = unitOfMeasureController.units(model);
        assertEquals("units/units", viewName);
        Mockito.verify(unitOfMeasureService).getAllUnits();
        ArgumentCaptor<List> listArgumentCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(model).addAttribute(ArgumentMatchers.anyString() , listArgumentCaptor.capture());
        List list = listArgumentCaptor.getValue();
        System.out.println(list);
    }

    @Test
    public void checkUnitOfMeasureController() throws Exception {
        // it brings the spring context
        // MockMvc mockMvc= MockMvcBuilders.webAppContextSetup().build();
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(unitOfMeasureController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/units"))
                .andExpect(status().isOk())
                .andExpect(view().name("units/units"))
                .andExpect(model().attributeExists("units"));
    }

}