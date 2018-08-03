package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.commands.IngredientCommand;
import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.domain.Ingredient;
import com.chukanwobi.recipeapp.services.IngredientService;
import com.chukanwobi.recipeapp.services.RecipeService;
import com.chukanwobi.recipeapp.services.UnitOfMeasureService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.criteria.CriteriaBuilder;

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
        model.addAttribute("recipe",recipeService.
                findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

@GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/update/")
    public String updateIngredients(@PathVariable String recipeId, @PathVariable String ingredientId,Model model){
      model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));
       model.addAttribute("uom",unitOfMeasureService.findAllUnitOfMeasure());
       model.addAttribute("idRecipe",Long.valueOf(recipeId));
        return "recipe/ingredient/form";
}
    @PostMapping("recipe/{recipeId}/ingredient")
public String saveOrUpdate(@ModelAttribute IngredientCommand command,@PathVariable String recipeId){
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    log.debug("\n\n\n\n\nsaved recipe id:" + savedCommand.getRecipeId());
    log.debug("saved ingredient id:" + savedCommand.getId());

    return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/view&edit";
}
@GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe (@PathVariable String recipeId,Model model){
    RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setRecipeId(Long.valueOf(recipeId));
    model.addAttribute("ingredient",ingredientCommand);

    model.addAttribute("uom",unitOfMeasureService.findAllUnitOfMeasure());
    return "recipe/ingredient/form";

}
@GetMapping("recipe/{recipeId}/ingredients/{ingredientId}/delete")
    public String delete(@PathVariable String recipeId,@PathVariable String ingredientId ){
        ingredientService.deleteById(Long.valueOf(recipeId),Long.valueOf(ingredientId));

        return "redirect:/recipe/"+ recipeId+"/ingredients/view&edit";

}


}
