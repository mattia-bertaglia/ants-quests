package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/studenti")
public class ControllerStudenti {

    @GetMapping("/")
    public String homepageStudente(HttpSession session) {
        // inserire una lista di questionari per lo studente nella session
        // lista questionari uguale per ogni studente
        // se assegnato ad una classe vede anche quelli del corso altrimenti no
       // session.setAttribute("studente", stuService.findByUserNameAndPassword());
        return "studenti-home.html";
    }

    @GetMapping("/doQuestionario")
    public String doQuestionario() {
        return "exe-test.html";
    }

}
