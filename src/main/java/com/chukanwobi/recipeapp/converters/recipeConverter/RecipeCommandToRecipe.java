package com.chukanwobi.recipeapp.converters.recipeConverter;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.converters.categoryConverter.CategoryCommandToCategory;
import com.chukanwobi.recipeapp.converters.directionConverter.DirectionCommandToDirection;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientCommandToIngredient;
import com.chukanwobi.recipeapp.converters.notesConverter.NotesCommandToNote;
import com.chukanwobi.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final CategoryCommandToCategory commandToCategory;
    private final NotesCommandToNote commandToNote;
    private final IngredientCommandToIngredient commandToIngredient;
    private final DirectionCommandToDirection commandToDirection;

    public RecipeCommandToRecipe(CategoryCommandToCategory commandToCategory, NotesCommandToNote commandToNote,
                                 IngredientCommandToIngredient commandToIngredient, DirectionCommandToDirection
                                         commandToDirection) {
        this.commandToCategory = commandToCategory;
        this.commandToNote = commandToNote;
        this.commandToIngredient = commandToIngredient;
        this.commandToDirection = commandToDirection;
    }

    @Override
    @Synchronized
    @Nullable
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setImage(recipeCommand.getImage());
        recipe.setNotes(commandToNote.convert(recipeCommand.getNotes()));

        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
            recipeCommand.getIngredients().forEach(ingredientCommand -> recipe.getIngredients().add(commandToIngredient.convert(ingredientCommand)));
        }
        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
            recipeCommand.getCategories().forEach(categoryCommand -> recipe.getCategories().add(commandToCategory.convert(categoryCommand)));
        }
        if (recipeCommand.getDirections() != null && recipeCommand.getDirections().size() > 0) {
            recipeCommand.getDirections().forEach(directionCommand -> recipe.getDirections().add(commandToDirection.convert(directionCommand)));
        }
        return recipe;
    }
}
