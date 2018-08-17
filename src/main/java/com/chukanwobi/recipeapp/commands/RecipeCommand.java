package com.chukanwobi.recipeapp.commands;

import com.chukanwobi.recipeapp.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
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
    @Size(min = 3, max = 255)
    private String description;


    @NotNull
    @Digits(integer = 4,fraction = 0)
    @Min(3)
    private Integer prepTime;

    @PositiveOrZero
    @NotNull
    private Integer cookTime;
    @NotNull
    @Positive
    private Integer servings;

    @Size(min = 4, max = 255)
    private String source;
    @URL
    private String url;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    @NotNull
    private NotesCommand notes;
    private Set<CategoryCommand> categories= new HashSet<>();
    private List<DirectionCommand> directions = new ArrayList<>();

}
