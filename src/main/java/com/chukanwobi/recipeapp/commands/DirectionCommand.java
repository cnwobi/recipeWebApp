package com.chukanwobi.recipeapp.commands;

import com.chukanwobi.recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class DirectionCommand {
    private Long id;
    @Size(min = 4,max = 255)
    private String steps;
    private Recipe recipe;
    private Long recipeId;
}
