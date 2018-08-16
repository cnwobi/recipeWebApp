package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.commands.DirectionCommand;
import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.converters.recipeConverter.RecipeToRecipeCommand;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
import com.chukanwobi.recipeapp.services.DirectionService;
import com.chukanwobi.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class DirectionControllerTest {
    @Mock
    RecipeService recipeService;

    @Mock
    DirectionService directionService;


    DirectionController directionController;

    MockMvc mockMvc;
    RecipeCommand recipeCommand;
    DirectionCommand directionCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        directionController = new DirectionController(recipeService,directionService);
        mockMvc= MockMvcBuilders.standaloneSetup(directionController).build();
         recipeCommand = new RecipeCommand();
        recipeCommand.setId(4L);
        directionCommand = new DirectionCommand();
        directionCommand.setRecipeId(recipeCommand.getId());
        directionCommand.setId(1l);

    }

    @Test
    public void listDirections() throws Exception{


        when(recipeService.findCommandById(recipeCommand.getId())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/2/directions/view&edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/direction/list"));
    }

    @Test
    public void updateDirections() throws Exception {
        when(directionService.findByRecipeIdAndDirectionID(anyLong(),anyLong()))
                .thenReturn(directionCommand);
        when(recipeService.findCommandById(recipeCommand.getId())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/4/directions/1/update/"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/direction/form"))
                .andExpect(model().attributeExists("recipe","recipeId","direction"));


    }

  @Test
    public void testDeleteDirections() throws Exception{
       mockMvc.perform(get("/recipe/4/directions/1/delete/"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/recipe/4/directions/view&edit"));
verify(directionService,times(1)).deleteById(anyLong(),anyLong());
  }
}