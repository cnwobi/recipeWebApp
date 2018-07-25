package com.chukanwobi.recipeapp.domain;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;
@Data
@Entity

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;

}
