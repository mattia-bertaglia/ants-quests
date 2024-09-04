
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

import com.gol.ants_quests.business.AuthService;
import com.gol.ants_quests.business.ErrorService;
import com.gol.ants_quests.business.GesCorsiService;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ges_corsi")
@RequiredArgsConstructor
public class GesCorsiController {

    private final GesCorsiService corsoService;
    private final AuthService authService;
    private final ErrorService errorService;
    private final Ruolo ruolo = Ruolo.admin;

    @GetMapping("/")
    public String findAll(HttpSession session, Model model) {
        model.addAttribute("corsi", corsoService.findAll());
        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            return "gesCorsiAdmin.html";
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

    @PostMapping("/savecorso")
    public String saveCorso(@RequestParam HashMap<String, String> params, HttpSession session) {

        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            corsoService.saveCorso(params);
            return "redirect:/ges_corsi/";
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

    @PostMapping("/eliminaStudenteDalCorso")
    @ResponseBody
    public String eliminaStudenteDalCorso(@RequestParam HashMap<String, String> params, HttpSession session) {
        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            return corsoService.eliminaStudenteDalCorso(params);
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

    @PostMapping("/find-studs")
    @ResponseBody
    public List<Studente> cercaStudenti(@RequestParam HashMap<String, String> params, HttpSession session) {
        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            return corsoService.cercaStudenti(params);
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            errorService.addErrorMessageToSession(session, "notLogged");
            return null;
        } else if (!authService.hasPermission(session, ruolo)) {
            errorService.addErrorMessageToSession(session, "noPermission");
            return null;
        } else {
            errorService.addErrorMessageToSession(session, "unknownError");
            return null;
        }

    }

    @PostMapping("/aggiungiStudenteAlCorso")
    @ResponseBody
    public String aggiungiStudenteAlCorso(@RequestParam HashMap<String, String> params, HttpSession session) {
        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            return corsoService.aggiungiStudenteAlCorso(params);
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
