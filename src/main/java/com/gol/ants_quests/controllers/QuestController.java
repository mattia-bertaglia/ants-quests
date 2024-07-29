package com.gol.ants_quests.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gol.ants_quests.business.GestQuestService;
import com.gol.ants_quests.hibernate.entities.Quest;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class QuestController {

    private final GestQuestService gestSrv;

    // done
    @GetMapping("/esiti")
    public String esiti(@RequestParam HashMap<String, String> params, Model model) {
        // TODO: Add Check Auth
        gestSrv.openEsiti(model);
        return "esitiQuestionari.html";
    }

    // done
    @GetMapping("/lista")
    public String lista(Model model) {
        // TODO: Add Check Auth
        gestSrv.openLista(model);
        return "listaQuestionari.html";
    }

    @PostMapping("/gestione")
    public String gestioneQuest(@RequestParam("id_quest") String idQuest, Model model) {
        // TODO: Add Check Auth
        gestSrv.openGestione(idQuest, model);
        return "gestioneQuestionario.html";
    }

    @PostMapping("/gestionedomande")
    @ResponseBody
    public String gestioneDomande(@RequestBody Quest jsonQuest) {
        return gestSrv.gestioneDomande(jsonQuest);
    }

    @PostMapping("/savetest")
    @ResponseBody
    public String saveTest(@RequestParam HashMap<String, String> params) {
        return gestSrv.saveTest(params);
    }

    @PostMapping("/attivo")
    public String attivaDisattivaQuest(@RequestParam("idquest") Long idQuest) {
        gestSrv.attivaDisattivaQuest(idQuest);
        return "redirect:/quest/lista";
    }

}
