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
import com.gol.ants_quests.hibernate.entities.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ErrorService errorService;

    @PostMapping("/login")
    public String login(@RequestParam HashMap<String, String> params, HttpSession session, Model model) {
        String loginResult = authService.logInUser(params, session, model);
        if (loginResult.equals("redirect:/homeStud") || loginResult.equals("redirect:/homeAdmin")) {
            return loginResult;
        } else {
            // Gestione dell'errore
            if (authService.findByUsernameEmail(params.get("usernameEmail")).isEmpty()) {
                errorService.getToast(session, "erroreLog"); // Utente non trovato
            } else {
                errorService.getToast(session, "passwordMismatch"); // Password non valida
            }
            return "redirect:/"; // Rimani sulla pagina di login con messaggio di errore
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestParam HashMap<String, String> params, HttpSession session, Model model) {

        /*
         * params = nome, cognome e email
         * check email gia presente, se si = errore, rimani in index.html
         * se email nuova, vai a firstTime.html
         * compilazione dati User/Studente, invio dati, save a DB,
         * con user che torna da save lo metti in sessione
         * redirect:/homeStud
         * 
         */

        User newUser = authService.registerUser(params, model);
        if (newUser != null) {
            authService.setupSession(session, newUser);
            return "redirect:/?status=signOK";
        } else {
            // Rimani sulla pagina di registrazione se ci sono errori
            return "/firstTime";
        }
    }

    @PostMapping("/firstTime")
    public String firstTime(Model model) {
        return "/auth/firstTime";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        return "redirect:/";
    }
}