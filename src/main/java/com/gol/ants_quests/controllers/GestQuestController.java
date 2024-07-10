package com.gol.ants_quests.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/quest")
public class GestQuestController {

  



/* 
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
            @RequestBody List<Map<String, Object>> domande) throws Exception {
        gestQuestService.createQuestWithDomandeERisposte(titolo, categoriaId);
        return "redirect:/nuovaQuest";
    }

    @GetMapping("/categorie")
    public String categorie(Model model) {
        gestQuestService.findAll(model);
        return "newquest.html";
    }*/








}
