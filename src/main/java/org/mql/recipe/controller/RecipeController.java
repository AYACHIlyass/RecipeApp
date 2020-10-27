package org.mql.recipe.controller;

import org.mql.recipe.model.Recipe;
import org.mql.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recipe")
public class RecipeController  {

    private RecipeService service;

    @Autowired
    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/view")
    public String getRecipeById( @RequestParam("id") Long id ,  Model model) {
        Recipe recipe = service.findById(id);
        model.addAttribute("recipe", recipe);
        return "viewRecipe";
    }


}
