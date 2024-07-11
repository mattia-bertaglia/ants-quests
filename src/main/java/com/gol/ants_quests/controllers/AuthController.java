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
        boolean loginResult = authService.logInUser(params, session, model);

        if (loginResult) {
            return "redirect:" + params.get("root");
        } else {
            // errorService.getToast(session, params.get("status"));
            return "redirect:/";
        }
    }

    @GetMapping("/signup")
    public String signup(@RequestParam HashMap<String, String> params, HttpSession session, Model model) {
        String nome = "";
        String cognome = "";
        String usernameEmail = "";
        if (!authService.isLogged(session) && authService.userExists(params.get("usernameEmail"))) {
            // errorService.getToast(session, "usernameExists"); // Username già esistente
            return "redirect:/"; // Rimani sulla pagina di registrazione con messaggio di errore
        } else if (!authService.isLogged(session) && !authService.userExists(params.get("usernameEmail"))) {
            nome = params.get("nome");
            cognome = params.get("cognome");
            usernameEmail = params.get("usernameEmail");

        } else if (authService.isLogged(session)) {
            User user = (User) session.getAttribute("user");
            usernameEmail = user.getUsernameEmail();
            nome = user.getStudente().getNome();
            cognome = user.getStudente().getCognome();

        }

        model.addAttribute("nome", nome);
        model.addAttribute("cognome", cognome);
        model.addAttribute("usernameEmail", usernameEmail);
        return "firstTime";
    }

    @PostMapping("/registrazione")
    public String registrazione(HttpSession session, @RequestParam HashMap<String, String> params, Model model) {
        if (authService.isLogged(session)) {
            authService.updateUser(session, params, model);
        } else {
            authService.registerUser(session, params, model);
        }

        return "redirect:/homeStud";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        return "redirect:/";
    }
}
