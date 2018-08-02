package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;
import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> findAllUnitOfMeasure();
   UnitOfMeasureCommand findById(Long L);
}
