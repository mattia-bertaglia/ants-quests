package com.gol.ants_quests.controllers;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.business.AuthService;
import com.gol.ants_quests.business.ErrorService;
import com.gol.ants_quests.hibernate.entities.Studente;
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
            errorService.getToast(session, params.get("status"));
            return "redirect:/";
        }
    }

    @GetMapping("/signup")
    public String signup(@RequestParam HashMap<String, String> params, HttpSession session, Model model) {
        String nome = "";
        String cognome = "";
        String usernameEmail = "";
        boolean userLogged = session.getAttribute("usrlog") != null ? (boolean) session.getAttribute("usrlog") : false;
        if (!userLogged && authService.userExists(params.get("usernameEmail"))) {
            errorService.getToast(session, "usernameExists"); // Username già esistente
            return "redirect:/"; // Rimani sulla pagina di registrazione con messaggio di errore
        } else if (!userLogged && !authService.userExists(params.get("usernameEmail"))) {
            nome = params.get("nome");
            cognome = params.get("cognome");
            usernameEmail = params.get("usernameEmail");

        } else if (userLogged) {
            User user = (User) session.getAttribute("user");
            usernameEmail = user.getUsernameEmail();
            Optional<Studente> studOpt = authService.nomeLoggedStud(usernameEmail);
            nome = studOpt.isPresent() ? studOpt.get().getNome() : "";
            studOpt = authService.cognomeLoggedStud(usernameEmail);
            cognome = studOpt.isPresent() ? studOpt.get().getCognome() : "";

        }

        model.addAttribute("nome", nome);
        model.addAttribute("cognome", cognome);
        model.addAttribute("usernameEmail", usernameEmail);
        return "firstTime";
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
