package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientCommandToIngredient;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientToIngredientCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.repositories.IngredientsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    IngredientService ingredientService;

    @Mock
    IngredientsRepository ingredientsRepository;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    IngredientCommandToIngredient ingredientCommandToIngredient;
    Optional<Ingredient> ingredientOptional;
    Ingredient ingredient;
    @Before
    public void setUp() throws Exception {
        ingredient = new Ingredient();
        ingredient.setId(2l);

        ingredientOptional = Optional.of(ingredient);
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientsRepository,ingredientCommandToIngredient,ingredientToIngredientCommand);
    }

    @Test
    public void getIngredientByIdTest() {


        when(ingredientsRepository.findById(anyLong())).thenReturn(ingredientOptional);

        Ingredient returnedIngredient = ingredientService.findById(2l);

        assertNotNull("Null ingredient returned",returnedIngredient);

        verify(ingredientsRepository,times(1)).findById(anyLong());
        verify(ingredientsRepository,never()).findAll();
    }



    @Test
    public void deleteById() {
    }
}