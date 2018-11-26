package com.auyamatech.converters;

import com.auyamatech.commands.NotesCommand;
import com.auyamatech.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert( Notes source) {
        if (source == null) {
            return null;
        }

        final NotesCommand notes = new NotesCommand();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());

        return notes;
    }
}
