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

    ErrorService errorServ;
    private final AuthService authService;
    private final Ruolo studente = Ruolo.studente;
    private final Ruolo guest = Ruolo.guest;

    @GetMapping("/")
    public String openIndex(Model model, HttpSession session, @RequestParam(required = false) String code) {
        /*
         * if (code != null && !code.isEmpty()) {
         * errorServ.getToast(session, code);
         * }
         * 
         * // Aggiungi gli attributi del toast al modello se presenti
         * String toastTitle = (String) session.getAttribute("toastTitle");
         * String toastMessage = (String) session.getAttribute("toastMessage");
         * String toastColor = (String) session.getAttribute("toastColor");
         * 
         * if (toastTitle != null && toastMessage != null && toastColor != null) {
         * model.addAttribute("toastTitle", toastTitle);
         * model.addAttribute("toastMessage", toastMessage);
         * model.addAttribute("toastColor", toastColor);
         * 
         * // Rimuovi gli attributi del toast dalla sessione dopo averli aggiunti al
         * // modello
         * session.removeAttribute("toastTitle");
         * session.removeAttribute("toastMessage");
         * session.removeAttribute("toastColor");
         * }
         */
        return "index.html";
    }

    @GetMapping("/homeStud")
    public String homeStud(Model model) {
        // Check Autenticazione
        return "homeStud.html";
    }
}
