package com.auyamatech.converters;

import com.auyamatech.commands.UnitOfMeasureCommand;
import com.auyamatech.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private final static Long LONG_VALUE = new Long(1L);
    private final static String DESCRIPTION = "Description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameters() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyParameter() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand uomCommand = converter.convert(uom);

        //then
        assertNotNull(uomCommand);
        assertEquals(LONG_VALUE, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());

    }
}