package org.mql.recipe.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mql.recipe.model.Recipe;
import org.mql.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Override
    public boolean updateImage(Long id, MultipartFile file) {
        Recipe recipe = findById(id);
        try {
            recipe.setImage(new Byte[file.getBytes().length]);
            int i = 0;
            for (byte fileByte : file.getBytes()) {
                recipe.getImage()[i++] = fileByte;
            }
            Recipe updatedRecipe = updateRecipe(recipe);
            System.out.println("Image : " + updatedRecipe.getImage().length);
            System.out.println("file : " + file.getBytes().length);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public byte[] getImage(Long id) {
        Recipe recipe = findById(id);
        Byte[] image = recipe.getImage();
        if (image == null) {
            return null;
        }

        byte[] array = new byte[recipe.getImage().length];
        int i = 0;
        for (Byte aByte : image) {
            array[i++] = aByte;
        }
        return array;
    }

}
