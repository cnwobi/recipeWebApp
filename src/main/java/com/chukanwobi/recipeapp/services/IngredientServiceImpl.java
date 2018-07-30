package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientCommandToIngredient;
import com.chukanwobi.recipeapp.converters.ingredientsConverter.IngredientToIngredientCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.repositories.IngredientsRepository;
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

    public IngredientServiceImpl(IngredientsRepository
                                         ingredientsRepository,
                                 IngredientCommandToIngredient
                                         ingredientCommandToIngredient,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
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
}
