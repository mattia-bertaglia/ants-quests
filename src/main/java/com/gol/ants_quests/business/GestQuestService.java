package com.gol.ants_quests.business;

import java.util.HashMap;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.CategoriaQuest;
import com.gol.ants_quests.hibernate.entities.DomandaQuest;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.RispostaQuest;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.DomandeHibService;
import com.gol.ants_quests.hibernate.services.EsitiHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import com.gol.ants_quests.hibernate.services.RisposteHibService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GestQuestService {

    private final EsitiHibService esitiSrv;

    private final CategorieHibService catSrv;
    private final QuestsHibService qstSrv;
    private final DomandeHibService domSrv;
    private final RisposteHibService risSrv;

    private void findAllCategorie(Model model) {
        log.info("Caricamento Categorie e Questionari ...");
        model.addAttribute("listaCategorie", catSrv.findAll(Sort.by(Direction.DESC, "nome")));
    }

    // done
    public void openEsiti(Model model) {
        findAllCategorie(model);
        log.info("Caricamento Esiti ...");
        model.addAttribute("listaEsitiQuestionari", esitiSrv.findAll());
    }

    // done
    public void openLista(Model model) {
        findAllCategorie(model);
    }

    // done
    public void openGestione(String idQuest, Model model) {
        findAllCategorie(model);
        try {
            log.info("Caricamento Quest=" + idQuest);
            model.addAttribute("quest", qstSrv.findByID(Long.parseLong(idQuest)).get());
        } catch (NumberFormatException e) {
            log.info("Caricamento Quest Vuoto");
            Quest questionario = new Quest();
            questionario.setCategoriequest(new CategoriaQuest());
            model.addAttribute("quest", questionario);
        }
    }

    // change with findAllCategorie
    public void findAll(Model model) {
        model.addAttribute("listaCategorie", qstSrv.findAll());
    }

    public String saveTest(HashMap<String, String> params) {

        CategoriaQuest categoria = new CategoriaQuest();
        String id_domanda = "";

        if (params.get("type") != null && params.get("titolo") != null && params.get("id_quest") == "") {
            Quest questionario = new Quest();
            categoria.setIdCat(params.get("type"));
            questionario.setCategoriequest(categoria);
            questionario.setTitolo(params.get("titolo"));
            questionario.setAttivo(true);
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

            if (oggetto.getDomanda().get(i).getIdQstDet() != null) {
                if (oggetto.getDomanda().get(i).getDomanda() != null) {
                    domanda = domSrv.findByID(oggetto.getDomanda().get(i).getIdQstDet()).get();
                    domanda.setDomanda(oggetto.getDomanda().get(i).getDomanda());
                    domSrv.save(domanda); // da cambiare con update
                    esito = esito && true;
                } else {
                    domSrv.delete(oggetto.getDomanda().get(i).getIdQstDet());
                    esito = esito && true;
                    modificaRisposte = false;
                }

            } else {
                domanda = new DomandaQuest();
                Quest id_quest = new Quest();
                domanda.setDomanda(oggetto.getDomanda().get(i).getDomanda());
                id_quest.setIdQst(oggetto.getIdQst());
                domanda.setDom(id_quest);
                domSrv.save(domanda);
                esito = esito && true;
            }

            if (modificaRisposte) {
                RispostaQuest risposta;

                for (int y = 0; y < oggetto.getDomanda().get(i).getRisp().size(); y++) {
                    if (oggetto.getDomanda().get(i).getRisp().get(y).getIdAns() != null) {
                        if (oggetto.getDomanda().get(i).getRisp().get(y).getRisposta() != null) {
                            risposta = risSrv
                                    .findByID(oggetto.getDomanda().get(i).getRisp().get(y).getIdAns()).get();
                            risposta.setRisposta(oggetto.getDomanda().get(i).getRisp().get(y).getRisposta());
                            risposta.setCorretta(oggetto.getDomanda().get(i).getRisp().get(y).getCorretta());
                            risSrv.save(risposta); // cambiare con update
                        } else {
                            risSrv.delete(oggetto.getDomanda().get(i).getRisp().get(y).getIdAns());
                            esito = esito && true;
                        }

                    } else {
                        risposta = new RispostaQuest();
                        risposta.setRisposta(oggetto.getDomanda().get(i).getRisp().get(y).getRisposta());
                        risposta.setCorretta(oggetto.getDomanda().get(i).getRisp().get(y).getCorretta());
                        risposta.setDomandaQuest(domanda);
                        risSrv.save(risposta);
                        esito = esito && true;
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

    public void attivaDisattivaQuest(Long idQuest) {
        qstSrv.attivaDisattivaQuest(idQuest);
    }
}
