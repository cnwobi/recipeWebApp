package com.chukanwobi.recipeapp.converters.recipeConverter;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
    public static  final Set<Ingredient> INGREDIENT_SET = new HashSet<>();

    public static final Byte []IMAGE = {2,3,4,5};
    public static final Notes NOTES = new Notes();
    public static final Set<Category> CATEGORY_SET = new HashSet<>();
    public static final List<Direction> DIRECTION_LIST = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand();
        INGREDIENT_SET.add(new Ingredient());
        CATEGORY_SET.add(new Category());
        DIRECTION_LIST.add(new Direction());
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
        recipe.setIngredients(INGREDIENT_SET);
        recipe.setImage(IMAGE);
        recipe.setNotes(NOTES);
        recipe.setCategories(CATEGORY_SET);
        recipe.setDirections(DIRECTION_LIST);

        recipeCommand = converter.convert(recipe);
        assertEquals(LONG_VALUE,recipeCommand.getId());
        assertEquals(DESCRIPITION,recipeCommand.getDescription());
        assertEquals(PREP_TIME,recipeCommand.getPrepTime());
        assertEquals(COOK_TIME,recipeCommand.getCookTime());
        assertEquals(SERVINGS,recipeCommand.getServings());
        assertEquals(SOURCE,recipeCommand.getSource());
        assertEquals(URL,recipeCommand.getUrl());
        assertEquals(DIFFICULTY,recipeCommand.getDifficulty());
        assertEquals(INGREDIENT_SET,recipeCommand.getIngredients());
        assertArrayEquals(IMAGE,recipeCommand.getImage());
        assertEquals(NOTES,recipeCommand.getNotes());
        assertEquals(CATEGORY_SET,recipeCommand.getCategories());
        assertEquals(DIRECTION_LIST,recipeCommand.getDirections());


    }
}