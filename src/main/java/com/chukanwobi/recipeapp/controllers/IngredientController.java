package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.services.IngredientService;
import com.chukanwobi.recipeapp.services.RecipeService;
import com.chukanwobi.recipeapp.services.UnitOfMeasureService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller

public class IngredientController {
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, UnitOfMeasureService unitOfMeasureService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients/view&edit")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Retrieving ingredient list for recipe id:"+ recipeId);
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

@GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/update/")
    public String updateIngredients(@PathVariable String recipeId, @PathVariable String ingredientId,Model model){
      model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));
       model.addAttribute("uom",unitOfMeasureService.findAllUnitOfMeasure());

        return "recipe/ingredient/form";
}

}
