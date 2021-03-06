package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientCommandToIngredient;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientToIngredientCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.repositories.IngredientsRepository;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
import com.chukanwobi.recipeapp.repositories.UnitOfMeasureRepository;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
    private final IngredientsRepository ingredientsRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientsRepository ingredientsRepository,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Ingredient findById(Long id) {

       Optional<Ingredient> ingredientOptional = ingredientsRepository.findById(id);
       if(!ingredientOptional.isPresent()){
           new RuntimeException("Ingredient with Id {"+id+"} not found");
       }
       return ingredientOptional.get();
    }



    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                 log.debug("\n\n\n\n\n\n\n\n\ndebug...."+command.toString());

                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(command.getUomId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);
            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){

                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(command.getUomId()))
                        .findFirst();

            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }

    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe>     recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            new RuntimeException("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

    if (!ingredientCommandOptional.isPresent()){
        new RuntimeException("Ingredient id not found: " + ingredientId);
    }
return ingredientCommandOptional.get();

    }

    @Override
    public void deleteById(Long recipeId, Long idToDelete) {
        Optional<Recipe>     recipeOptional = recipeRepository.findById(recipeId);
        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional =  recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();
            if(ingredientOptional.isPresent()){
                log.debug("found Ingredient"+ ingredientOptional.get().toString());

                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
            }

        }else {
            log.debug("Recipe.id not found");
        }

    }
}
