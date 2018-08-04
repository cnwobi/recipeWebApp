package com.chukanwobi.recipeapp.converters.directionConverter;

import com.chukanwobi.recipeapp.commands.DirectionCommand;
import com.chukanwobi.recipeapp.domain.Direction;
import com.chukanwobi.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionToDirectionCommandTest {
    DirectionToDirectionCommand converter;
    public static final Long LONG_VALUE = new Long(1l);
    public static final String STEPS = "cook me for 5 mins";
    public static final Recipe RECIPE = new Recipe();

    @Before
    public void setUp() throws Exception {
converter =  new DirectionToDirectionCommand();
    }

    @Test
    public void testNullReturn() {
        assertNull(converter.convert(null));
    }
    @Test
    public void testObjectIsReturnedWhenNotNullIsPassed(){
        assertNotNull(converter.convert(new Direction("")));
    }

    @Test
    public void testConvert(){
        Direction direction = new Direction("");
        direction.setId(LONG_VALUE);
        direction.setRecipe(RECIPE);
        direction.setSteps(STEPS);
        DirectionCommand directionCommand = converter.convert(direction);
        assertEquals(LONG_VALUE,directionCommand.getId());
        assertEquals(RECIPE.getId(),directionCommand.getRecipeId());
        assertEquals(STEPS,directionCommand.getSteps());
    }
}