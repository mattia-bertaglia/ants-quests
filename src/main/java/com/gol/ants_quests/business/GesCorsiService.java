package com.gol.ants_quests.business;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Corso;
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

        /*
         * params avra' potenzialmente 3 parametri:
         * - idStudente
         * - nome
         * - cognome
         * vai a chiamare
         * studHibSrv.findByIdStudenteOrNomeOrCognomeAndCorsoId(idStudente, nome,
         * cognome, null) chew restituisce una lista
         * 
         * return Lista
         * 
         */
        return null;

    }

}
