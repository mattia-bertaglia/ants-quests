
package com.gol.ants_quests.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.business.GesCorsiService;

import lombok.RequiredArgsConstructor;

// Ross & Nass
@Controller
@RequestMapping("/ges_corsi")
@RequiredArgsConstructor
public class GesCorsiController {

    private final GesCorsiService corsoService;

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("corsi", corsoService.findAll());
        return "gesCorsiAdmin.html";
    }

    /*
     * ESEMPIO DA PROVARE
     * SBLOCCARE GESCORSISERVICES.JAVA
     * 
     * @PostMapping("/salvacorso")
     * public String salvaCorso(@RequestParam HashMap<String, String> params) {
     * 
     * /*
     * params = nome, data inizio e data fine
     * check corso gia presente, se si = errore, ritorna in gesCorsiAdmin.html
     * se corso nuovo compilazione dati Corso, invio dati, save a DB,
     * con user che torna da save lo metti in sessione
     * redirect:/ges_corsi/
     * 
     * 
     * Corso newCorso = corsoService.registerCorso(params, model);
     * if(newCorso !=null){
     * corsoService.saveCorso(params);
     * return "/ges_corsi/";
     * }
     * }
     * SOTTO: Se esiste un ID di corso nei parametri, imposta l'ID del corso
     * VALUTARE SE SI POSSONO UNIRE E PROVARLI ENTRAMBI
     */

    @PostMapping("/savecorso")
    public String saveCorso(@RequestParam HashMap<String, String> params) {
        corsoService.saveCorso(params);
        return "redirect:/ges_corsi/";
    }
}
