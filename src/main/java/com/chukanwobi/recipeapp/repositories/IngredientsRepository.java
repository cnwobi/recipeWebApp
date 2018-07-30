package com.chukanwobi.recipeapp.repositories;

import com.chukanwobi.recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientsRepository extends CrudRepository<Ingredient,Long> {

    Optional<Ingredient> findByDescription(String description);

}
