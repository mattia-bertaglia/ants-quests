package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gol.ants_quests.business.AuthService;
import com.gol.ants_quests.business.ErrorService;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/homeAdmin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;
    private final ErrorService errorService;
    private final Ruolo ruolo = Ruolo.admin;

    @GetMapping("/")
    public String homeAdmin(HttpSession session, Model model) {
        // Check Autenticazione
        if (authService.isLogged(session) && authService.hasPermission(session, ruolo)) {
            // Se chi sta cercando di accedere ha i permessi corretti ed è in sessione
            // Manda alla homeAdmin
            return "homeAdmin.html";
        } else if (!authService.isLogged(session)) {
            // Altrimenti manda alla pagina di login con un messaggio di errore
            // errorService.getMessage(model, "userNotLogged");
            return "redirect:/";
        } else if (!authService.hasPermission(session, ruolo)) {
            // errorService.getMessage(model, "noPermission");
            return "redirect:/";
        } else {
            // errorService.getMessage(model, "genericError");
            return "redirect:/";
        }
    }

}
