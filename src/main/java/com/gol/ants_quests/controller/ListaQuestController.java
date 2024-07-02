package com.gol.ants_quests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListaQuestController {

    @GetMapping("/")
    public String homePage() {
        return "esitiQuestionari.html";
    }
}
