package com.gol.ants_quests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gol.ants_quests.services.ShowQuestServices;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ListaQuestController {

    private final ShowQuestServices showHibSrv;

    @GetMapping("/esiti")
    public String esitiQuestionari(Model model) {

        showHibSrv.findAll(model);
       // model.addAttribute("listaQuestionari", lista);
        return "esitiQuestionari.html";
    }
}
