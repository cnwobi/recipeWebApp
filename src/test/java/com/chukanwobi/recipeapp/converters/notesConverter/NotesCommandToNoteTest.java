package com.chukanwobi.recipeapp.converters.notesConverter;

import com.chukanwobi.recipeapp.commands.NotesCommand;
import com.chukanwobi.recipeapp.domain.Notes;
import com.chukanwobi.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNoteTest {
NotesCommandToNote converter;
    public static final Long LONG_VALUE = 2L;
    public static final Recipe RECIPE =  new Recipe();
    public static final String RECIPE_NOTES = "This is a note";
    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNote();
    }
    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }
    @Test
    public void testObjectIsNotNull(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void testConvert() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(LONG_VALUE);
        notesCommand.setRecipe(RECIPE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);
        Notes notes = converter.convert(notesCommand);

        assertEquals(LONG_VALUE,notes.getId());
        assertEquals(RECIPE,notes.getRecipe());
        assertEquals(RECIPE_NOTES,notes.getRecipeNotes());
    }
}