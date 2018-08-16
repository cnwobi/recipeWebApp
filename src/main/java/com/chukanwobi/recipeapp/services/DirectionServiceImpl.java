package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.DirectionCommand;
import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.converters.directionConverter.DirectionCommandToDirection;
import com.chukanwobi.recipeapp.converters.directionConverter.DirectionToDirectionCommand;
import com.chukanwobi.recipeapp.domain.Direction;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
@Service

public class DirectionServiceImpl implements DirectionService{
    private final DirectionToDirectionCommand directionToDirectionCommand;
    private final DirectionCommandToDirection directionCommandToDirection;
   private final RecipeService recipeService;
   private final RecipeRepository recipeRepository;

    public DirectionServiceImpl(DirectionToDirectionCommand directionToDirectionCommand, DirectionCommandToDirection directionCommandToDirection,
                                RecipeService recipeService, RecipeRepository recipeRepository) {
        this.directionToDirectionCommand = directionToDirectionCommand;
        this.directionCommandToDirection = directionCommandToDirection;
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public DirectionCommand findByRecipeIdAndDirectionID(Long recipeId, Long directionId) {
        Recipe recipe = recipeService.findById(recipeId);
        Optional<DirectionCommand> command =  recipe.getDirections().stream()
                .filter(direction -> direction.getId().equals(directionId))
                .map(direction -> directionToDirectionCommand.convert(direction)).findFirst();
        if(!command.isPresent()){
            new RuntimeException("Direction id not found: "+directionId);
        }
        return command.get();

    }
@Override
    public DirectionCommand savedDirectionCommand(DirectionCommand command) {
        log.debug(command.toString());
        Optional<Recipe> recipeOptional = recipeRepository
                .findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {
            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipe().getId());
            return new DirectionCommand();
        } else {
            Recipe recipe = recipeOptional.get();
            Optional<Direction> optionalDirection = recipe
                    .getDirections()
                    .stream()
                    .filter(direction -> direction.getId()
                            .equals(command.getId()))
                    .findFirst();
            if (optionalDirection.isPresent()) {
                Direction directionFound = optionalDirection.get();
                directionFound.setId(command.getId());
                directionFound.setSteps(command.getSteps());
                log.debug("debug  " + command.toString());
            } else {
                //add new direction
                recipe.addDirections(directionCommandToDirection.convert(command));

                log.debug(recipe.toString());
            }
            Recipe savedRecipe = recipeRepository.save(recipe);
            Optional<Direction> savedDirectionOptional =
                    savedRecipe.getDirections()
                            .stream()
                            .filter(recipeDirection -> recipeDirection.getId()
                                    .equals(command.getId())).findFirst();


savedRecipe.getDirections().stream().forEach(direction -> System.out.print("Direciton\n\n\n\n"+direction.toString()));
            if (!savedDirectionOptional.isPresent()) {
                savedDirectionOptional = savedRecipe
                        .getDirections().stream()
                        .filter(recipeDirection -> recipeDirection.getSteps()
                                .matches(command.getSteps()))


                        .findFirst();
            }
            log.debug("is it "+ savedDirectionOptional.isPresent());
            return directionToDirectionCommand.convert(savedDirectionOptional.get());

        }
    }

    @Override
    public void deleteById(Long recipeId, Long idToDelete) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            Optional<Direction> directionOptional = recipe.getDirections()
                    .stream().filter(direction -> direction.getId().equals(idToDelete))
                    .findFirst();
            if(directionOptional.isPresent()){
                log.debug("found direction"+directionOptional.get().toString());

                Direction directionToDelete =  directionOptional.get();
                directionToDelete.setRecipe(null);
                recipe.getDirections().remove(directionToDelete);
            }
        }
        else {
            log.debug("Recipe with ID " + recipeId+" not found");
        }
    }
}
