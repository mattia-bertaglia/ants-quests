package com.gol.ants_quests.services;



import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gol.ants_quests.hibernate.services.EsitiHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final EsitiHibService esitiSrv;
    private final QuestsHibService questSrv;


    public void findAllEsitiQuest(Model model) {
        model.addAttribute("listaEsitiQuestionari", esitiSrv.findAll());
    }


    public void findAllQuestByCategoria(Model model){
        model.addAttribute("listaQuestionari", questSrv.findAll());
    }

}
