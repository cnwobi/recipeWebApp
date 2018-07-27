package com.chukanwobi.recipeapp.converters.notesConverter;

import com.chukanwobi.recipeapp.commands.NotesCommand;
import com.chukanwobi.recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNote implements Converter<NotesCommand,Notes> {

    @Override
    @Synchronized
    @Nullable
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand == null) {
            return null;
        }
        Notes notes = new Notes();
        notes.setId(notesCommand.getId());
        notes.setRecipe(notesCommand.getRecipe());
        notes.setRecipeNotes(notesCommand.getRecipeNotes());

        return notes;
    }
}
