package com.chukanwobi.recipeapp.converters.ingredientsConverter;

import com.chukanwobi.recipeapp.commands.IngredientCommand;

import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.services.UnitOfMeasureService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;



@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    @Autowired
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public Ingredient convert(IngredientCommand ingredientCommand) {

        if (ingredientCommand == null) {
            return null;
        }
        Ingredient ingredient = new Ingredient();

        if(ingredientCommand.getRecipeId()!=null){
            Recipe recipe = new Recipe();
            recipe.setId(ingredientCommand.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setAmount(ingredientCommand.getAmount());

        if(ingredientCommand.getUomId()!=null) {
          ingredient.setUnitOfMeasure(uomConverter.convert(unitOfMeasureService.findById(ingredientCommand.getUomId())));

        }

        return ingredient;
    }
}
