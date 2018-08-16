package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.commands.DirectionCommand;
import com.chukanwobi.recipeapp.services.DirectionService;
import com.chukanwobi.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class DirectionController {
    private final RecipeService recipeService;
    private final DirectionService directionService;


    public DirectionController(RecipeService recipeService, DirectionService directionService) {
        this.recipeService = recipeService;
        this.directionService = directionService;
    }

    @GetMapping("/recipe/{recipeId}/directions/view&edit")
    public String listDirections(@PathVariable String recipeId, Model model){

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/direction/list";

    }
    @GetMapping("recipe/{recipeId}/directions/{directionId}/update/")
    public String updateDirections(@PathVariable String recipeId, @PathVariable String directionId,Model model){
        model.addAttribute("direction",directionService.findByRecipeIdAndDirectionID(
                Long.valueOf(recipeId),Long.valueOf(directionId)));
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
        model.addAttribute("recipeId",Long.valueOf(recipeId));
        return "recipe/direction/form";


    }
    @GetMapping("recipe/{recipeId}/directions/{directionId}/delete/")
    public String deleteDirection(@PathVariable String recipeId,@PathVariable String directionId){
        directionService.deleteById(Long.valueOf(recipeId),Long.valueOf(directionId));
        return "redirect:/recipe/"+ recipeId+"/directions/view&edit";
    }


    @PostMapping("/recipe/{recipeId}/direction")
    public String saveOrUpdate(@ModelAttribute DirectionCommand command,@PathVariable String recipeId){
        DirectionCommand savedDirectionCommand =  directionService.savedDirectionCommand(command);
        log.debug("saved recipe id: "+ savedDirectionCommand.getRecipeId());
        log.debug("saved direction id: " +savedDirectionCommand.getId());
        return "redirect:/recipe/"+savedDirectionCommand.getRecipeId()+"/directions/view&edit";
}
@GetMapping("/recipe/{recipeId}/direction/new")
    public String newDirection(@PathVariable String recipeId,Model model){

        DirectionCommand directionCommand = new DirectionCommand();

        directionCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("direction",directionCommand);
    model.addAttribute("recipeId",Long.valueOf(recipeId));
    return "recipe/direction/form";

}
}
