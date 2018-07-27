package com.chukanwobi.recipeapp.converters.recipeConverter;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
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
    public static  final Set<Ingredient> INGREDIENT_SET = new HashSet<>();

    public static final Byte []IMAGE = {2,3,4,5};
    public static final Notes NOTES = new Notes();
    public static final Set<Category> CATEGORY_SET = new HashSet<>();
    public static final List<Direction> DIRECTION_LIST = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe();
        INGREDIENT_SET.add(new Ingredient());
        CATEGORY_SET.add(new Category());
        DIRECTION_LIST.add(new Direction());
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }
@Test
public void testObjectIsNotNull(){
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setNotes(new Notes());
        assertNotNull(converter.convert(recipeCommand));
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
        recipeCommand.setIngredients(INGREDIENT_SET);
        recipeCommand.setImage(IMAGE);
        recipeCommand.setNotes(NOTES);
        recipeCommand.setCategories(CATEGORY_SET);
        recipeCommand.setDirections(DIRECTION_LIST);

        recipe = converter.convert(recipeCommand);
        assertEquals(LONG_VALUE,recipe.getId());
        assertEquals(DESCRIPITION,recipe.getDescription());
        assertEquals(PREP_TIME,recipe.getPrepTime());
        assertEquals(COOK_TIME,recipe.getCookTime());
        assertEquals(SERVINGS,recipe.getServings());
        assertEquals(SOURCE,recipe.getSource());
        assertEquals(URL,recipe.getUrl());
        assertEquals(DIFFICULTY,recipe.getDifficulty());
        assertEquals(INGREDIENT_SET,recipe.getIngredients());
        assertArrayEquals(IMAGE,recipe.getImage());
        assertEquals(NOTES,recipe.getNotes());
        assertEquals(CATEGORY_SET,recipe.getCategories());
        assertEquals(DIRECTION_LIST,recipe.getDirections());

    }
}