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
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/homeStud")
@RequiredArgsConstructor
public class HomeStudentiController {

    private final AuthService authService;
    private final HomeStudentiService homeStudSrv;
    private final Ruolo ruoloStud = Ruolo.studente;
    private final Ruolo ruoloGuest = Ruolo.guest;

    private final ErrorService errorService;

    @GetMapping("/")
    public String homepageStudente(HttpSession session, Model model) {
        // DONE: authSrv.checkAuthentication(session & permission)

        // inserire una lista di questionari per lo studente nella session
        log.info("Start Open Home Page Studente ...");

        if (authService.isLogged(session)
                && (authService.hasPermission(session, ruoloStud) || authService.hasPermission(session, ruoloStud))) {
            User user = (User) session.getAttribute("user");
            homeStudSrv.openHomeStud(model, user.getStudente().getIdStudente());
            log.info("End Open Home Page Studente.");
            return "homeStud.html";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            log.warn("Home Studente - Sessione Scaduta");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruoloStud) || !authService.hasPermission(session, ruoloGuest)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            log.warn("Home Studente - Non Autorizzato");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            log.warn("Home Studente - Errore sconosciuto");
            return "redirect:/";
        }
    }

    @GetMapping("/profilo")
    public String openProfilo(HttpSession session, Model model) {
        // DONE: authSrv.checkAuthentication(session & permission)
        log.info("Open Pagina Profilo Studente.");
        if (authService.isLogged(session)
                && (authService.hasPermission(session, ruoloStud) || authService.hasPermission(session, ruoloStud))) {
            return "profiloStud.html";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            log.warn("Profilo Studente - Sessione Scaduta");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruoloStud) || !authService.hasPermission(session, ruoloGuest)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            log.warn("Profilo Studente - Non Autorizzato");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            log.warn("Profilo Studente - Errore sconosciuto");
            return "redirect:/";
        }
    }

    // DONE: modifica Profilo Studente

    @PostMapping("/modificaProfilo")
    public String modificaProfilo(HttpSession session, @RequestParam HashMap<String, String> params, Model model) {
        // DONE: authSrv.checkAuthentication(session & permission)
        if (authService.isLogged(session)
                && (authService.hasPermission(session, ruoloStud) || authService.hasPermission(session, ruoloStud))) {
            homeStudSrv.modificaProfilo(session, params, model);
            // Controlla se ci sono errori
            if (model.containsAttribute("errorMessage")) {
                return "profiloStud"; // Ritorna alla pagina di modifica se ci sono errori
            } else {
                // Reindirizza alla pagina del profilo studente se tutto è andato bene
                return "redirect:/homeStud/profilo";
            }
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            log.warn("Profilo Studente - Sessione Scaduta");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruoloStud) || !authService.hasPermission(session, ruoloGuest)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            log.warn("Profilo Studente - Non Autorizzato");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            log.warn("Profilo Studente - Errore sconosciuto");
            return "redirect:/";
        }

    }

    @GetMapping("/doQuestionario")
    public String doQuestionario(HttpSession session, Model model, @RequestParam("quest-select") Long selectedValue) {
        // DONE: authSrv.checkAuthentication(session & permission)
        if (authService.isLogged(session)
                && (authService.hasPermission(session, ruoloStud) || authService.hasPermission(session, ruoloStud))) {
            log.info("Start Questionario=" + selectedValue + " ...");

            homeStudSrv.doQuestionario(model, selectedValue);

            log.info("End Questionario=" + selectedValue);
            return "doQuest.html";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            log.warn("Profilo Studente - Sessione Scaduta");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruoloStud) || !authService.hasPermission(session, ruoloGuest)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            log.warn("Profilo Studente - Non Autorizzato");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            log.warn("Profilo Studente - Errore sconosciuto");
            return "redirect:/";
        }

    }

    @PostMapping("/submit-quest")
    public String submitQuest(HttpSession session, @RequestParam HashMap<String, String> params) {
        // DONE: authSrv.checkAuthentication(session & permission)
        if (authService.isLogged(session)
                && (authService.hasPermission(session, ruoloStud) || authService.hasPermission(session, ruoloStud))) {
            log.info("Start Submit Questionario ...");

            User user = (User) session.getAttribute("user");
            homeStudSrv.elaborazioneQuestionario(user, params);

            log.info("End Submit Questionario.");
            return "redirect:/homeStud/";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            log.warn("Profilo Studente - Sessione Scaduta");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruoloStud) || !authService.hasPermission(session, ruoloGuest)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            log.warn("Profilo Studente - Non Autorizzato");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            log.warn("Profilo Studente - Errore sconosciuto");
            return "redirect:/";
        }
    }

}
