package com.gol.ants_quests.controllerStudentiCorsi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.gol.ants_quests.hibernateStudentiCorsi.services.CorsiHibService;
import com.gol.ants_quests.hibernateStudentiCorsi.services.StudentiHibService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentiController {

    private final StudentiHibService studentiHibService;
    private final CorsiHibService corsiHibService;

    @PostMapping("/studenti")
    public String studenti(Model model) {
        model.addAttribute("listaStudenti", studentiHibService.findAll());
        return "studenti.html";
    }

    @PostMapping("/corsi")
    public String corsi(Model model) {
        model.addAttribute("listaCorsi", corsiHibService.findAll());
        return "corsi.html";
    }

}
