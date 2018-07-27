package com.chukanwobi.recipeapp.converters;

import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = new Long(2L);
    UnitOfMeasureToUnitOfMeasureCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }
@Test
public void testNullParameter(){
        assertNull(converter.convert(null));
}

    @Test
    public void testObjectNotNull() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void testConverter() {
        UnitOfMeasure unitOfMeasure =  new UnitOfMeasure();
        unitOfMeasure.setDescription(DESCRIPTION);
        unitOfMeasure.setId(LONG_VALUE);

        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        assertEquals(DESCRIPTION,unitOfMeasureCommand.getDescription());
        assertEquals(LONG_VALUE,unitOfMeasureCommand.getId());
    }
}