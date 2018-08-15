package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
import com.chukanwobi.recipeapp.services.ImageService;
import com.chukanwobi.recipeapp.services.ImageServiceImpl;
import com.chukanwobi.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    ImageService imageService;
    @Mock
    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;

    ImageController controller;
    MockMvc mockMvc;
    MockMultipartFile multipartFile;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "Chuka Nwobi com".getBytes());
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void testUpdateForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1l);
        when(recipeService.findCommandById(1l)).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/image/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe", "recipezId"))
                .andExpect(view().name("recipe/image/form"));
    }

  /*  @Test
    public void testReceiveImagePost() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "Chuka Nwobi com".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/update"));
        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }*/

    @Test
    public void saveImageFile() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        //when
        imageService.saveImageFile(1l, multipartFile);

        //then
        verify(recipeRepository, times(1)).save(recipeArgumentCaptor.capture());

        Recipe savedRecipe = recipeArgumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);


    }
    @Test
    public void renderImageFromDatabase() throws Exception{
      RecipeCommand command = new RecipeCommand();
      command.setId(1L);
      String s = "fake image text test";
      Byte[] bytesTest = new Byte[s.getBytes().length];
      int i = 0;

      for (byte b : s.getBytes()){
          bytesTest[i++] = b;
      }
            command.setImage(bytesTest);
      when(recipeService.findCommandById(anyLong())).thenReturn(command);

        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk()).andReturn().getResponse();
        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length,responseBytes.length);
    }
}