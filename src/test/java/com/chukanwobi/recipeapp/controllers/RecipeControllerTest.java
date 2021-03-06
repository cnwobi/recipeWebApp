package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.exceptions.NotFoundException;
import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
import com.chukanwobi.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class RecipeControllerTest {
    @Mock
    RecipeService recipeService;

@Mock
        RecipeRepository recipeRepository;

    RecipeController controller;

    MockMvc mockMvc;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(recipeService);
         mockMvc = MockMvcBuilders.standaloneSetup(controller)
                 .setControllerAdvice(new ControllerExceptionHandler()).build();}

    @Test
    public void testGetRecipe() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);



        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk())
                .andExpect(view().
                name("recipe/show")).andExpect(model().attributeExists(
                        "recipe"));
    }

    @Test
    public void testNewPostRecipe() throws Exception{
        RecipeCommand command =  new RecipeCommand();
        command.setId(2l);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id","")
                .param("description","Some string")
                .param("prepTime","34")
                .param("cookTime","24")
                .param("source","Some Source")
                .param("servings", "4")
                .param("url","https://www.javadevjournal.com/spring-boot/spring-boot-internationalization/")

        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }


    @Test
    public void testGetUpdateView() throws Exception{
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.findCommandById(any())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));

    }

   @Test
    public void testGetRecipeNotFound() throws Exception{
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
        .andExpect(view().name("error404"));
   }
   @Test
    public void testNumberFormatException() throws Exception{
        mockMvc.perform(get("/recipe/1a/show"))
                .andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("numberFormatException"))
        .andExpect(view().name("error400"));
   }
}