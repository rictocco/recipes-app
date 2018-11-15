package com.auyamatech.services;

import com.auyamatech.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long recipeId);
}
