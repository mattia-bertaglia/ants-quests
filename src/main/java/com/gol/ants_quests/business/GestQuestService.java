package com.gol.ants_quests.business;


import java.util.HashMap;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {
    
    private final QuestsHibService qstSrv;

    public void findDomandeByID(String domanda_id,Model model){
        model.addAttribute("quest", qstSrv.findByID(Long.parseLong(domanda_id)).get());
    }

    public void empyObject(Model model){
        Quest questionario = new Quest();
        CategoriaQuest categoria = new CategoriaQuest();
        questionario.setCategoriequest(categoria);
        model.addAttribute("quest", questionario);

    }

    public void saveTest(HashMap<String, String> params){
        CategoriaQuest categoria = new CategoriaQuest();

        if(params.get("type") != null && params.get("titolo") != null && params.get("id_quest") == ""){
            Quest questionario = new Quest();
            categoria.setIdCat(params.get("type"));
            questionario.setCategoriequest(categoria);
            questionario.setTitolo(params.get("titolo"));
            qstSrv.save(questionario);
        }else{
            Quest questEsistente = qstSrv.findByID(Long.parseLong(params.get("id_quest"))).get();
            categoria.setIdCat(params.get("type"));
            questEsistente.setCategoriequest(categoria);
            questEsistente.setTitolo(params.get("titolo"));
            qstSrv.save(questEsistente);
        }

    
    }



}
