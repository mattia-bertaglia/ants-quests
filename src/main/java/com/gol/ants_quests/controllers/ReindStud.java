package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReindStud {

    @GetMapping("/studenti")
    public String studenti() {
        return "studenti.html";
    }

    @GetMapping("/classi")
    public String classi() {
        return "classi.html";
    }

    @GetMapping("/calendario")
    public String calendario() {
        return "calendario.html";
    }

}
