package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientCommandToIngredient;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientToIngredientCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.repositories.IngredientsRepository;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
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

    public IngredientServiceImpl(IngredientsRepository ingredientsRepository, IngredientCommandToIngredient ingredientCommandToIngredient, IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
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
    public IngredientCommand findByCommandById(Long id1) {
        return ingredientToIngredientCommand.convert(findById(id1));
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Ingredient unSavedIngredient = ingredientCommandToIngredient.convert(ingredientCommand);
        Ingredient savedIngredient = ingredientsRepository.save(unSavedIngredient);
        log.debug("Saved Ingredient: " + savedIngredient.getDescription());
        return ingredientToIngredientCommand.convert(savedIngredient);


    }

    @Override
    public void deleteById(Long l) {

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
}
