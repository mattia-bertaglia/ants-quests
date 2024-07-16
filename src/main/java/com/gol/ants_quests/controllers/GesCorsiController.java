
package com.gol.ants_quests.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gol.ants_quests.business.GesCorsiService;
import com.gol.ants_quests.hibernate.entities.Studente;

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

    @PostMapping("/eliminaStudenteDalCorso")
    @ResponseBody
    public String eliminaStudenteDalCorso(@RequestParam HashMap<String, String> params) {
        return corsoService.eliminaStudenteDalCorso(params);
    }

    @PostMapping("/find-studs")
    @ResponseBody
    public List<Studente> cercaStudenti(@RequestParam HashMap<String, String> params) {
        return corsoService.cercaStudenti(params);
    }

    // TODO: aggiungiStudenteAlCorso

}
