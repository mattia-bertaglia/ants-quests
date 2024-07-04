package com.gol.ants_quests.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernateStudentiCorsi.entities.Studente;
import com.gol.ants_quests.hibernateStudentiCorsi.services.StudentiHibService;

import lombok.RequiredArgsConstructor;

/* aggregatore */

@Service
@RequiredArgsConstructor
public class StudentiService {

    private final StudentiHibService studHibSrv;

    public void paginaStudenti(Model model) {
        model.addAttribute("studenti", studHibSrv.findAll());
    }

    public void save(HashMap<String, String> params) {
        Studente stud = new Studente();

        stud.setIdStudente(Integer.parseInt(params.get("idstud")));

        stud.setNome(params.get("nome"));

        /*
         * Attenzione se oggetto studente che passo al save ha un id gia presente a db
         * lo aggiorna, altrimenti fa un insert
         */
        studHibSrv.save(stud);

    }

}
