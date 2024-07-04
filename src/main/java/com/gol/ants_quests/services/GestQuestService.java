package com.gol.ants_quests.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.DomandeHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import com.gol.ants_quests.hibernate.services.RisposteHibService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {

    private final CategorieHibService questSrv;
    private final QuestsHibService queSrv;
    private final DomandeHibService dom;
    private final RisposteHibService risp;

    public void findAll(org.springframework.ui.Model model) {

        model.addAttribute("listaCategorie", questSrv.getAllCategories());
    }






    @Transactional
    public Quest createQuestWithDomandeERisposte(String titolo, String categoriaId, List<DomandaQuest> domande) {
        CategoriaQuest categoriaQuest = questSrv.getCategoryById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        Quest quest = new Quest();
        quest.setTitolo(titolo);
        quest.setCategoriequest(categoriaQuest);
        quest = queSrv.createQuest(quest);

        for (DomandaQuest domanda : domande) {
            domanda.setDom(quest);
            domanda = dom.createDomanda(domanda);

            for (RispostaQuest risposta : domanda.getRisp()) {
                risposta.setDomandaQuest(domanda);
                risp.createRisposta(risposta);
            }
        }

        return quest;
    }







}
