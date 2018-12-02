package com.auyamatech.services;

import com.auyamatech.commands.IngredientCommand;
import com.auyamatech.commands.RecipeCommand;
import com.auyamatech.converters.*;
import com.auyamatech.domain.Ingredient;
import com.auyamatech.domain.Recipe;
import com.auyamatech.domain.UnitOfMeasure;
import com.auyamatech.repositories.RecipeRepository;
import com.auyamatech.repositories.UnitOfMeasureReposirory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    private IngredientService ingredientService;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    UnitOfMeasureReposirory unitOfMeasureReposirory;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        this.recipeToRecipeCommand = new RecipeToRecipeCommand(new NotesToNotesCommand(),
                ingredientToIngredientCommand, new CategoryToCategoryCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeToRecipeCommand, ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureReposirory);
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

    @Test
    public void testSaveIngredientCommand() throws Exception {
        //give
        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        ingredient.setDescription("Description");
        ingredient.setRecipe(savedRecipe);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(3L);

        ingredient.setUom(uom);
        savedRecipe.addIngredient(ingredient);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientToIngredientCommand.convert(ingredient));

        //then
        assertEquals(Long.valueOf(2L), savedIngredient.getId());
        assertEquals(ingredient.getRecipe().getId(), savedIngredient.getRecipeId());
        assertEquals(uom.getId(), savedIngredient.getUom().getId());
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository).save(any(Recipe.class));
    }

    @Test
    public void testDeleteByID() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);

        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        RecipeCommand returnCommand = ingredientService.deleteByRecipeIdAndIngredientId(1L, 2L);

        assertEquals(0,returnCommand.getIngredients().size());
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository).save(any(Recipe.class));
    }
}