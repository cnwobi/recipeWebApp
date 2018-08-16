package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.exceptions.NotFoundException;
import com.chukanwobi.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipe/show";


    }
    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping( "recipe")
    public String saveOrUpdate(@Valid @ModelAttribute RecipeCommand command, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {log.debug(objectError.toString());});
        }
        RecipeCommand savedCommand =  recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId()+"/show";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id,Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable Long id){
        recipeService.deleteById(id);
        return "redirect:/index";
    }



}
