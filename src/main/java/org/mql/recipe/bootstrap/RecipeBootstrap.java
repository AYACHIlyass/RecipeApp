package org.mql.recipe.bootstrap;


import org.mql.recipe.model.*;
import org.mql.recipe.repository.CategoryRepository;
import org.mql.recipe.repository.RecipeRepository;
import org.mql.recipe.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;


@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    public static final String URL = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.bbcgoodfood.com%2Frecipes%2Fcollection%2Feasy-recipes&psig=AOvVaw0sgc_pfpb02ZZk_NR-Ibpx&ust=1605135677649000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPDMvceK-ewCFQAAAAAdAAAAABAD";
    private Set<Category> categories;
    private List<Ingredient> ingredients;
    private Note note;


    private UnitOfMeasureRepository measureRepository;
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    private Recipe recipe;

    @Autowired
    public RecipeBootstrap(UnitOfMeasureRepository measureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.measureRepository = measureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        for (int i = 0; i < 3; i++) {
            recipe = new Recipe();
            recipe.setCookTime(Integer.valueOf(10));
            recipe.setPrepTime(Integer.valueOf(10));
            recipe.setDescription("this is a description");
            recipe.setDifficulty(Difficulty.EASY);
            recipe.setDirections("this are Directions");
            recipe.setServings((Integer.valueOf(10)));
            recipe.setSource("this is a source");
            recipe.setUrl(URL);
            recipe.setImage(null);
            note = new Note();
            note.setRecipeDescription("Note Description");
            note.setRecipe(recipe);
            recipe.setNote(note);
            addIngredients();
            recipe.setIngredients(ingredients);
            addCategories();
            recipe.setCategories(categories);
            recipeRepository.save(recipe);
        }

    }

    private void addCategories() {
        categories = new HashSet<>();
        List<Category> list = categoryRepository.findAll();
        Set<Recipe> set = new HashSet<>();
        set.add(recipe);
        list.forEach(category -> {
            category.setRecipes(set);
            categories.add(category);
        });
    }

    private void addIngredients() {
        ingredients = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setAmount(new BigDecimal(10));
            ingredient.setDescription("this is a Description");
            Optional<UnitOfMeasure> unit = measureRepository.findById(Long.valueOf(i + 1));
            if (unit.isPresent()) {
                ingredient.setUnit(unit.get());
            } else {
                ingredient.setUnit(null);
            }
            ingredient.setRecipe(recipe);
            ingredients.add(ingredient);
        }

    }

}
