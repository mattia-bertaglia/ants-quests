
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

    @PostMapping("/savecorso")
    public String saveCorso(@RequestParam HashMap<String, String> params) {
        corsoService.saveCorso(params);
        return "redirect:/ges_corsi/";
    }

    @PostMapping("/findStudentiByTelefono")
    public String findStudentiByTelefono(Model model) {
        studSrv.findStudentiByTelefono();
        return "redirect:/ges_corsi/";
    }

    /*
     * @PostMapping("/aggiungiStudenteCorso")
     * public String aggiungiStudenteCorso(@RequestParam HashMap<String, String>
     * params) {
     * studSrv.aggiungiStudenteCorso(params);
     * return "redirect:/ges_corsi/";
     * 
     * // studente già presente in db, se c'è aggiunge , altrimenti dice chenon
     * esiste
     * }
     */
}
