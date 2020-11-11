package org.mql.recipe.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "description is required.")
    @Length(min = 10 , max = 600 , message = "enter a valid description [min=10,max=600]")
    @Lob
    private String description;
    @Min(value = 1, message = "enter an amount greater than 0.")
    @NotNull(message = "amount is required.")
    private BigDecimal amount;
    @ManyToOne()
    private Recipe recipe;
    @OneToOne
    private UnitOfMeasure unit;

    public Ingredient() {
    }

    public Ingredient(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitOfMeasure unit) {
        this.unit = unit;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", recipe=" + recipe +
                ", unit=" + unit +
                '}';
    }
}
