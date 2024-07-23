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

    public void findAll(Model model) {
        model.addAttribute("listaCategorie", qstSrv.findAll());
    }

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
            qstSrv.save(questEsistente); // funzione da cambiare con update
            id_domanda = params.get("id_quest");
        }

        return id_domanda;

    }

    public String gestioneDomande(Quest oggetto) {
        boolean esito = true;
        boolean modificaRisposte;
        DomandaQuest domanda = null;

        for (int i = 0; i < oggetto.getDomanda().size(); i++) {

            modificaRisposte = true;

            if (oggetto.getDomanda().get(i).getIdQstDet() == null
                    && oggetto.getDomanda().get(i).getDomanda() != null) {

                domanda = new DomandaQuest();
                Quest id_quest = new Quest();
                domanda.setDomanda(oggetto.getDomanda().get(i).getDomanda());
                id_quest.setIdQst(oggetto.getIdQst());
                domanda.setDom(id_quest);
                domSrv.save(domanda);
                esito = esito && true;

            } else if (oggetto.getDomanda().get(i).getIdQstDet() != null
                    && oggetto.getDomanda().get(i).getDomanda() == "") {

                domSrv.delete(oggetto.getDomanda().get(i).getIdQstDet());
                esito = esito && true;
                modificaRisposte = false;

            } else if (oggetto.getDomanda().get(i).getIdQstDet() != null
                    && oggetto.getDomanda().get(i).getDomanda() != "") {

                domanda = domSrv.findByID(oggetto.getDomanda().get(i).getIdQstDet()).get();
                domanda.setDomanda(oggetto.getDomanda().get(i).getDomanda());
                domSrv.save(domanda); // da cambiare con update
                esito = esito && true;

            } else {
                modificaRisposte = false;
                esito = esito && false;
            }

            if (modificaRisposte) {
                for (int y = 0; y < oggetto.getDomanda().get(i).getRisp().size(); y++) {

                    if (oggetto.getDomanda().get(i).getRisp().get(y).getIdAns() == null &&
                            !oggetto.getDomanda().get(i).getRisp().get(y).getRisposta().equals("")) {
                        RispostaQuest risposta = new RispostaQuest();
                        risposta.setRisposta(oggetto.getDomanda().get(i).getRisp().get(y).getRisposta());
                        risposta.setCorretta(oggetto.getDomanda().get(i).getRisp().get(y).getCorretta());
                        risposta.setDomandaQuest(domanda);
                        risSrv.save(risposta);
                        esito = esito && true;
                    } else if (oggetto.getDomanda().get(i).getRisp().get(y).getIdAns() != null &&
                            !oggetto.getDomanda().get(i).getRisp().get(y).getRisposta().equals("")) {
                        // modifica risposta già esistente
                    } else {
                        esito = esito && false;
                    }
                }
            }
        }

        if (esito == true) {
            return "OK";
        } else {
            return "";
        }

    }
}
