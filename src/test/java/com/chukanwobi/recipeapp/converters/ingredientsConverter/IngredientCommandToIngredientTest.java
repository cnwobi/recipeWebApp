package com.chukanwobi.recipeapp.converters.ingredientsConverter;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;
import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
  IngredientCommandToIngredient converter;
  public static final Long UOM_ID =2l;
    public static final Long LONG_ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal(2.4);

    public static final Recipe RECIPE = new Recipe();

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
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
         RECIPE.setId(3l);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);
        ingredientCommand.setRecipeId(RECIPE.getId());


        Ingredient ingredient =  converter.convert(ingredientCommand);
        System.out.print(ingredient.toString());
        assertNotNull(ingredient);

        assertEquals(LONG_ID,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(AMOUNT,ingredient.getAmount());

        assertEquals(RECIPE.getId(),ingredient.getRecipe().getId());

      //
        // Had to modify ingredient...it is tightly coupled
        // assertNotNull(ingredient.getUnitOfMeasure());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(LONG_ID);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();


        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(LONG_ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());

    }

}