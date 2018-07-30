package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> findAllUnitOfMeasure();
}
