package com.gol.ants_quests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.services.ErrorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppController {

    @Autowired
    private ErrorService errorServ;

    @GetMapping("/")
    public String openIndex(Model model, HttpSession session, @RequestParam(required = false) String code) {
        if (code != null) {
            errorServ.getToast(session, code);
        }
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
