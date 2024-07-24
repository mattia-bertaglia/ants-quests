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
        log.info("Start Open Home Page Studente ...");

        if (authSrv.isLogged(session)) {
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

    @GetMapping("/doQuestionario")
    public String doQuestionario(HttpSession session, Model model, @RequestParam("quest-select") Long selectedValue) {
        log.info("Start Questionario=" + selectedValue + " ...");

        if (authSrv.isLogged(session)) {
            homeStudSrv.doQuestionario(model, selectedValue);
            log.info("End Questionario=" + selectedValue);
            return "doQuest.html";
        } else {
            log.warn("Do Questionario - Non Autorizzato o Sessione Scaduta");
            errorSrv.addErrorMessageToSession(session, "notAuthOrOutSession");
            return "redirect:/";
        }
    }

    @PostMapping("/submit-quest")
    public String submitQuest(HttpSession session, @RequestParam HashMap<String, String> params) {
        log.info("Start Submit Questionario ...");

        if (authSrv.isLogged(session)) {
            User user = (User) session.getAttribute("user");
            homeStudSrv.elaborazioneQuestionario(user, params);
            log.info("End Submit Questionario.");
            return "redirect:/homeStud/";
        } else {
            log.warn("Submit Questionario - Non Autorizzato o Sessione Scaduta");
            errorSrv.addErrorMessageToSession(session, "notAuthOrOutSession");
            return "redirect:/";
        }
    }

    @GetMapping("/profilo")
    public String openProfilo(HttpSession session, Model model) {
        log.info("Open Pagina Profilo Studente.");

        if (authSrv.isLogged(session)) {
            return "profiloStud.html";
        } else {
            log.warn("View Profilo - Non Autorizzato o Sessione Scaduta");
            errorSrv.addErrorMessageToSession(session, "notAuthOrOutSession");
            return "redirect:/";
        }
    }

    @PostMapping("/modificaProfilo")
    public String modificaProfilo(HttpSession session, @RequestParam HashMap<String, String> params, Model model) {
        log.info("Dati ricevuti dal form: " + params.toString());

        if (authSrv.isLogged(session)) {
            authSrv.updateUser(session, params, model);
            return "redirect:/homeStud/profilo";
        } else {
            errorSrv.addErrorMessageToModel(model, "modificaProfiloError");
            return "redirect:/login";
        }
    }
}
