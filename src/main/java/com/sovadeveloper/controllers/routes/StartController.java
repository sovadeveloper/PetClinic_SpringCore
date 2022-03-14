package com.sovadeveloper.controllers.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/")
    public String hello(){
        return "index";
    }
}
