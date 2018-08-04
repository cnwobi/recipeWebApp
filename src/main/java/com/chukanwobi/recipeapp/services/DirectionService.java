package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.DirectionCommand;

public interface DirectionService {
    DirectionCommand findByRecipeIdAndDirectionID(Long recipeID,Long directionId);
    DirectionCommand savedDirectionCommand(DirectionCommand command);
}
