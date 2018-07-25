package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.domain.Category;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.domain.UnitOfMeasure;
import com.chukanwobi.recipeapp.repositories.CategoryRepository;
import com.chukanwobi.recipeapp.repositories.IngredientsRepository;
import com.chukanwobi.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private IngredientsRepository ingredientsRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    public IndexController(IngredientsRepository ingredientsRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository= categoryRepository;
    }

    @GetMapping({"","/","/index"})
    public String getIndex(Model model){
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<Category>optionalCategory = categoryRepository.findByDescription("American");
        model.addAttribute("uom",optionalUnitOfMeasure);
        model.addAttribute("cat",optionalCategory);
        return "index";
    }
}
