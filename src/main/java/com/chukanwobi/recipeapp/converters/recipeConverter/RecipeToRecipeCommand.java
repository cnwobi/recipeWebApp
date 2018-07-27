package com.chukanwobi.recipeapp.converters.recipeConverter;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component

public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {




    @Override
    @Synchronized
    @Nullable
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null){
            return null;
        }
       RecipeCommand recipeCommand =  new RecipeCommand();
       recipeCommand.setId(recipe.getId());
       recipeCommand.setDescription(recipe.getDescription());
       recipeCommand.setPrepTime(recipe.getPrepTime());
       recipeCommand.setCookTime(recipe.getCookTime());
       recipeCommand.setServings(recipe.getServings());
       recipeCommand.setSource(recipe.getSource());
       recipeCommand.setUrl(recipe.getUrl());
       recipeCommand.setDifficulty(recipe.getDifficulty());

       recipeCommand.setImage(recipe.getImage());
       recipeCommand.setNotes(recipe.getNotes());
//       recipeCommand.setCategories(recipe.getCategories());
//       recipeCommand.setDirections(recipe.getDirections() );
//        recipeCommand.setIngredients(recipe.getIngredients());










        return recipeCommand;
    }
}
