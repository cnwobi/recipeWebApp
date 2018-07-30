package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.services.RecipeService;
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

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
@GetMapping("/recipe/{recipeId}/ingredients/view&edit")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Retrieving ingredient list for recipe id:"+ recipeId);
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }


}
