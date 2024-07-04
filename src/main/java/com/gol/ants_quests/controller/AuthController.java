package com.gol.ants_quests.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;
import com.gol.ants_quests.hibernate.services.UserHibService;
import com.gol.ants_quests.services.AuthService;
import com.gol.ants_quests.services.ErrorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserHibService userService;
    private final UserRepository userRepository;
    private final ErrorService errorService;

    @GetMapping("/signup")
    public String signup(@RequestParam HashMap<String, String> params, Model model) {
        return userService.signUpUser(params, model);
    }

        
    

    @GetMapping("/login")
    public String login(@RequestParam HashMap<String, String> params, HttpSession session, Model model) {
        if (params.containsKey("usernameEmail") && params.containsKey("passkey")) {
            String usernameEmail = params.get("usernameEmail");
            String passkey = params.get("passkey");

            Optional<User> userOptional = userRepository.findByUsernameEmail(usernameEmail);
            if (userOptional.isPresent() && userOptional.get().getPasskey().equals(passkey)) {
                User user = userOptional.get();
                session.setAttribute("usrlog", true);
                session.setAttribute("usernameEmail", user.getUsernameEmail());

                String ruolo = user.getRuolo().toString();
                switch (ruolo) {
                    case "studente":
                    case "guest": // Reindirizza sia studenti che guest alla stessa pagina
                        return "redirect:/auth/homeStud";
                    case "admin":
                        return "redirect:/auth/homeAdmin";
                    default:
                        params.put("status", "unknownRuolo");
                        errorService.getToast(model, params);
                        return "redirect:/";
                }
            } else {
                /*
                 * Gestione dell'errore di login
                 */
                errorService.getToast(session, "erroreLog");
            }
        } else {
            params.put("status", "erroreLog");
            errorService.getToast(model, params);
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate(); // Invalida la sessione completamente
        return "redirect:/";
    }

    @GetMapping("/homeStud")
    public String homeStud(HttpSession session, Model model) {
        String ciao = "ciao";
        session.setAttribute("ciao", ciao);
        AuthService isLoggedIn = new AuthService();
        isLoggedIn.isUserLoggedIn(session);
        if (isLoggedIn.isUserLoggedIn(session)) {
            return "homeStud.html"; // Accesso consentito solo se l'utente è loggato
        } else {
            return "redirect:/auth/login"; // Se l'utente non è loggato, reindirizzalo alla pagina di login
        }
    }

}
