package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.domain.Category;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import com.chukanwobi.recipeapp.repositories.CategoryRepository;
import com.chukanwobi.recipeapp.repositories.IngredientsRepository;
import com.chukanwobi.recipeapp.repositories.UnitOfMeasureRepository;
import com.chukanwobi.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
@Slf4j
@Controller
public class IndexController {

private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/","/index"})
    public String getIndex(Model model){
        log.debug("getting index page");
     model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
