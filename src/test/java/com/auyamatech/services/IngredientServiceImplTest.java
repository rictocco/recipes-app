package com.auyamatech.services;

import com.auyamatech.commands.IngredientCommand;
import com.auyamatech.converters.IngredientToIngredientCommand;
import com.auyamatech.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.auyamatech.domain.Ingredient;
import com.auyamatech.domain.Recipe;
import com.auyamatech.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    private IngredientService ingredientService;
    private final IngredientToIngredientCommand converter;

    @Mock
    RecipeRepository recipeRepository;

    public IngredientServiceImplTest() {
        this.converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(converter, recipeRepository);
    }

    @Test
    public void testFindByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient1.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient1.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        IngredientCommand command = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //then
        assertEquals(Long.valueOf(3L), command.getId());
        assertEquals(Long.valueOf(1L), command.getRecipeId());
        verify(recipeRepository).findById(anyLong());
    }
}