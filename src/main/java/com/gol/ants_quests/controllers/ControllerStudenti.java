package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/studenti")
public class ControllerStudenti {

    @GetMapping("/")
    public String homepageStudente() {
        return "studenti-home.html";
    }

    @GetMapping("/doQuestionario")
    public String doQuestionario() {
        return "exe-test.html";
    }

}
