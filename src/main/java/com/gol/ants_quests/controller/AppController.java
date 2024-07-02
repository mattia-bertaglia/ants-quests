package com.gol.ants_quests.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AppController {

    @GetMapping("")
    public String index(){

        return "index.html";
    }

}
