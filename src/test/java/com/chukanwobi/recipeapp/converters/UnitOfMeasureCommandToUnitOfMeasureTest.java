package com.chukanwobi.recipeapp.converters;

import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class UnitOfMeasureCommandToUnitOfMeasureTest {
    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1l);

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp()  {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testObjectIsNotNull() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void testConverter() {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        UnitOfMeasure unitOfMeasure;

        unitOfMeasureCommand.setDescription(DESCRIPTION);
        unitOfMeasureCommand.setId(LONG_VALUE);

        unitOfMeasure=converter.convert(unitOfMeasureCommand);

        assertEquals(LONG_VALUE,unitOfMeasure.getId());
        assertEquals(DESCRIPTION,unitOfMeasure.getDescription());
    }
}
