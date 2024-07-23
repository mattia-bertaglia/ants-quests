package com.gol.ants_quests.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.business.AuthService;
import com.gol.ants_quests.business.ErrorService;
import com.gol.ants_quests.business.HomeStudentiService;
import com.gol.ants_quests.hibernate.entities.User;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/homeStud")
@RequiredArgsConstructor
public class HomeStudentiController {

    private final AuthService authSrv;
    private final HomeStudentiService homeStudSrv;

    private final ErrorService errorSrv;

    @GetMapping("/")
    public String homepageStudente(HttpSession session, Model model) {
        // inserire una lista di questionari per lo studente nella session
        log.info("Start Open Home Page Studente ...");

        // TODO: authSrv.checkAuthentication(session)
        if (session.getAttribute("usrlog") != null) {
            User user = (User) session.getAttribute("user");
            homeStudSrv.openHomeStud(model, user.getStudente().getIdStudente());

        } else {
            log.warn("Home Studente - Non Autorizzato o Sessione Scaduta");
            errorSrv.addErrorMessageToSession(session, "notAuthOrOutSession");
            return "redirect:/";
        }

        log.info("End Open Home Page Studente.");
        return "homeStud.html";
    }

    @GetMapping("/profilo")
    public String openProfilo(HttpSession session, Model model) {
        // TODO: authSrv.checkAuthentication(session)
        log.info("Open Pagina Profilo Studente.");
        // TODO: Compilazione Profilo Utente, aggiungere form per modifica dati.
        return "profiloStud.html";
    }

    // TODO: modifica Profilo Studente

    @PostMapping("/modificaProfilo")
    public String modificaProfilo(HttpSession session, @RequestParam HashMap<String, String> params, Model model) {
        homeStudSrv.modificaProfilo(session, params, model);

        // Controlla se ci sono errori
        if (model.containsAttribute("errorMessage")) {
            return "modificaProfilo"; // Ritorna alla pagina di modifica se ci sono errori
        }

        // Reindirizza alla pagina del profilo studente se tutto è andato bene
        return "redirect:/homeStud/profilo";
    }

    @GetMapping("/doQuestionario")
    public String doQuestionario(HttpSession session, Model model, @RequestParam("quest-select") Long selectedValue) {
        // TODO: authSrv.checkAuthentication(session)
        log.info("Start Questionario=" + selectedValue + " ...");

        homeStudSrv.doQuestionario(model, selectedValue);

        log.info("End Questionario=" + selectedValue);
        return "doQuest.html";

    }

    @PostMapping("/submit-quest")
    public String submitQuest(HttpSession session, @RequestParam HashMap<String, String> params) {
        // TODO: authSrv.checkAuthentication(session)
        log.info("Start Submit Questionario ...");

        User user = (User) session.getAttribute("user");
        homeStudSrv.elaborazioneQuestionario(user, params);

        log.info("End Submit Questionario.");
        return "redirect:/homeStud/";
    }

}
