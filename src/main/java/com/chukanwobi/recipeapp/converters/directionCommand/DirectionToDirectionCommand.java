package com.chukanwobi.recipeapp.converters.directionCommand;

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
        directionCommand.setRecipe(direction.getRecipe());
        directionCommand.setSteps(direction.getSteps());
        return directionCommand;
    }
}
