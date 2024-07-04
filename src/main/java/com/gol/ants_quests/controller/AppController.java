package com.gol.ants_quests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppController {


    @GetMapping("/")
    public String openIndex(Model model, HttpSession session, @RequestParam(required = false) String code) {
        return "index.html";
    }

    @GetMapping("/homeStudente")
    public String homeStud(Model model) {
        return "homeStud.html";
    }

    @GetMapping("/homeAdmin")
    public String homeAdmin(Model model) {
        return "homeAdmin.html";
    }
}
