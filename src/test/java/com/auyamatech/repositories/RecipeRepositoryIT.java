package com.auyamatech.repositories;

import com.auyamatech.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryIT {

    @Autowired
    UnitOfMeasureReposirory unitOfMeasureReposirory;

    @Before
    public void setUp() {
    }

    @Test
    public void findByDescriptionTeaspoon() throws Exception{
        Optional<UnitOfMeasure> uofOptional = unitOfMeasureReposirory.findByDescription("Teaspoon");

        assertEquals("Teaspoon", uofOptional.get().getDescription());
    }

    @Test
    @DirtiesContext
    public void findByDescriptionCup() throws Exception{
        Optional<UnitOfMeasure> uofOptional = unitOfMeasureReposirory.findByDescription("Cup");

        assertEquals("Cup", uofOptional.get().getDescription());
    }
}