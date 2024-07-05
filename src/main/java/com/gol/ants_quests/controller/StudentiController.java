package com.gol.ants_quests.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.service.StudentiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/studenti")
@RequiredArgsConstructor
public class StudentiController {

    private final StudentiService studSrv;

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

    @GetMapping("/corsi")
    public String getCorsi(Model model) {
        model.addAttribute("corsi", studSrv.findAllCorsi());
        return "testCorsi.html";
    }

    @PostMapping("/savecorso")
    public String saveCorso(@RequestParam HashMap<String, String> params) {
        studSrv.saveCorso(params);
        return "redirect:/studenti/corsi";
    }

}
