package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe findById(Long L);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
