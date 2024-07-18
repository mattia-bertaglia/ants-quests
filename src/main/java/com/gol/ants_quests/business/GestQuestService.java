package com.gol.ants_quests.business;


import java.util.HashMap;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.gol.ants_quests.hibernate.services.DomandeHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import com.gol.ants_quests.hibernate.services.RisposteHibService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestQuestService {
    
    private final QuestsHibService qstSrv;
    private final DomandeHibService domSrv;
    private final RisposteHibService risSrv;

    public void findDomandeByID(String domanda_id,Model model){
        model.addAttribute("quest", qstSrv.findByID(Long.parseLong(domanda_id)).get());
    }

    public void empyObject(Model model){
        Quest questionario = new Quest();
        questionario.setCategoriequest(new CategoriaQuest());
        model.addAttribute("quest", questionario);

    }





    

    public String saveTest(String jsonOggetto){
        /* 
        CategoriaQuest categoria = new CategoriaQuest();
        String id_domanda = "";

        if(params.get("type") != null && params.get("titolo") != null && params.get("id_quest") == ""){
            Quest questionario = new Quest();
            categoria.setIdCat(params.get("type"));
            questionario.setCategoriequest(categoria);
            questionario.setTitolo(params.get("titolo"));
            qstSrv.save(questionario);
            id_domanda = "" + questionario.getIdQst();
        }else{
            Quest questEsistente = qstSrv.findByID(Long.parseLong(params.get("id_quest"))).get();
            categoria.setIdCat(params.get("type"));
            questEsistente.setCategoriequest(categoria);
            questEsistente.setTitolo(params.get("titolo"));
            qstSrv.save(questEsistente);
            id_domanda = params.get("id_quest");
        }

        return id_domanda;
        */        
    }

    public String saveNewDomanda(HashMap<String, String> params){

        if(params.get("id_quest") != "" && params.get("domanda_inserita") != null){
            DomandaQuest domanda = new DomandaQuest();
            Quest domanda_id = new Quest();
            domanda.setDomanda(params.get("domanda_inserita"));
            domanda_id.setIdQst(Long.parseLong(params.get("id_quest")));
            domanda.setDom(domanda_id);
            domSrv.save(domanda);

            RispostaQuest risposta = new RispostaQuest();
            risposta.setDomandaQuest(domanda);
            risposta.setRisposta(params.get("risposta"));
            boolean corretta = true;
            if(!params.containsKey("corretta")){
                corretta = false;
            }
            risposta.setCorretta(corretta);
            risSrv.save(risposta);
                
        }

        return params.get("id_quest");

    }

    public String modificaDomanda(HashMap<String, String> params){

        if(params.get("id_domanda") != "" && params.get("domanda_inserita") != null){

            DomandaQuest questEsistente = domSrv.findByID(Long.parseLong(params.get("id_domanda"))).get();
            questEsistente.setDomanda(params.get("domanda_inserita"));
            domSrv.save(questEsistente);
        }

        return params.get("id_questionario");

    }



}
