package com.gol.ants_quests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReindStart {

    @GetMapping("/")
    public String inizio() {
        return "index.html";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/guest-prima")
    public String guestfirst() {
        return "guest-prima.html";
    }

    @GetMapping("/guest-home")
    public String guestHome() {
        return "guest-home.html";
    }

}
