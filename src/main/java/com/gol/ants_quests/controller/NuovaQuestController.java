package com.gol.ants_quests.controller;


import com.gol.ants_quests.services.GestQuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;


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
    public String inserisciQuest(
            @RequestParam String titolo,
            @RequestParam String categoriaId,
            @RequestBody List<Map<String, Object>> domande) {
        gestQuestService.createQuestWithDomandeERisposte(titolo, categoriaId, domande);
        return "redirect:/nuovaQuest";
    }

    @GetMapping("/categorie")
    public String categorie(Model model) {
        gestQuestService.findAll(model);
        return "newquest.html";
    }
}
