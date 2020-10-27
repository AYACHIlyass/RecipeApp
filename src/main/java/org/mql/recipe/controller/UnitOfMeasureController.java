package org.mql.recipe.controller;

import org.mql.recipe.model.UnitOfMeasure;
import org.mql.recipe.service.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/recipe")
public class UnitOfMeasureController {
    private UnitOfMeasureService unitOfMeasureService;

    @Autowired
    public UnitOfMeasureController(UnitOfMeasureService unitOfMeasureService) {
        this.unitOfMeasureService = unitOfMeasureService;
    }

    public UnitOfMeasureController() {
    }

    @GetMapping("/units")
    public String units(Model model) {
        List<UnitOfMeasure> allUnits = unitOfMeasureService.getAllUnits();
        model.addAttribute("units" , allUnits);
        return "units/units";
    }
    @GetMapping("/unit")
    public String unitByUom( Model model, @RequestParam("uom") String uom) {
        UnitOfMeasure unit = unitOfMeasureService.findByUom(uom);
        model.addAttribute("unit" , unit);
        return "units/unit";
    }
}
