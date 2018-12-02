package com.auyamatech.services;

import com.auyamatech.commands.UnitOfMeasureCommand;
import com.auyamatech.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.auyamatech.domain.UnitOfMeasure;
import com.auyamatech.repositories.UnitOfMeasureReposirory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureReposirory reposirory;
    @Mock
    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(reposirory, converter);
    }

    @Test
    public void findAllUoms() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);

        Set<UnitOfMeasure> uoms = new HashSet<>();
        uoms.add(uom1);

        when(reposirory.findAll()).thenReturn(uoms);

        Set<UnitOfMeasureCommand> commands = unitOfMeasureService.findAllUoms();

        assertNotNull(commands);
        assertEquals(1, commands.size());
        verify(reposirory, times(1)).findAll();



    }
}