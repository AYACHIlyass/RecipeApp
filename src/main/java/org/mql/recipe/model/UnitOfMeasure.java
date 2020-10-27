package org.mql.recipe.model;

import javax.persistence.*;

@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uom;


    public UnitOfMeasure() {
    }

    public UnitOfMeasure(Long id, String uom) {
        this.id = id;
        this.uom = uom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    @Override
    public String toString() {
        return "UnitOfMeasure{" +
                "id=" + id +
                ", uom='" + uom + '\'' +
                '}';
    }
}
