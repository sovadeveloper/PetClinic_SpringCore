package com.sovadeveloper.controllers.routes;

import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
    private final ClientService clientService;

    @Autowired
    public SecurityController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(ClientEntity clientEntity) throws Exception {
        clientService.create(clientEntity);
        return "redirect:/login";
    }
}
