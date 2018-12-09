package com.auyamatech.services;

import com.auyamatech.commands.RecipeCommand;
import com.auyamatech.converters.RecipeCommandToRecipe;
import com.auyamatech.converters.RecipeToRecipeCommand;
import com.auyamatech.domain.Recipe;
import com.auyamatech.exceptions.NotFoundException;
import com.auyamatech.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipesTest() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        Long recipeId = 1L;
        recipe.setId(recipeId);

        when(recipeRepository.findById(recipe.getId())).thenReturn(Optional.of(recipe));

        Recipe returnRecipe = recipeService.findById(recipeId);

        assertNotNull("Null recipe returned", returnRecipe);
        assertEquals(recipe, returnRecipe);
        verify(recipeRepository).findById(recipeId);
        verify(recipeRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() {
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipe = recipeService.findById(1L);
    }

    @Test
    public void getRecipeCommandByIdTest() {
        Recipe recipe = new Recipe();
        Long recipeId = 1L;
        recipe.setId(recipeId);

        when(recipeRepository.findById(recipe.getId())).thenReturn(Optional.of(recipe));

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipeId);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand command = recipeService.findCommandById(recipeId);

        assertNotNull("Null recipe returned", command);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void deleteByIdTest() {
        //given
        Long id = Long.valueOf(2L);

        //when
        recipeRepository.deleteById(id);
        //no 'when', since delete method has void return type

        //then
        verify(recipeRepository).deleteById(anyLong());
    }
}