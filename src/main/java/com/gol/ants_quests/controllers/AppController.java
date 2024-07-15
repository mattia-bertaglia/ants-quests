package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.business.AuthService;
import com.gol.ants_quests.business.ErrorService;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {

    private final ErrorService errorServ;
    private final AuthService authService;
    private final Ruolo studente = Ruolo.studente;
    private final Ruolo guest = Ruolo.guest;

    @GetMapping("/")
    public String openIndex(Model model, HttpSession session, @RequestParam(required = false) String code) {
        if (code != null && !code.isEmpty()) {
            String errorMessage = errorServ.getErrorMessage(code); // Ottieni il messaggio di errore dal
                                                                   // service

            if (errorMessage != null) {
                session.setAttribute("errorMessage", errorMessage); // Imposta il messaggio di errore nella sessione
            }
        }

        String message = (String) session.getAttribute("errorMessage");

        if (message != null) {
            // Aggiungi gli attributi al modello
            model.addAttribute("message", message);

            // Rimuovi gli attributi dalla sessione dopo averli aggiunti al modello
            session.removeAttribute("errorMessage");
        }

        return "index.html";
    }

    @GetMapping("/homeStud")
    public String homeStud(HttpSession session, Model model) {
        // Check Autenticazione
        if (authService.isLogged(session)
                && (authService.hasPermission(session, guest)
                        || authService.hasPermission(session, studente))) {
            return "homeStud.html";
        } else if (!authService.isLogged(session)) {
            errorServ.addErrorMessageToSession(session, "notLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, guest) || !authService.hasPermission(session, studente)) {
            errorServ.addErrorMessageToSession(session, "noPermission");
            return "redirect:/";
        } else {
            errorServ.addErrorMessageToSession(session, "unknownError");
            return "redirect:/";
        }

    }
}
