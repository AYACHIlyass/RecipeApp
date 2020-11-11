package org.mql.recipe.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    @NotNull(message = "description is required.")
    @Length(min = 10 , max = 600 , message = "enter a valid description [min=10,max=600]")
    private String recipeDescription;

    public Note(Long id, Recipe recipe, String recipeDescription) {
        this.id = id;
        this.recipe = recipe;
        this.recipeDescription = recipeDescription;
    }

    public Note() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String toString() {
        return "Note{" +
                ", recipeDescription='" + recipeDescription + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
