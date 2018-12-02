package com.auyamatech.services;

import com.auyamatech.commands.IngredientCommand;
import com.auyamatech.commands.RecipeCommand;
import com.auyamatech.domain.Ingredient;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    RecipeCommand deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
