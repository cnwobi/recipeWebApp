package com.chukanwobi.recipeapp.converters.ingredientsConverter;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient,IngredientCommand> {
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
ingredientCommand.setUnitOfMeasure(ingredient.getUnitOfMeasure());
ingredientCommand.setRecipe(ingredient.getRecipe());


        return ingredientCommand;

    }
}
