package com.chukanwobi.recipeapp.commands;

import com.chukanwobi.recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectionCommand {
    private Long id;
    private String steps;
    private Recipe recipe;
    private Long recipeId;
}
