package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.domain.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe findById(Long L);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand findCommandById(Long L);
    void deleteById(Long L);
}
