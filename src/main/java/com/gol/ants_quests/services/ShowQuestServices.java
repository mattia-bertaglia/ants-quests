package com.gol.ants_quests.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.services.EsitiHibService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowQuestServices {

    private final EsitiHibService cliHibSrv;

    public void findAll(Model model) {

        model.addAttribute("listaQuestionari", cliHibSrv.findAll());
    }
    
}
