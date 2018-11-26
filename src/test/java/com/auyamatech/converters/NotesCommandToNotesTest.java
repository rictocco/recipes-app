package com.auyamatech.converters;

import com.auyamatech.commands.NotesCommand;
import com.auyamatech.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    private NotesCommandToNotes converter;
    private final static Long ID_VALUE = new Long(1L);
    private final static String NOTES = "Description";

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void convert_NullReturnsNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert_EmptyReturnEmpty() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        //given
        NotesCommand command = new NotesCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(NOTES);

        //when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }
}