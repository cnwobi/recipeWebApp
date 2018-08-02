package com.chukanwobi.recipeapp.converters.recipeConverter;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.converters.categoryConverter.CategoryToCategoryCommand;
import com.chukanwobi.recipeapp.converters.directionConverter.DirectionToDirectionCommand;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientToIngredientCommand;
import com.chukanwobi.recipeapp.converters.notesConverter.NotesToNotesCommand;
import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
    RecipeToRecipeCommand converter;
    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPITION= "some description";
    public static final Integer PREP_TIME= 2;
    public static final Integer COOK_TIME= 30;
    public static final Integer SERVINGS= 34;
    public static final String SOURCE = "My source";
    public static final String URL = "www.example.com";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Byte []IMAGE = {2,3,4,5};

    public static final Long INGREDIENT_ID_1= 1L;
    public static final Long INGREDIENT_ID_2=2L;
    public static final Long CATEGORY_ID_1 = 3L;
    public static final Long CATEGORY_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    public static final Long DIRECTION_ID_2 = 4L;
    public static final Long DIRECTION_ID = 9L;





    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),new DirectionToDirectionCommand(),new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),new NotesToNotesCommand());

    }

    @Test
    public void testNullParameter() {
assertNull(converter.convert(null));
    }
@Test
public void testObjectIsNotNullWhenNotNullIsPassed(){
        assertNotNull(converter.convert(new Recipe()));
}
    @Test
    public void testConvert() {
        Recipe recipe = new Recipe();
        RecipeCommand recipeCommand;
        recipe.setId(LONG_VALUE);
        recipe.setDescription(DESCRIPITION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setImage(IMAGE);
         // create Note, Directions, Categories ,INgredients add to Recipe

        //Notes
        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        //directions
        Direction direction =  new Direction();
        Direction direction1 =  new Direction();

        direction.setId(DIRECTION_ID);
        direction1.setId(DIRECTION_ID_2);

        recipe.getDirections().add(direction);
        recipe.getDirections().add(direction1);


        //categories
        Category category =  new Category();
        category.setId(CATEGORY_ID_1);

        Category category1 =  new Category();
        category1.setId(CATEGORY_ID_2);


        recipe.getCategories().add(category);
        recipe.getCategories().add(category1);

//ingredients
        Ingredient ingredient = new Ingredient();
        Ingredient ingredient1 = new Ingredient();
        ingredient.setId(INGREDIENT_ID_1);
        ingredient.setId(INGREDIENT_ID_2);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(2l);
        ingredient.setUnitOfMeasure(unitOfMeasure);
        ingredient1.setUnitOfMeasure(unitOfMeasure);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient1);


        recipeCommand = converter.convert(recipe);
        assertEquals(LONG_VALUE,recipeCommand.getId());
        assertEquals(DESCRIPITION,recipeCommand.getDescription());
        assertEquals(PREP_TIME,recipeCommand.getPrepTime());
        assertEquals(COOK_TIME,recipeCommand.getCookTime());
        assertEquals(SERVINGS,recipeCommand.getServings());
        assertEquals(SOURCE,recipeCommand.getSource());
        assertEquals(URL,recipeCommand.getUrl());
        assertEquals(DIFFICULTY,recipeCommand.getDifficulty());
        assertArrayEquals(IMAGE,recipeCommand.getImage());

        assertEquals(NOTES_ID,recipeCommand.getNotes().getId());

        assertEquals(2,recipeCommand.getCategories().size());

        assertEquals(DIRECTION_ID,recipeCommand.getDirections().get(0).getId());

        assertEquals(DIRECTION_ID_2,recipeCommand.getDirections().get(1).getId());

        assertEquals(2,recipeCommand.getIngredients().size());



    }
}