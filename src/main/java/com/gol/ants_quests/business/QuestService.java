package com.gol.ants_quests.business;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.services.EsitiHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final EsitiHibService esitiSrv;
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

}
