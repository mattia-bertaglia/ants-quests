package com.gol.ants_quests.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.business.GesCorsiService;
import com.gol.ants_quests.business.GesStudentiService;

import lombok.RequiredArgsConstructor;

// Ross & Nass
@Controller
@RequestMapping("/ges_studenti")
@RequiredArgsConstructor
public class GesStudentiController {

    private final GesStudentiService studSrv;
    private final GesCorsiService corsiSrv;
    /* private final GestQuestService questSrv; */

    @GetMapping("/")
    public String getStud(Model model) {
        model.addAttribute("corsi", corsiSrv.findAll());
        model.addAttribute("studenti", studSrv.findAllStudentiDTO());
        /* questSrv.findAll(model); */

        return "gesStudentiAdmin.html";
    }

    @PostMapping("/savestud")
    public String saveStud(@RequestParam HashMap<String, String> params) {
        studSrv.saveStudenteFixed(params);
        return "redirect:/ges_studenti/";
    }

    @PostMapping("/updatestud")
    public String updateStud(@RequestParam HashMap<String, String> params) {
        studSrv.updateStudenteFixed(params);
        return "redirect:/ges_studenti/";
    }

}
