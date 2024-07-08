package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReindQuest {

    @GetMapping("/ges-questionari")
    public String gestioneQuestionari() {
        return "ges-questionari.html";
    }

    @GetMapping("/dettaglio-test")
    public String dettagliotest() {
        return "/dettaglio-test.html";
    }

    @GetMapping("/esi-questionari")
    public String inizio() {
        return "esi-questionari.html";
    }

    @GetMapping("/ciao")
    public String ciao() {
        return "ciao.html";
    }
}
