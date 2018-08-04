package com.chukanwobi.recipeapp.repositories;

import com.chukanwobi.recipeapp.domain.Direction;
import org.springframework.data.repository.CrudRepository;

public interface DirectionsRepository extends CrudRepository<Direction,Long> {
}
