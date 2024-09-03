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

import com.gol.ants_quests.business.AuthService;
import com.gol.ants_quests.business.GestQuestService;
import com.gol.ants_quests.hibernate.entities.Quest;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class QuestController {

    private final GestQuestService gestSrv;
    private final AuthService authService;

    // done
    @GetMapping("/esiti")
    public String esiti(HttpSession session,@RequestParam HashMap<String, String> params, Model model) {
        
        //if(authService.isLogged(session)){
        if(true){
            gestSrv.openEsiti(model);
            return "esitiQuestionari.html";
        }else{
            return "redirect:/";
        }

    }

    // done
    @GetMapping("/lista")
    public String lista(HttpSession session,Model model) {

        //if(authService.isLogged(session)){
        if(true){
            gestSrv.openLista(model);
            return "listaQuestionari.html";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/gestione")
    public String gestioneQuest(HttpSession session,@RequestParam("id_quest") String idQuest, Model model) {

        //if(authService.isLogged(session)){
        if(true){
            gestSrv.openGestione(idQuest, model);
            return "gestioneQuestionario.html";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/gestione")
    public String gestioneQuest(){
        return "redirect:/";
    }

    @PostMapping("/gestionedomande")
    @ResponseBody
    public String gestioneDomande(@RequestBody Quest jsonQuest) {
        return gestSrv.gestioneDomande(jsonQuest);
    }


    @GetMapping("/gestionedomande")
    public String gestioneDomande() {
        return "redirect:/";
    }

    @PostMapping("/savetest")
    @ResponseBody
    public String saveTest(@RequestParam HashMap<String, String> params) {
        return gestSrv.saveTest(params);
    }

    @GetMapping("/savetest")
    public String saveTest(){
        return "redirect:/";
    }

    @PostMapping("/attivo")
    public String attivaDisattivaQuest(@RequestParam("idquest") Long idQuest) {
        gestSrv.attivaDisattivaQuest(idQuest);
        return "redirect:/quest/lista";
    }

    @GetMapping("/attivo")
    public String attivaDisattivaQuest() {
        return "redirect:/";
    }

}
