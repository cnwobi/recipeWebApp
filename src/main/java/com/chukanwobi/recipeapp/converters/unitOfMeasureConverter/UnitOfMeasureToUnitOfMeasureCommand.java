package com.chukanwobi.recipeapp.converters.unitOfMeasureConverter;

import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure,UnitOfMeasureCommand> {


    @Override
    @Nullable
    @Synchronized
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure == null){
            return null;
        }
        final UnitOfMeasureCommand  unitOfMeasureCommand=  new UnitOfMeasureCommand();
      unitOfMeasureCommand.setId(unitOfMeasure.getId());
      unitOfMeasureCommand.setDescription(unitOfMeasure.getDescription());

        return unitOfMeasureCommand;
    }
}
