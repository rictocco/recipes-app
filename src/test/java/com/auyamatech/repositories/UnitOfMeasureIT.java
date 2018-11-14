package com.auyamatech.repositories;

import com.auyamatech.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureIT {

    @Autowired
    private UnitOfMeasureReposirory unitOfMeasureReposirory;

    @Before
    public void setUp() {
    }

    @Test
    @DirtiesContext //Use this to restore the Spring context if the test changes something in the database.
    public void findByDescription() {
        Optional<UnitOfMeasure> uofOptional = unitOfMeasureReposirory.findByDescription("Teaspoon");

        assertEquals("Teaspoon", uofOptional.get().getDescription());
    }

    @Test
    @DirtiesContext
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> uofOptional = unitOfMeasureReposirory.findByDescription("Cup");

        assertEquals("Cup", uofOptional.get().getDescription());
    }
}