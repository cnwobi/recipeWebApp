package com.chukanwobi.recipeapp.converters.ingredientsConverter;

import com.chukanwobi.recipeapp.commands.IngredientCommand;

import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure;
import com.chukanwobi.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;



@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

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
        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setUnitOfMeasure(uomConverter.convert(ingredientCommand.getUnitOfMeasure()));
        ingredient.setRecipe(ingredientCommand.getRecipe());


        return ingredient;
    }
}
