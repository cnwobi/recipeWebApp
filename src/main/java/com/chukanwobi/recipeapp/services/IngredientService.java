package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;

public interface IngredientService {
    Ingredient findById(Long l);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteById(Long recipeId,Long idToDelete);
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);


}
