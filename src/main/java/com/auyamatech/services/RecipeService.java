package com.auyamatech.services;

import com.auyamatech.commands.RecipeCommand;
import com.auyamatech.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long recipeId);

    RecipeCommand findCommandById(Long recipeId);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long recipeId);
}
