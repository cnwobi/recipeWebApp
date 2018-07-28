package com.chukanwobi.recipeapp.converters.recipeConverter;

import com.chukanwobi.recipeapp.commands.CategoryCommand;
import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.converters.categoryConverter.CategoryToCategoryCommand;
import com.chukanwobi.recipeapp.converters.directionConverter.DirectionToDirectionCommand;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientToIngredientCommand;
import com.chukanwobi.recipeapp.converters.notesConverter.NotesToNotesCommand;
import com.chukanwobi.recipeapp.domain.Category;
import com.chukanwobi.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component

public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {
    private final CategoryToCategoryCommand categoryConverter;
    private final DirectionToDirectionCommand directionConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;


    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, DirectionToDirectionCommand directionConverter,
                                 IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.directionConverter = directionConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

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

       recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));


if(recipe.getIngredients() !=null && recipe.getIngredients().size()>0){
    recipe.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
}
if(recipe.getCategories() !=null && recipe.getCategories().size()>0){
    recipe.getCategories().forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
}

if(recipe.getDirections() !=null && recipe.getDirections().size()>0){
    recipe.getDirections().forEach(direction -> recipeCommand.getDirections().add(directionConverter.convert(direction)));
}






        return recipeCommand;
    }
}
