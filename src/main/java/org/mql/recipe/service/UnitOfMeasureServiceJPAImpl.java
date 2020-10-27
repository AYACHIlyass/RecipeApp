package org.mql.recipe.service;

import org.mql.recipe.model.UnitOfMeasure;
import org.mql.recipe.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitOfMeasureServiceJPAImpl implements UnitOfMeasureService {

    private UnitOfMeasureRepository repository;

    @Autowired
    public UnitOfMeasureServiceJPAImpl(UnitOfMeasureRepository repository) {
        this.repository = repository;
    }

    public UnitOfMeasureServiceJPAImpl() {
    }


    public List<UnitOfMeasure> getAllUnits() {
        List<UnitOfMeasure> list = repository.findAll();
        return list;
    }


    public UnitOfMeasure findByUom(String uom) {
        Optional<UnitOfMeasure> unitOfMeasure = repository.findByUom(uom);
        return unitOfMeasure.get();
    }
}
