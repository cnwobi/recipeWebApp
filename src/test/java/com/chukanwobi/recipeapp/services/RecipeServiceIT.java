package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.converters.recipeConverter.RecipeCommandToRecipe;
import com.chukanwobi.recipeapp.converters.recipeConverter.RecipeToRecipeCommand;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RecipeServiceIT {
    public static final String NEW_DESCRIPTION = "SOme descriptiom";
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;


@Transactional
@Test
    public void testSaveOfDescription(){
        Iterable<Recipe> recipes = recipeRepository.findAll();

        Recipe testRecipe = recipes.iterator().next();

        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        testRecipeCommand.setDescription(NEW_DESCRIPTION);

        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(testRecipeCommand);

        assertEquals(NEW_DESCRIPTION,savedRecipe.getDescription());
        assertEquals(testRecipe.getId(),savedRecipe.getId());
        assertEquals(testRecipe.getCategories().size(),savedRecipe.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),savedRecipe.getIngredients().size());
    }
}
