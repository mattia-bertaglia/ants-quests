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
        if (authService.userExists(params.get("usernameEmail"))) {
            errorService.getToast(model, "usernameExists"); // Username già esistente
            return "redirect:/"; // Rimani sulla pagina di registrazione con messaggio di errore
        } else {
            model.addAttribute("nome", params.get("nome"));
            model.addAttribute("cognome", params.get("cognome"));
            model.addAttribute("usernameEmail", params.get("usernameEmail"));
            return "firstTime";
        }
    }

    @PostMapping("/registrazione")
    public String registrazione(HttpSession session, @RequestParam HashMap<String, String> params, Model model) {
        authService.registerUser(session, params, model);
        return "redirect:/homeStud";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        return "redirect:/";
    }
}
