package com.gol.ants_quests.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.gol.ants_quests.hibernate.services.UserHibService;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserHibService userService;

    @GetMapping("/signup")
    public String signup(@RequestParam HashMap<String, String> params, Model model) {
        return userService.signUpUser(params, model);
    }

        
    

    @GetMapping("/login")
    public String login(@RequestParam HashMap<String, String> params, HttpSession session, Model model) {
        return userService.logInUser(params, session, model);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/?status=logoutOK";
    }
}
