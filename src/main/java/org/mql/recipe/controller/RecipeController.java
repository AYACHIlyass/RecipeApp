package org.mql.recipe.controller;

import org.mql.recipe.model.Category;
import org.mql.recipe.model.Difficulty;
import org.mql.recipe.model.Ingredient;
import org.mql.recipe.model.Recipe;
import org.mql.recipe.service.CategoryService;
import org.mql.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;
    private CategoryService categoryService;


    @Autowired
    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categoriesList")
    public List<Category> populateCategories() {
        return categoryService.getAllCategories();
    }

    @ModelAttribute("difficulties")
    public List<Difficulty> populateDifficulties() {
        return Arrays.asList(Difficulty.ALL);
    }

    @GetMapping("/view")
    public String getRecipeById(@RequestParam("id") Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "/recipes/viewRecipe";
    }

    @GetMapping("/addRecipe")
    public String ForewordToAdditionPage(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "/recipes/addRecipe";
    }

    @PostMapping(value = "/save")
    public String addRecipe(@ModelAttribute("recipe") final Recipe recipe , Model model) {
        recipe.getIngredients().forEach(ingredient -> {
            ingredient.setRecipe(recipe);
        });
        recipeService.addRecipe(recipe);
        return "redirect:/index";
    }


    @GetMapping("/updateRecipe")
    public String ForewordToAdditionPage(@RequestParam("id") Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "/recipes/addRecipe";
    }

    @PostMapping(value = "/update")
    public String updateRecipe(final Recipe recipe) {
        recipe.getIngredients().forEach(ingredient -> {
            if (ingredient.getRecipe() == null)
                ingredient.setRecipe(recipe);
        });
        recipeService.updateRecipe(recipe);
        return "redirect:/index";
    }
    @GetMapping(value = "/deleteRecipe")
    public String deleteRecipe(@RequestParam("id") Long id) {
        recipeService.delete(id);
        return "redirect:/index";
    }

    @PostMapping(value = {"/save", "/update"}, params = {"addIngredient"})
    public String addIngredient(@ModelAttribute("recipe") final Recipe recipe, Model model) {
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);
        recipe.getIngredients().add(ingredient);
        return "/recipes/addRecipe";
    }

    @PostMapping(value = {"/save", "/update"}, params = {"removeIngredient"})
    public String removeIngredient(final Recipe recipe, HttpServletRequest request) {
        Long ingredientId = Long.valueOf(request.getParameter("removeIngredient"));
        recipe.getIngredients().remove(ingredientId.intValue());
        return "/recipes/addRecipe";
    }


}
