package com.chukanwobi.recipeapp.converters.directionConverter;

import com.chukanwobi.recipeapp.commands.DirectionCommand;
import com.chukanwobi.recipeapp.domain.Direction;
import com.chukanwobi.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionCommandToDirectionTest {
    DirectionCommandToDirection converter;
    public static final Long LONG_VALUE = new Long(1l);
    public static final String STEPS = "cook me for 5 mins";
    public static final Recipe RECIPE = new Recipe();

    @Before
    public void setUp() throws Exception {
        converter = new DirectionCommandToDirection();
    }

    @Test
    public void testNullReturn() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testObjectisReturnedWhenNotNullIsPassed() {
        assertNotNull(converter.convert(new DirectionCommand()));
    }



    @Test
    public void testConvert() {
        DirectionCommand directionCommand = new DirectionCommand();
        directionCommand.setId(LONG_VALUE);
        directionCommand.setSteps(STEPS);
        directionCommand.setRecipe(RECIPE);
        Direction direction= converter.convert(directionCommand);
        assertEquals(LONG_VALUE,direction.getId());
        assertEquals(STEPS,direction.getSteps());
        assertEquals(RECIPE,direction.getRecipe());
    }
}