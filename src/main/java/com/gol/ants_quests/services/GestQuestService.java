package com.gol.ants_quests.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {

    private final CategorieHibService questSrv;
    private  QuestsHibService queSrv;

    public void findAll(Model model) {

        model.addAttribute("listaCategorie", questSrv.getAllCategories());
    }

     public Quest createQuestWithTitolo(String titolo) {
        Quest newQuest = new Quest();
        newQuest.setTitolo(titolo);
        return queSrv.createQuest(newQuest);
    }


}
