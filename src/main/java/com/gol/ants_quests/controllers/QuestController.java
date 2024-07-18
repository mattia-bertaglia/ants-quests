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
import com.gol.ants_quests.business.QuestService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class QuestController {

    private final QuestService showSrv;
    private final GestQuestService gestSrv;

    @GetMapping("/esiti")
    public String esiti(@RequestParam HashMap<String, String> params, Model model) {
        showSrv.findAllCategorie(model);
        showSrv.findAllEsitiQuest(model);
        showSrv.findAllQuestByCategoria(model);
        return "esitiQuestionari.html";
    }

    @GetMapping("/lista")
    public String lista(Model model) {
        showSrv.findAllQuestByCategoria(model);
        return "listaQuestionari.html";
    }

    @GetMapping("/gestione")
    public String gestioneQuest(@RequestParam("id_quest") String id_quest, Model model) {
        showSrv.findAllCategorie(model);
        try {
            Integer.parseInt(id_quest);
            gestSrv.findDomandeByID(id_quest,model);
        } catch (Exception error) {
            gestSrv.empyObject(model);   
        }
        return "gestioneQuestionario.html";
    }

    @PostMapping("/savetest")
    @ResponseBody
    public String savetest(){
        return gestSrv.saveTest();
    }


    /* 
    @PostMapping("/gestionedomande")
    @ResponseBody
    public String gestioneDomande(@RequestBody String jsonOggetto){
        return gestSrv.modificaDomanda(jsonOggetto);
    }*/
   
}
