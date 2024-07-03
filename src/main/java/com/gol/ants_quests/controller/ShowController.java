package com.gol.ants_quests.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gol.ants_quests.services.ShowService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ShowController {
    
    private final ShowService showSrv;

    @GetMapping("/esiti")
    public String esiti(Model model) {

        showSrv.findAll(model);
        return "esitiQuestionari.html";
    }


}
