package com.gol.ants_quests.services;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
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


    
    
    /*    WIP
    public Quest createQuestWithDomandeERisposte(String titolo, String categoriaId, List<Map<String, Object>> domandeData) {
        CategoriaQuest categoriaQuest = questSrv.getCategoryById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        Quest quest = new Quest();
        quest.setTitolo(titolo);
        quest.setCategoriequest(categoriaQuest);
        quest = queSrv.createQuest(quest);

        for (Map<String, Object> domandaData : domandeData) {
            DomandaQuest domanda = new DomandaQuest();
            domanda.setDomanda((String) domandaData.get("domanda"));
            domanda.setDom(quest);
            domanda = dom.createDomanda(domanda);

            List<Map<String, String>> risposteData = (List<Map<String, String>>) dom.get("risp");
            for (Map<String, String> rispostaData : risposteData) {
                RispostaQuest risposta = new RispostaQuest();
                risposta.setRisposta(rispostaData.get("risposta"));
                risposta.setCorretta(rispostaData.getOrDefault("corretta", "false"));
                risposta.setDomandaQuest(domanda);
                risp.createRisposta(risposta);
            }
        }

        return quest;
    }

*/





}
