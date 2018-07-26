package com.chukanwobi.recipeapp.services;

import com.chukanwobi.recipeapp.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long L);
}
