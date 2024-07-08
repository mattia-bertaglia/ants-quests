package com.gol.ants_quests.business;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.OnlyCorso;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.services.StudentiHibService;

import lombok.RequiredArgsConstructor;

/* aggregatore */

@Service
@RequiredArgsConstructor
public class GesStudentiService {

    private final StudentiHibService studHibSrv;

    public List<Studente> findAllStudenti() {
        return studHibSrv.findAll(Sort.by(Direction.DESC, "dataInserimento"));
    }

    public Studente saveStudente(HashMap<String, String> params) {
        Studente stud = new Studente();

        if (params.get("idstudente") != null && !"".equals(params.get("idstudente")))
            stud.setIdStudente(Integer.parseInt(params.get("idstudente")));

        stud.setNome(params.get("nome"));
        stud.setCognome(params.get("cognome"));
        if (params.get("datanascita") != null && !"".equals(params.get("datanascita")))
            stud.setDataNascita(Date.valueOf(params.get("datanascita")));

        stud.setCap(params.get("cap"));
        stud.setProvincia(params.get("provincia"));
        stud.setTelefono(params.get("telefono"));
        stud.setDataInserimento(Date.valueOf(LocalDate.now()));
        stud.setNote(params.get("note"));
        if (params.get("idcorso") != null && !"".equals("idcorso"))
            stud.setCorso(new OnlyCorso(Integer.parseInt(params.get("idcorso")), null, null, null));

        /*
         * Attenzione se oggetto studente che passo al save ha un id gia presente a db
         * lo aggiorna, altrimenti fa un insert
         */
        return studHibSrv.save(stud);

    }

}
