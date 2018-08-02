package com.chukanwobi.recipeapp.converters.ingredientsConverter;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import javafx.scene.AmbientLight;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
IngredientToIngredientCommand converter;
public static final Long LONG_ID = 1L;
public static final Long UOM_ID= 25l;
public static final String DESCRIPTION = "description";
public static final BigDecimal AMOUNT = new BigDecimal(2.4);
public static final UnitOfMeasure UNIT_OF_MEASURE = new UnitOfMeasure();
public static final Recipe RECIPE = new Recipe();
    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }
    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }
    @Test
    public void testObjectIsNotNull(){
        Ingredient ingredient = new Ingredient();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(2L);
        ingredient.setUnitOfMeasure(unitOfMeasure);
        assertNotNull(converter.convert(ingredient));
    }

    @Test
    public void testConvertNullUnitOfMeasure() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(LONG_ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUnitOfMeasure(UNIT_OF_MEASURE);
        ingredient.setRecipe(RECIPE);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertEquals(LONG_ID,ingredientCommand.getId());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(AMOUNT,ingredientCommand.getAmount());



    }
    @Test
    public void testConvertWithUOM(){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(LONG_ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUnitOfMeasure(unitOfMeasure);
        ingredient.setRecipe(RECIPE);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertEquals(LONG_ID,ingredientCommand.getId());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(AMOUNT,ingredientCommand.getAmount());
        assertEquals(UOM_ID,ingredientCommand.getUnitOfMeasure().getId());
    }
}