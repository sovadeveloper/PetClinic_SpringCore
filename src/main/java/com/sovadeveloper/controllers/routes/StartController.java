package com.sovadeveloper.controllers.routes;

import com.sovadeveloper.entities.ClientEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/")
    public String hello(@AuthenticationPrincipal ClientEntity currentUser, Model model){
        model.addAttribute("currentUser", currentUser);
        return "index";
    }
}
