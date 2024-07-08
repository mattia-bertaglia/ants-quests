package com.gol.ants_quests.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.business.GestQuestService;
import com.gol.ants_quests.business.QuestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
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

}
