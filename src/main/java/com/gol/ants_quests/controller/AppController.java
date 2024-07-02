package com.gol.ants_quests.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.services.ErrorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppController {

    private final ErrorService errServ;

    @GetMapping("/")
    public String openIndex(Model model, @RequestParam HashMap<String, String> params) {
        errServ.getToast(model, params);
        return "index.html";
    }

}
