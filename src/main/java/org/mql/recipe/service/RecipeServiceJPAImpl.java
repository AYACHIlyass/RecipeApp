package org.mql.recipe.service;

import org.mql.recipe.model.Recipe;
import org.mql.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecipeServiceJPAImpl implements RecipeService {
    RecipeRepository repository;

    @Autowired
    public RecipeServiceJPAImpl(RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return repository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        return repository.save(recipe);
    }

    @Override
    public Recipe findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
