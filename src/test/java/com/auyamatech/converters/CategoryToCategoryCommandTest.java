package com.auyamatech.converters;

import com.auyamatech.commands.CategoryCommand;
import com.auyamatech.domain.Category;
import com.auyamatech.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private CategoryToCategoryCommand converter;
    private final static Long LONG_VALUE = new Long(1L);
    private final static String DESCRIPTION = "Description";

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void convert_NullReturnsNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert_EmptyReturnEmpty() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert_ReturnsCategory() {
        //given
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand command = converter.convert(category);

        //then
        assertNotNull(category);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}