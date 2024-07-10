package com.gol.ants_quests.business;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.EsitiHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final EsitiHibService esitiSrv;
    private final QuestsHibService qstSrv;
    private final CategorieHibService catSrv;

    public void findAllEsitiQuest(Model model) {
        model.addAttribute("listaEsitiQuestionari", esitiSrv.findAll());
    }

    public void findAllQuestByCategoria(Model model) {
        model.addAttribute("listaQuestionari", qstSrv.findAll());
    }

    public void findAllCategorie(Model model){
        model.addAttribute("listaCategorie", catSrv.findAll());
    }

}


