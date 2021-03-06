package com.chukanwobi.recipeapp.controllers;

import com.chukanwobi.recipeapp.commands.RecipeCommand;
import com.chukanwobi.recipeapp.exceptions.ImageNotFoundException;
import com.chukanwobi.recipeapp.services.ImageService;
import com.chukanwobi.recipeapp.services.RecipeService;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Controller
@Slf4j
public class ImageController {
    private final ImageService imageService;
    private  final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping ("/recipe/{recipeId}/image/update")
    public String updateForm(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
        model.addAttribute("recipezId",Long.valueOf(recipeId));

        return "recipe/image/form";

    }
    @PostMapping("recipe/{id}/image")
    public String recieveImagePost(@PathVariable String id, @RequestParam("imagefile")MultipartFile file){
        imageService.saveImageFile(Long.valueOf(id),file);
        return "redirect:/recipe/" + id +"/update";
    }
    @GetMapping("/recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws Exception{
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
         if(recipeCommand.getImage()==null){
             throw new ImageNotFoundException("Image not found");
         }
        byte[] bytes = new byte[recipeCommand.getImage().length];
        int i = 0;
        for (Byte b : recipeCommand.getImage()){
            bytes[i++] = b;
            log.debug(b.toString()+"nothingpiol");
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream,response. getOutputStream());
    }

}
