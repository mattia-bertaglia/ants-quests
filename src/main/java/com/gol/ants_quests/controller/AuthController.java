package com.gol.ants_quests.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/signin")
    public String signin(@RequestParam HashMap<String, String> params) {
        if (params.containsKey("usernameEmail") && params.containsKey("passkey")) {
            User user = new User();
            user.setUsernameEmail(params.get("usernameEmail"));
            user.setPasskey(params.get("passkey"));
            userRepository.save(user);
            return "redirect:/?status=signOK";
        }
        return "redirect:/?status=erroreLog";
    }

    @PostMapping("/login")
    public String login(@RequestParam HashMap<String, String> params, HttpSession session) {
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
        }
        return "redirect:/?status=erroreLog";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/?status=logoutOK";
    }
}
