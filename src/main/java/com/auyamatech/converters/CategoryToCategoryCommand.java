package com.auyamatech.converters;

import com.auyamatech.commands.CategoryCommand;
import com.auyamatech.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert( Category source) {
        if (source == null) {
            return null;
        }

        final CategoryCommand category = new CategoryCommand();
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }
}
