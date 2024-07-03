package com.gol.ants_quests.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;
import com.gol.ants_quests.services.ErrorService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final ErrorService errorService;

    @PostMapping("/signin")
    public String signin(@RequestParam HashMap<String, String> params, Model model) {
        if (params.containsKey("usernameEmail") && params.containsKey("passkey")
                && params.containsKey("confirmPasskey")) {
            String usernameEmail = params.get("usernameEmail");
            String passkey = params.get("passkey");
            String confirmPasskey = params.get("confirmPasskey");

            if (!passkey.equals(confirmPasskey)) {
                params.put("status", "passwordMismatch");
                errorService.getToast(model, params);
                return "index";
            }

            Optional<User> existingUser = userRepository.findByUsernameEmail(usernameEmail);
            if (existingUser.isPresent()) {
                params.put("status", "userExists");
                errorService.getToast(model, params);
                return "index";
            }

            User user = new User();
            user.setUsernameEmail(usernameEmail);
            user.setPasskey(passkey);
            userRepository.save(user);

            return "redirect:/?status=signOK";
        }

        params.put("status", "erroreLog");
        errorService.getToast(model, params);
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam HashMap<String, String> params, HttpSession session, Model model) {
        if (params.containsKey("usernameEmail") && params.containsKey("passkey")) {
            String usernameEmail = params.get("usernameEmail");
            String passkey = params.get("passkey");

            Optional<User> user = userRepository.findByUsernameEmail(usernameEmail);
            User ruolo = new User();

            if (user.isPresent() && user.get().getPasskey().equals(passkey)) {
                session.setAttribute("usrlog", true);
                session.setAttribute("usernameEmail", user.get().getUsernameEmail());
                
                if(ruolo.getRole().equals("studente"))
                    return "";

                if(ruolo.getRole().equals("guest"))
                    return "";
                
                if(ruolo.getRole().equals("admin"))
                    return "";
            }
        } else {
            params.put("status", "erroreLog");
            errorService.getToast(model, params);
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/?status=logoutOK";
    }
}
