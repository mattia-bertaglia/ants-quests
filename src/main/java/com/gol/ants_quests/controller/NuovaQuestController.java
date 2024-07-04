package com.gol.ants_quests.controller;

import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.gol.ants_quests.services.GestQuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class NuovaQuestController {

    private final GestQuestService gestQuestService;

    @GetMapping("/nuovaQuest")
    public String showQuestForm(Model model) {
        gestQuestService.findAll(model);
        return "newquest.html";
    }


    @GetMapping("/inserisciQuest")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    public String inserisciQuest(
            @RequestParam String titolo,
            @RequestParam String categoriaId,
            @ModelAttribute("domande") List<DomandaQuest> domande) {
        for (DomandaQuest domanda : domande) {
            for (RispostaQuest risposta : domanda.getRisp()) {
                if (risposta.getCorretta() == null) {
                    risposta.setCorretta("false");
                }
            }
        }
        gestQuestService.createQuestWithDomandeERisposte(titolo, categoriaId, domande);
        return "redirect:/nuovaQuest";
    }

    @GetMapping("/categorie")
    public String categorie(Model model) {
        gestQuestService.findAll(model);
        return "newquest.html";
    }
}
