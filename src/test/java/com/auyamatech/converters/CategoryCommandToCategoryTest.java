package com.auyamatech.converters;

import com.auyamatech.commands.CategoryCommand;
import com.auyamatech.domain.Category;
import com.auyamatech.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private CategoryCommandToCategory converter;
    private final static Long LONG_VALUE = new Long(1L);
    private final static String DESCRIPTION = "Description";

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void convert_NullReturnsNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert_EmptyReturnEmpty() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert_ReturnsCategory() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(LONG_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertNotNull(category);
        assertEquals(LONG_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }


}