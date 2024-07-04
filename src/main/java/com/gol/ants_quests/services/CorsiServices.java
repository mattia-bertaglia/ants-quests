package com.gol.ants_quests.services;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernateStudentiCorsi.entities.Corso;
import com.gol.ants_quests.hibernateStudentiCorsi.entities.Studente;
import com.gol.ants_quests.hibernateStudentiCorsi.services.CorsiHibService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CorsiServices {
    private final CorsiHibService corsiHibService;

   public void paginaCorsi(Model model) {
        model.addAttribute("corsi", corsiHibService.findAll());
    }

    public void save(HashMap<String, String> params) {
        Corso corso = new Corso();

        corso.setIdCorso(Integer.parseInt(params.get("idcorso")));

        corso.setNome(params.get("nome"));

        /*
         
Attenzione se oggetto studente che passo al save ha un id gia presente a db
lo aggiorna, altrimenti fa un insert*/
corsiHibService.save(corso);

    }
}



