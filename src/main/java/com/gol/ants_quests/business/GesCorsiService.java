package com.gol.ants_quests.business;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Corso;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.services.CorsiHibService;
import com.gol.ants_quests.hibernate.services.StudentiHibService;

import lombok.RequiredArgsConstructor;

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

    public Optional<Studente> findByTelefono(String telefono) {
        return studHibSrv.findByTelefono(telefono);
    }

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

    public Studente eliminaStudenteDalCorso(HashMap<String, String> params) {
        if (params.get("idStudente") != null && !"".equals(params.get("idStudente"))) {
            Long idStudente = Long.parseLong(params.get("idStudente"));
            Studente studente = studHibSrv.findById(idStudente).get();

            if (studente != null) {
                // Rimuovi l'ID del corso dallo studente
                studente.setCorso(null);

                // Salva le modifiche
                return studHibSrv.save(studente);
            } else {
                // Gestisci il caso in cui lo studente non viene trovato
                throw new RuntimeException("Studente non trovato con ID: " + idStudente);
            }
        } else {
            // Gestisci il caso in cui l'ID dello studente non è presente nei parametri
            throw new IllegalArgumentException("ID dello studente non fornito");
        }
    }

}
