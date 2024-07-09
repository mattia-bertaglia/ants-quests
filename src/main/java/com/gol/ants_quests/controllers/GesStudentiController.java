package com.gol.ants_quests.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.business.GesStudentiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ges_studenti")
@RequiredArgsConstructor
public class GesStudentiController {

    private final GesStudentiService studSrv;

    @GetMapping("/")
    public String getStud(Model model) {
        model.addAttribute("studenti", studSrv.findAllStudenti());
        return "testStudenti.html";
    }

    @PostMapping("/savestud")
    public String saveStud(@RequestParam HashMap<String, String> params) {
        studSrv.saveStudente(params);
        return "redirect:/studenti/";
    }

}
