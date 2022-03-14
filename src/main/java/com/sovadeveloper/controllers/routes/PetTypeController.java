package com.sovadeveloper.controllers.routes;

import com.sovadeveloper.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PetTypeController {
    private final PetTypeService petTypeService;

    @Autowired
    public PetTypeController(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @GetMapping("/petType")
    public String petTypeList(Model model) throws Exception {
        model.addAttribute("petTypes", petTypeService.getAll());
        return "petTypeList";
    }

    @GetMapping("/petType/{id}")
    public String petTypeProfile(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("petType", petTypeService.getById(id));
        return "petTypeProfile";
    }
}
