package org.mql.recipe.service;

import org.mql.recipe.model.UnitOfMeasure;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface UnitOfMeasureService {
    List<UnitOfMeasure> getAllUnits();

    UnitOfMeasure findByUom(String uom);
}
