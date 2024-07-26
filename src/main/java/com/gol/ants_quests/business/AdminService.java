package com.gol.ants_quests.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.dto.StudenteDTO;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.services.EsitiHibService;
import com.gol.ants_quests.hibernate.services.StudentiHibService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final StudentiHibService studHibSrv;
    private final EsitiHibService esitiHibSrv;

    public void openHomeAdmin(Model model) {
        log.info("Caricamento Ultimi Studenti ...");
        List<Studente> studenti = studHibSrv.findLastStuds();
        List<StudenteDTO> result = new ArrayList<StudenteDTO>();
        for (Studente stud : studenti) {
            result.add(new StudenteDTO().convertDto(stud));
        }

        model.addAttribute("studenti", result);

        log.info("Caricamento Ultimi Questioanari ...");
        model.addAttribute("listaEsitiQuestionari", esitiHibSrv.findLastEsiti());

    }

}
