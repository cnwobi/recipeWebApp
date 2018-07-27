package com.chukanwobi.recipeapp.converters.categoryConverter;

import com.chukanwobi.recipeapp.commands.CategoryCommand;
import com.chukanwobi.recipeapp.domain.Category;
import com.chukanwobi.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    CategoryToCategoryCommand coverter;
public static final Long LONG_VALUE = new Long(3l);
public static final String DESCRIPTIONS = "description";
public static final Set<Recipe> RECIPES= new HashSet<>();



    @Before
    public void setUp() throws Exception {

coverter = new CategoryToCategoryCommand();
RECIPES.add(new Recipe());
    }
@Test
public void testNullParameter(){
        assertNull(coverter.convert(null));
}

@Test
public void testObjectIsReturnedWhenNotNullIsPassed(){
assertNotNull(coverter.convert(new Category()));
}

@Test
    public void testConvert(){
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTIONS);
        category.setRecipes(RECIPES);
    CategoryCommand categoryCommand= coverter.convert(category);
    assertEquals(LONG_VALUE,categoryCommand.getId());
    assertEquals(DESCRIPTIONS,categoryCommand.getDescription());
    assertEquals(RECIPES,categoryCommand.getRecipes());



}


}