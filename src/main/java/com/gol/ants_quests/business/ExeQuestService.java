package com.gol.ants_quests.business;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.EsitoQuest;
import com.gol.ants_quests.hibernate.entities.OnlyStudente;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.EsitiHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExeQuestService {

    private final EsitiHibService esitiSrv;
    private final CategorieHibService categorieHibSrv;
    private final QuestsHibService questSrv;

    public void findAllEsitiQuest(Model model) {
        model.addAttribute("listaEsitiQuestionari", esitiSrv.findAll());
    }

    public void findAllQuestByCategoria(Model model) {
        model.addAttribute("listaQuestionari", questSrv.findAll());
    }

    public void findByData(Model model, String data_inizio, String data_fine) {
        model.addAttribute("listaEsitiQuestionari",
                esitiSrv.findData(LocalDate.parse(data_inizio), LocalDate.parse(data_fine)));
    }

    public void openHomeStud(Model model, Long idStudente) {
        log.info("caricamento categorie");
        model.addAttribute("categorie", categorieHibSrv.findAll());
        log.info("caricamento esiti studente: " + idStudente);
        model.addAttribute("esiti", esitiSrv.findByStudente(idStudente));
    }

    public void eleborazioneQuestionario(User user, HashMap<String, String> params) {

        Optional<Quest> quest = questSrv.findByID(Long.parseLong(params.get("id-quest")));
        String tempo = params.get("tempo-quest");
        int punteggio = 0;

        // in params ci sono come chiave dom-{id} e valore ans-{id}
        // con quest andiamo a controllare quante risposte corrette su quante

        EsitoQuest esitoFinale = new EsitoQuest();
        esitoFinale.setDataEsecuzione(Date.valueOf(LocalDate.now()));
        esitoFinale.setPunteggio(punteggio + "/" + quest.get().getDomanda().size());
        esitoFinale.setTempo(tempo);

        esitoFinale.setQuest(quest.get());
        esitoFinale.setStudente(new OnlyStudente(user.getStudente().getIdStudente(), user, null, null, null, null, null,
                null, null, null, null));

        esitiSrv.save(esitoFinale);

        // elaborazione PDF

    }

}
