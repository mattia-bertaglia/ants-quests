
package com.gol.ants_quests.controller;

import com.gol.ants_quests.hibernateStudentiCorsi.entities.Corso;
import com.gol.ants_quests.hibernateStudentiCorsi.services.CorsiHibService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/corsi")
public class CorsoController {

    @Autowired
    private CorsiHibService corsoService;

    /*
     * @GetMapping("/add")
     * public ResponseEntity<Corso> addCorso(@RequestBody Corso corso) {
     * Corso nuovoCorso = corsoService.saveCorso(corso);
     * return ResponseEntity.ok(nuovoCorso);
     * }
     */
}
