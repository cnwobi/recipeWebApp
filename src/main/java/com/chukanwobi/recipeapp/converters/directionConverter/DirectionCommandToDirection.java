package com.chukanwobi.recipeapp.converters.directionConverter;

import com.chukanwobi.recipeapp.commands.DirectionCommand;
import com.chukanwobi.recipeapp.domain.Direction;
import com.chukanwobi.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DirectionCommandToDirection implements Converter<DirectionCommand,Direction> {


    @Override
    @Synchronized
    @Nullable
    public Direction convert(DirectionCommand directionCommand) {
        if(directionCommand == null){
            return null;
        }
        Direction direction = new Direction();
        direction.setId(directionCommand.getId());
        direction.setSteps(directionCommand.getSteps());


        if(directionCommand.getRecipeId()!=null){
            Recipe recipe = new Recipe();
            recipe.setId(directionCommand.getRecipeId());
            direction.setRecipe(recipe);
            recipe.addDirections(direction);
        }
        return direction;

    }
}


