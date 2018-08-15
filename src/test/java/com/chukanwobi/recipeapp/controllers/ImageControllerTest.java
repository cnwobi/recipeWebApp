package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.services.ImageService;
import com.chukanwobi.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {
    @Mock
    ImageService imageService;
    @Mock
    RecipeService recipeService;
    ImageController controller;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
 controller = new ImageController(imageService,recipeService);
 mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }
    @Test
    public void testUpdateForm() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1l);
        when(recipeService.findCommandById(1l)).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/image/update"))
                .andExpect(status().isOk())
        .andExpect(model().attributeExists("recipe","recipezId"))
        .andExpect(view().name("recipe/image/form"));
    }
    @Test
    public void testReceiveImagePost() throws Exception{
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile","testing.txt",
                "text/plain","Chuka Nwobi com".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location","/recipe/1/update"));
verify(imageService,times(1)).saveImageFile(anyLong(),any());
    }
}