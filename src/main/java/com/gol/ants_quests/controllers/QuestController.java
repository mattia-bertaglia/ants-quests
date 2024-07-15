package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/* @RequiredArgsConstructor */
@RequestMapping("/quest")
public class QuestController {

    private final QuestService showSrv;

    @GetMapping("/esiti")
    public String esiti(@RequestParam HashMap<String, String> params, Model model) {

        if (params.containsKey("data_inizio") && params.containsKey("data_fine")) {
            if (!params.get("data_inizio").isEmpty() && !params.get("data_fine").isEmpty()) {
                showSrv.findByData(model, params.get("data_inizio"), params.get("data_fine"));

            } else
                showSrv.findAllEsitiQuest(model);

        } else {
            showSrv.findAllEsitiQuest(model);
        }

        return "esitiQuestionari.html";
    }

    @GetMapping("/lista")
    public String gestione(Model model) {

        showSrv.findAllQuestByCategoria(model);

        return "listaQuestionari.html";
    }

    /////////////////////////////////

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
        // gestQuestService.createQuestWithDomandeERisposte(titolo, categoriaId,
        // domande);
        return "redirect:/nuovaQuest";
    }

    @GetMapping("/categorie")
    public String categorie(Model model) {
        gestQuestService.findAll(model);
        return "newquest.html";
    }

}
