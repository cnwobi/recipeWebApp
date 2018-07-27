package com.chukanwobi.recipeapp.converters.notesConverter;

import com.chukanwobi.recipeapp.commands.NotesCommand;
import com.chukanwobi.recipeapp.domain.Notes;
import com.chukanwobi.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    NotesToNotesCommand converter;
    public static final Long LONG_VALUE = 2L;
    public static final Recipe RECIPE =  new Recipe();
    public static final String RECIPE_NOTES = "This is a note";
    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }
@Test
public void testNullParameter(){
        assertNull(converter.convert(null));
}
@Test
public void testObjectIsNotNull(){
        assertNotNull(converter.convert(new Notes()));
}
    @Test
    public void testConvert() {
        Notes notes =  new Notes();
        notes.setId(LONG_VALUE);
        notes.setRecipe(RECIPE);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand notesCommand = converter.convert(notes);

        assertEquals(LONG_VALUE,notesCommand.getId());
        assertEquals(RECIPE,notesCommand.getRecipe());
        assertEquals(RECIPE_NOTES,notesCommand.getRecipeNotes());
    }
}