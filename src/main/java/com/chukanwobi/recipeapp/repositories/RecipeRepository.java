package com.chukanwobi.recipeapp.repositories;

import com.chukanwobi.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
