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
import com.gol.ants_quests.business.GesCorsiService;
import com.gol.ants_quests.business.GesStudentiService;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ges_studenti")
@RequiredArgsConstructor
public class GesStudentiController {

    private final GesStudentiService studSrv;
    private final GesCorsiService corsiSrv;
    private final AuthService authService;
    private final ErrorService errorService;
    private final Ruolo ruolo = Ruolo.admin;

    @GetMapping("/")
    public String getStud(HttpSession session, Model model) {
        model.addAttribute("corsi", corsiSrv.findAll());
        model.addAttribute("studenti", studSrv.findAllStudentiDTO());

        // Check Autenticazione
        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            return "gesStudentiAdmin.html";
        } else if (!authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }
    }

    /*
     * @GetMapping("/")
     * public String getStud(Model model) {
     * model.addAttribute("corsi", corsiSrv.findAll());
     * model.addAttribute("studenti", studSrv.findAllStudentiDTO());
     * 
     * return "gesStudentiAdmin.html";
     * }
     */

    @PostMapping("/savestud")
    public String saveStud(@RequestParam HashMap<String, String> params, HttpSession session) {

        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            studSrv.saveStudenteFixed(params);
            return "redirect:/ges_studenti/";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }
    }

    @PostMapping("/updatestud")
    public String updateStud(@RequestParam HashMap<String, String> params, HttpSession session) {
        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            studSrv.updateStudenteFixed(params);
            return "redirect:/ges_studenti/";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }
    }

}
