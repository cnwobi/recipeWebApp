package com.chukanwobi.recipeapp.commands;

import com.chukanwobi.recipeapp.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    @NotBlank
    @Size(min = 3)
    private String description;


    @NotNull
    private Integer prepTime;
    @NotNull
    private Integer cookTime;
    @NotNull
    private Integer servings;
    @NotBlank
    private String source;
    @URL
    private String url;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private NotesCommand notes;
    private Set<CategoryCommand> categories= new HashSet<>();
    private List<DirectionCommand> directions = new ArrayList<>();

}
