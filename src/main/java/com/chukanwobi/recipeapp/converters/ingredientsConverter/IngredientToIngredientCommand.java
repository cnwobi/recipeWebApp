package com.chukanwobi.recipeapp.converters.ingredientsConverter;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.converters.unitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient,IngredientCommand> {
    private final UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommandConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommandConverter) {
        this.toUnitOfMeasureCommandConverter = toUnitOfMeasureCommandConverter;
    }

    @Override
    @Synchronized
    @Nullable


    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setUnitOfMeasure(toUnitOfMeasureCommandConverter.convert(ingredient.getUnitOfMeasure()));


        if(ingredient.getRecipe()!=null){
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }

        /// get

    ingredientCommand.setUomId(ingredientCommand.getUnitOfMeasure().getId());


        return ingredientCommand;

    }
}
