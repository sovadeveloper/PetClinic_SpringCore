package com.sovadeveloper.controllers.routes;

import com.sovadeveloper.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pet/{id}")
    public String petProfile(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("pet", petService.getById(id));
        model.addAttribute("client", petService.getById(id).getClient());
        return "petProfile";
    }
}
