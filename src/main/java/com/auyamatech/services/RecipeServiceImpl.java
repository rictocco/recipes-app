package com.auyamatech.services;

import com.auyamatech.domain.Recipe;
import com.auyamatech.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> getRecipes() {
        log.debug("Hey I am inside the project");
        
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if(!recipe.isPresent()) {
            throw new RuntimeException("Recipe Not Found");
        }
        return recipe.get();
    }
}
