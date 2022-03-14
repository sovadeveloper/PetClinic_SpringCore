package com.sovadeveloper.controllers.routes;

import com.sovadeveloper.services.ClientService;
import com.sovadeveloper.services.PetService;
import com.sovadeveloper.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/client")
    public String clientList(Model model) throws Exception {
        model.addAttribute("clients", clientService.getAll());
        return "clientList";
    }

    @GetMapping("/client/{id}")
    public String clientProfile(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("client", clientService.getById(id));
        model.addAttribute("pets", petService.getAllByClient(clientService.getById(id)));
        model.addAttribute("petTypes", petTypeService.getAll());
        return "clientProfile";
    }
}
