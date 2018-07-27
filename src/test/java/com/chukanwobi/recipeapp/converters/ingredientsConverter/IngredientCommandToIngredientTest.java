package com.chukanwobi.recipeapp.converters.ingredientsConverter;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
  IngredientCommandToIngredient converter;
    public static final Long LONG_ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal(2.4);
    public static final UnitOfMeasure UNIT_OF_MEASURE = new UnitOfMeasure();
    public static final Recipe RECIPE = new Recipe();

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient();
    }
@Test
public void testNullParameter(){
        assertNull(converter.convert(null));
}
@Test
public void testObjectIsNotNull(){
        assertNotNull(converter.convert(new IngredientCommand()));
}
    @Test
    public void testConvert() {
        IngredientCommand ingredientCommand =  new IngredientCommand();
        ingredientCommand.setId(LONG_ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUnitOfMeasure(UNIT_OF_MEASURE);
        ingredientCommand.setRecipe(RECIPE);
        Ingredient ingredient =  converter.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(LONG_ID,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(AMOUNT,ingredient.getAmount());
        assertEquals(UNIT_OF_MEASURE,ingredient.getUnitOfMeasure());
        assertEquals(RECIPE,ingredient.getRecipe());


    }
}