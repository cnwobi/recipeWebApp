package com.chukanwobi.recipeapp.commands;

import com.chukanwobi.recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private Long id;

    private String recipeNotes;
}
