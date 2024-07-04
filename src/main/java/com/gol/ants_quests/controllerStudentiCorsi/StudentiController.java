package com.gol.ants_quests.controllerStudentiCorsi;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gol.ants_quests.hibernateStudentiCorsi.entities.Studente;

import com.gol.ants_quests.hibernateStudentiCorsi.services.StudentiHibService;
import com.gol.ants_quests.service.StudentiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentiController {

    /* richiamare logiche su studentiService */

    private final StudentiService studentiService;

    /*
     * private final CorsiHibService corsiHibService;
     */
    @GetMapping("/studenti")
    public String paginaStudenti(Model model) {
        studentiService.paginaStudenti(model);
        return "studenti.html";
    }

    /*
     * @GetMapping("/studenti")
     * public String getStudenti(Model model) {
     * // Aggiungi le variabili necessarie al modello
     * model.addAttribute("studenti", getStudentiList());
     * return "studenti";
     * }
     */

    @PostMapping("/new-stud")
    public String paginaStudenti(@RequestParam HashMap<String, String> params) {
        studentiService.save(params);
        return "redirect:/studenti";
    }

}
