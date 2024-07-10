package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homeAdmin")
public class AdminController {

    @GetMapping("/")
    public String homeAdmin(Model model) {
        // Check Autenticazione
        return "homeAdmin.html";
    }

}
