package com.chukanwobi.recipeapp.commands;

import com.chukanwobi.recipeapp.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private Difficulty difficulty;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Byte[] image;
    private Notes notes;
    private Set<Category> categories= new HashSet<>();
    private List<Direction> directions = new ArrayList<>();

}
