package com.auyamatech.converters;

import com.auyamatech.commands.NotesCommand;
import com.auyamatech.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    private NotesToNotesCommand converter;
    private final static Long ID_VALUE = new Long(1L);
    private final static String NOTES = "Description";

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void convert_NullReturnsNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert_EmptyReturnEmpty() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(NOTES);

        //when
        NotesCommand command = converter.convert(notes);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(NOTES, command.getRecipeNotes());
    }
}