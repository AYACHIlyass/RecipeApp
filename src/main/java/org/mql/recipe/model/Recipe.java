package org.mql.recipe.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @NotNull(message = "description is required.")
    @Length(min = 10 , max = 600 , message = "enter a valid description [min=10,max=600]")
    private String description;
    @Min(value = 1, message = "enter a preparation time greater than 0.")
    @NotNull(message = "preparation time is required.")
    private Integer prepTime;
    @Min(value=1 ,message = "enter a cock time greater than 0.")
    @NotNull(message = "cock time is required.")
    private Integer cookTime;
    @Min(value= 1, message = "enter a cock time greater than 0.")
    @NotNull(message = "cock time is required.")
    private Integer servings;
    @NotNull(message = "source is required.")
    @Length(min = 10 , max = 600 , message = "enter a valid source [min=10,max=600]")
    private String source;
    @URL(message = "enter a valid url")
    private String url;
    @Lob
    @NotNull(message = "source is required.")
    @Length(min = 10 , max = 600 , message = "enter a valid source [min=10,max=600]")
    private String directions;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private Note note;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe",orphanRemoval = true)
    @Valid
    @Size(min = 1 ,message = "add at least one ingredient.")
    private List<Ingredient> ingredients;
    @ManyToMany
    @JoinTable(name = "RECIPE_CATEGORY",
            joinColumns = @JoinColumn(name = "Recipe_ID"),
            inverseJoinColumns = @JoinColumn(name = "Category_ID")
    )
    @Valid
    @Size(min = 1 , message = "choose at least one category")
    private Set<Category> categories;
    @Lob
    private Byte[] image;

    public Recipe() {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        if (categories == null) {
            categories = new HashSet<>();
        }
        if (note == null) {
            note = new Note();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cockTime) {
        this.cookTime = cockTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredient) {
        this.ingredients = ingredient;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", prepTime=" + prepTime +
                ", cockTime=" + cookTime +
                ", servings=" + servings +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", directions='" + directions + '\'' +
                ", difficulty=" + difficulty +
                ", note=" + note +
//                ", ingredients=" + ingredients +
                ", categories=" + categories +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
