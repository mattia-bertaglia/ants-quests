package com.gol.ants_quests.business;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Corso;
import com.gol.ants_quests.hibernate.entities.OnlyCorso;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.services.CorsiHibService;
import com.gol.ants_quests.hibernate.services.StudentiHibService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GesCorsiService {

    private final CorsiHibService corsiHibSrv;
    private final StudentiHibService studHibSrv;

    public List<Corso> findAll() {
        return corsiHibSrv.findAll(Sort.by(Direction.DESC, "dataInizio"));
    }

    /*
     * public Optional<Corso> findById(Long idCorso) {
     * 
     * return corsiHibSrv.findById(idCorso);
     * }
     */

    public Corso saveCorso(HashMap<String, String> params) {
        Corso corso = new Corso();

        if (params.get("idcorso") != null && !"".equals(params.get("idcorso")))
            corso.setIdCorso(Long.parseLong(params.get("idcorso")));
        corso.setNome(params.get("nome"));
        if (params.get("datainizio") != null && !"".equals(params.get("datainizio")))
            corso.setDataInizio(Date.valueOf(params.get("datainizio")));
        if (params.get("datafine") != null && !"".equals(params.get("datafine")))
            corso.setDataFine(Date.valueOf(params.get("datafine")));

        return corsiHibSrv.save(corso);
    }

    public String eliminaStudenteDalCorso(HashMap<String, String> params) {
        if (params.get("idStudente") != null && !"".equals(params.get("idStudente"))) {
            Long idStudente = Long.parseLong(params.get("idStudente"));

            log.info("Ricerca IdStudente=" + idStudente);
            if (studHibSrv.findById(idStudente).isPresent()) {
                log.info("Studente Trovato, eliminazione da Corso");
                studHibSrv.modificaCorso(idStudente, null);
                return "OK";

            } else {
                log.error("Studente non trovato.");
                return "KO";
            }
        } else {
            log.error("IdStudente non ricevuto.");
            return "KO";
        }
    }

    public List<Studente> cercaStudenti(HashMap<String, String> params) {
        Long idStudente = null;
        String nome = null;
        String cognome = null;

        if (params.get("idStudente") != null && !"".equals(params.get("idStudente")))
            idStudente = Long.parseLong(params.get("idStudente"));

        if (params.get("nome") != null && !"".equals(params.get("nome")))
            nome = params.get("nome");

        if (params.get("cognome") != null && !"".equals(params.get("cognome")))
            cognome = params.get("cognome");

        List<Object[]> risultati = studHibSrv.cercaStudenti(idStudente, nome, cognome);
        List<Studente> studenti = new ArrayList<>();
        for (Object[] riga : risultati) {
            Studente studente = new Studente();
            studente.setIdStudente((Long) riga[0]);
            studente.setNome((String) riga[1]);
            studente.setCognome((String) riga[2]);
            studenti.add(studente);
        }

        return studenti;

    }

    public String aggiungiStudenteAlCorso(HashMap<String, String> params) {
        // es elimina studente+ id corso
        if (params.get("idStudente") != null && !"".equals(params.get("idStudente")) && params.get("idCorso") != null
                && !"".equals(params.get("idCorso"))) {
            Long idStudente = Long.parseLong(params.get("idStudente"));
            Long idCorso = Long.parseLong(params.get("idCorso"));

            log.info("Ricerca IdStudente=" + idStudente + "RicercaidCorso=" + idCorso);
            if (studHibSrv.findById(idStudente).isPresent() && corsiHibSrv.findById(idCorso).isPresent()) {
                log.info("Studente Trovato, aggiunto al Corso");
                studHibSrv.modificaCorso(idStudente, idCorso);
                return "OK";

            } else {
                log.error("Studente/Corso non trovato.");
                return "KO";
            }
        } else {
            log.error("IdStudente(idCorso) non ricevuto.");
            return "KO";
        }
    }

    /* per associare idcorso a studente nel modale aggiungi */
    public OnlyCorso findById(Long idCorso) {
        // Implementare il metodo per trovare il corso per ID, restituendo un'istanza di
        // OnlyCorso
        Optional<Corso> optionalCorso = corsiHibSrv.findById(idCorso);
        if (optionalCorso.isPresent()) {
            Corso corso = optionalCorso.get();
            OnlyCorso onlyCorso = new OnlyCorso();
            onlyCorso.setIdCorso(corso.getIdCorso());
            onlyCorso.setNome(corso.getNome());
            return onlyCorso;
        } else {
            throw new RuntimeException("Corso non trovato");
        }
    }
}
