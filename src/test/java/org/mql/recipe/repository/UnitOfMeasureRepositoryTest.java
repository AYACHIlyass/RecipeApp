package org.mql.recipe.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mql.recipe.model.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository repository ;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUom() {
        Optional<UnitOfMeasure> teaspoon = repository.findByUom("Teaspoon");
        assertEquals("Teaspoon" ,teaspoon.get().getUom());
    }
    @Test
    void findByUomCup() {
        Optional<UnitOfMeasure> teaspoon = repository.findByUom("Cup");
        assertEquals("Cup" ,teaspoon.get().getUom());
    }
}