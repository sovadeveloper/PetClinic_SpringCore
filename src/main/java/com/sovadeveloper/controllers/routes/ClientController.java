package com.sovadeveloper.controllers.routes;

import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.services.ClientService;
import com.sovadeveloper.services.PetService;
import com.sovadeveloper.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@CrossOrigin
public class ClientController {
    private final ClientService clientService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    @Autowired
    public ClientController(ClientService clientService, PetService petService, PetTypeService petTypeService) {
        this.clientService = clientService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @GetMapping("/personalAccount/{id}")
    public String personalAccount(@AuthenticationPrincipal ClientEntity currentUser, Model model) throws Exception {
        model.addAttribute("client", clientService.getById(currentUser.getId()));
        model.addAttribute("pets", petService.getAllByClient(clientService.getById(currentUser.getId())));
        model.addAttribute("petTypes", petTypeService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "personalAccount";
    }

    @GetMapping("/client")
    public String clientList(@AuthenticationPrincipal ClientEntity currentUser, Model model) throws Exception {
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "clientList";
    }

    @GetMapping("/client/{id}")
    public String clientProfile(@AuthenticationPrincipal ClientEntity currentUser,
                                @PathVariable Long id, Model model) throws Exception {
        model.addAttribute("client", clientService.getById(id));
        model.addAttribute("pets", petService.getAllByClient(clientService.getById(id)));
        model.addAttribute("petTypes", petTypeService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "clientProfile";
    }
}
