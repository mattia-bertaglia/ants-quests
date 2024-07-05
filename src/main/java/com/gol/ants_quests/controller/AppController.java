package com.gol.ants_quests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/homeAdmin")
    public String homeAdmin() {
        return "homeAdmin.html";
    }

}
