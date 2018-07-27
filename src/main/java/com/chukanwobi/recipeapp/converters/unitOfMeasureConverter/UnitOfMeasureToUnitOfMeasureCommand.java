package com.chukanwobi.recipeapp.converters.unitOfMeasureConverter;

import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;

public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure,UnitOfMeasureCommand> {


    @Override
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
