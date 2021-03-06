package com.chukanwobi.recipeapp.converters.categoryConverter;

import com.chukanwobi.recipeapp.commands.CategoryCommand;
import com.chukanwobi.recipeapp.domain.Category;
import com.chukanwobi.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {
    CategoryCommandToCategory converter;
    public static final Long LONG_VALUE = new Long(3l);
    public static final String DESCRIPTIONS = "description";
    public static final Set<Recipe> RECIPES= new HashSet<>();
    @Before
    public void setUp() throws Exception {
        converter =  new CategoryCommandToCategory();
        RECIPES.add(new Recipe());
    }

    @Test
    public void testReturnNull() {
        assertNull(converter.convert(null));
    }
    @Test
    public void testObjectIsReturnedWhenNotNullIsPassed(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }
@Test
    public void testConvert(){
        CategoryCommand categoryCommand= new CategoryCommand();
        categoryCommand.setId(LONG_VALUE);
        categoryCommand.setDescription(DESCRIPTIONS);

    Category category =  converter.convert(categoryCommand);
    assertEquals(LONG_VALUE,category.getId());
    assertEquals(DESCRIPTIONS,category.getDescription());

}
}