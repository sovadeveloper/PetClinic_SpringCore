package com.sovadeveloper.controllers.routes;

import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/personalPet/{id}")
    public String personalPetProfile(@AuthenticationPrincipal ClientEntity currentUser,
                                     @PathVariable Long id, Model model) throws Exception {
        model.addAttribute("pet", petService.getById(id));
        model.addAttribute("client", currentUser);
        model.addAttribute("currentUser", currentUser);
        return "personalPetProfile";
    }

    @GetMapping("/pet/{id}")
    public String petProfile(@AuthenticationPrincipal ClientEntity currentUser,
                             @PathVariable Long id, Model model) throws Exception {
        model.addAttribute("pet", petService.getById(id));
        model.addAttribute("client", petService.getById(id).getClient());
        model.addAttribute("currentUser", currentUser);
        return "petProfile";
    }
}
