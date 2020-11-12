package org.mql.recipe.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mql.recipe.model.*;
import org.mql.recipe.repository.UnitOfMeasureRepository;
import org.mql.recipe.service.CategoryService;
import org.mql.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;
    private CategoryService categoryService;
    private UnitOfMeasureRepository unitOfMeasureRepository;


    @Autowired
    public RecipeController(RecipeService recipeService, CategoryService categoryService, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @ModelAttribute("categoriesList")
    public List<Category> populateCategories() {
        return categoryService.getAllCategories();
    }

    @ModelAttribute("unitsList")
    public List<UnitOfMeasure> populateUnits() {
        return unitOfMeasureRepository.findAll();
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
    public String addRecipe(@ModelAttribute("recipe") @Valid final Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/recipes/addRecipe";
        }
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
    public String updateRecipe(@Valid final Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/recipes/addRecipe";
        }
        recipeService.updateRecipe(recipe);
        return "redirect:/index";
    }

    @GetMapping(value = "/deleteRecipe")
    public String deleteRecipe(@RequestParam("id") Long id) {
        recipeService.delete(id);
        return "redirect:/index";
    }

    @GetMapping(value = "/{id}/image")
    public String changeImageForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "recipes/updateImage";
    }

    @PostMapping(value = "/{id}/updateImage")
    public String updateImage(@PathVariable("id") Long id, @RequestParam("imagefile") MultipartFile file) {
        recipeService.updateImage(id, file);
        return "redirect:/index";
    }

    @GetMapping(value = "/{id}/getImage")
    public void getImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        System.out.println("RecipeController.getImage");
        response.setContentType("image/jpeg"); // Or whatever format you wanna use
        byte[] array = recipeService.getImage(id);
        if (array == null) return;
        InputStream is = new ByteArrayInputStream(array);
        IOUtils.copy(is, response.getOutputStream());
    }


    @PostMapping(value = {"/save", "/update"}, params = {"addIngredient"})
    public String addIngredient(@ModelAttribute("recipe") final Recipe recipe) {
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
