package com.chukanwobi.recipeapp.converters.directionConverter;

import com.chukanwobi.recipeapp.commands.DirectionCommand;
import com.chukanwobi.recipeapp.domain.Direction;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DirectionToDirectionCommand implements Converter<Direction,DirectionCommand> {

    @Override
    @Synchronized
    @Nullable
    public DirectionCommand convert(Direction direction) {
        if(direction == null){
            return null;
        }
        DirectionCommand directionCommand = new DirectionCommand();
        directionCommand.setId(direction.getId());
      if(direction.getRecipe()!=null){
          directionCommand.setRecipeId(direction.getRecipe().getId());
      }
        directionCommand.setSteps(direction.getSteps());
        return directionCommand;
    }
}
