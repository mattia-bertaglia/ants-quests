package com.gol.ants_quests.controllerStudentiCorsi;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gol.ants_quests.hibernateStudentiCorsi.services.CorsiHibService;
import com.gol.ants_quests.hibernateStudentiCorsi.services.StudentiHibService;
import com.gol.ants_quests.services.CorsiServices;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentiController {

    private final StudentiHibService studentiHibService;
    private final CorsiServices corsiHibService;

    @PostMapping("/studenti")
    public String studenti(Model model) {
        model.addAttribute("listaStudenti", studentiHibService.findAll());
        return "studenti.html";
    }

    @GetMapping("/corsi")
    public String paginaCorsi(Model model) {
       corsiHibService.paginaCorsi(model);
        return "Corso.html";
    }

}
