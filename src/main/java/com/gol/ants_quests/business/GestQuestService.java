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

    public void findDomandeByID(String domanda_id, Model model) {
        model.addAttribute("quest", qstSrv.findByID(Long.parseLong(domanda_id)).get());
    }

    public void empyObject(Model model) {
        Quest questionario = new Quest();
        questionario.setCategoriequest(new CategoriaQuest());
        model.addAttribute("quest", questionario);

    }

    public String saveTest(HashMap<String, String> params) {

        CategoriaQuest categoria = new CategoriaQuest();
        String id_domanda = "";

        if (params.get("type") != null && params.get("titolo") != null && params.get("id_quest") == "") {
            Quest questionario = new Quest();
            categoria.setIdCat(params.get("type"));
            questionario.setCategoriequest(categoria);
            questionario.setTitolo(params.get("titolo"));
            qstSrv.save(questionario);
            id_domanda = "" + questionario.getIdQst();
        } else {
            Quest questEsistente = qstSrv.findByID(Long.parseLong(params.get("id_quest"))).get();
            categoria.setIdCat(params.get("type"));
            questEsistente.setCategoriequest(categoria);
            questEsistente.setTitolo(params.get("titolo"));
            qstSrv.save(questEsistente);
            id_domanda = params.get("id_quest");
        }

        return id_domanda;

    }

    /* aggiunte Ross */

    public String gestioneDomande(Quest jsonOggetto) {

        /* 
        // Esempio di come gestire domande e risposte da jsonOggetto
        for (DomandaQuest domanda : jsonOggetto.getDomanda()) {
            // Salvataggio della domanda nel database
            DomandaQuest savedDomanda = domSrv.save(domanda);

            // Iterazione sulle risposte associate alla domanda
            for (RispostaQuest risposta : domanda.getRisp()) {
                risposta.setDomandaQuest(savedDomanda); // Imposta la domanda a cui appartiene la risposta
                // Salvataggio della risposta nel database
                risSrv.save(risposta);
            }
        }

        // Ritorna un messaggio di conferma o l'id del nuovo test salvato
        return "Operazione completata con successo";*/
        return "";
    }
}
