package org.mql.recipe.controller;

import org.mql.recipe.exception.BadRequest;
import org.mql.recipe.model.Recipe;
import org.mql.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recipe")
public class IndexController {
    private RecipeService service;

    @Autowired
    public IndexController(RecipeService service) {
        this.service = service;
    }

    @GetMapping({"index", "index.html", "/"})
    public String index(Model model) {
        List<Recipe> recipes = service.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}
