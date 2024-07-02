package com.gol.ants_quests.controllerStudentiCorsi;

import org.springframework.stereotype.Controller;

import com.gol.ants_quests.hibernateStudentiCorsi.services.StudentiHibService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentiController {

    private final StudentiHibService studentiHibService;
    private final CorsiHibService corsiHibService;

}
