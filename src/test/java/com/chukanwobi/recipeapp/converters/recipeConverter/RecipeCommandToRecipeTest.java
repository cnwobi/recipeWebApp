package com.chukanwobi.recipeapp.converters.recipeConverter;

import com.chukanwobi.recipeapp.commands.*;
import com.chukanwobi.recipeapp.converters.categoryConverter.CategoryCommandToCategory;
import com.chukanwobi.recipeapp.converters.directionConverter.DirectionCommandToDirection;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientCommandToIngredient;
import com.chukanwobi.recipeapp.converters.notesConverter.NotesCommandToNote;
import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure;
import com.chukanwobi.recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
RecipeCommandToRecipe converter;
    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPITION= "some description";
    public static final Integer PREP_TIME= 2;
    public static final Integer COOK_TIME= 30;
    public static final Integer SERVINGS= 34;
    public static final String SOURCE = "My source";
    public static final String URL = "www.example.com";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Byte []IMAGE = {2,3,4,5};


    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    public static final Long DIRECTION_ID=2L;
    public static final Long DIRECTION_ID_1 = 4l;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),new NotesCommandToNote(),new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),new DirectionCommandToDirection());

    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }
@Test
public void testObjectIsNotNull(){


        assertNotNull(converter.convert(new RecipeCommand()));
}
    @Test
    public void testConvert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        Recipe recipe;

        recipeCommand.setId(LONG_VALUE);
        recipeCommand.setDescription(DESCRIPITION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setImage(IMAGE);
//create note objects for notes , directions, categories and ingredients add and test

        //notes
        NotesCommand notesCommand = new NotesCommand();
          notesCommand.setId(NOTES_ID);
         recipeCommand.setNotes(notesCommand);
          //directions
        DirectionCommand directionCommand = new DirectionCommand();
        directionCommand.setId(DIRECTION_ID);



        DirectionCommand directionCommand1 = new DirectionCommand();
        directionCommand1.setId(DIRECTION_ID_1);

        recipeCommand.getDirections().add(directionCommand);
        recipeCommand.getDirections().add(directionCommand1);

        //Categories
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID_1);

        CategoryCommand categoryCommand1 =  new CategoryCommand();
        categoryCommand1.setId(CAT_ID2);

        recipeCommand.getCategories().add(categoryCommand);
        recipeCommand.getCategories().add(categoryCommand1);


       //Ingredients
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGRED_ID_1);
        recipeCommand.getIngredients().add(ingredientCommand);


        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGRED_ID_2);
        recipeCommand.getIngredients().add(ingredientCommand1);





        recipe = converter.convert(recipeCommand);
        assertEquals(LONG_VALUE,recipe.getId());
        assertEquals(DESCRIPITION,recipe.getDescription());
        assertEquals(PREP_TIME,recipe.getPrepTime());
        assertEquals(COOK_TIME,recipe.getCookTime());
        assertEquals(SERVINGS,recipe.getServings());
        assertEquals(SOURCE,recipe.getSource());
        assertEquals(URL,recipe.getUrl());
        assertEquals(DIFFICULTY,recipe.getDifficulty());
        assertArrayEquals(IMAGE,recipe.getImage());
 assertEquals(NOTES_ID,recipe.getNotes().getId());

 assertEquals(2,recipe.getIngredients().size());
        assertEquals(2,recipe.getCategories().size());
        assertEquals(2,recipe.getDirections().size());
    }
}