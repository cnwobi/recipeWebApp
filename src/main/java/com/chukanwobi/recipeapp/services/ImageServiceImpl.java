package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.domain.Recipe;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Inside save image");
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Recipe recipe = null;
        if (recipeOptional.isPresent()){
            recipe = recipeOptional.get();
        }
        else {
            new RuntimeException("Recipe with ID " +recipeId+ " not found");
        }


        try {

            Byte[] imageObjectByte = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                imageObjectByte[i++] = b;

            }
            recipe.setImage(imageObjectByte);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("IO Exception Occured", e);
            e.printStackTrace();
        }
    }
}
