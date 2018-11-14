package com.auyamatech.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void setId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void setDescription() {
        String description = "italian";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @Test
    public void setRecipes() {
        Recipe recipe1 = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe1);
        category.setRecipes(recipes);
        assertEquals(1, recipes.size());
    }
}