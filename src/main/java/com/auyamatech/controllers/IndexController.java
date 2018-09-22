package com.auyamatech.controllers;

import com.auyamatech.domain.Recipe;
import com.auyamatech.services.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Set;


@Controller
public class IndexController {
    private RecipeServiceImpl recipeService;

    public IndexController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/" , "", "/index"})
    public String getIndexPage(Model model) {
        Set<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);

        return "index";
    }
}
