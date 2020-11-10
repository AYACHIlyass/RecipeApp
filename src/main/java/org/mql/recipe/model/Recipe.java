package org.mql.recipe.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cockTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @OneToOne(cascade = CascadeType.ALL)
    private Note note;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;
    @ManyToMany
    @JoinTable(name = "RECIPE_CATEGORY",
            joinColumns = @JoinColumn(name = "Recipe_ID"),
            inverseJoinColumns = @JoinColumn(name = "Category_ID")
    )
    private Set<Category> categories;
    @Lob
    private Byte[] image;

    public Recipe() {
//        System.err.println("Recipe Constructor by the thread : " + Thread.currentThread());
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

    public Integer getCockTime() {
        return cockTime;
    }

    public void setCockTime(Integer cockTime) {
        this.cockTime = cockTime;
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
                ", cockTime=" + cockTime +
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
