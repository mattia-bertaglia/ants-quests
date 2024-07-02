package com.gol.ants_quests.hibernateStudentiCorsi.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernateStudentiCorsi.entities.Studente;
import com.gol.ants_quests.hibernateStudentiCorsi.repositories.StudentiRepository;

@Service
public class StudentiHibService extends GenericHibService<Studente, Integer, StudentiRepository> {

    public StudentiHibService(StudentiRepository repository) {
        super(repository);

    }

    public List<Studente> findByNomeAndCognome(String nome, String cognome) {
        return getRepository().findByNomeAndCognome(nome, cognome);

    }

    List<Studente> findByNome(String nome) {
        return getRepository().findByNome(nome);

    }

    List<Studente> findByCognome(String cognome) {
        return getRepository().findByCognome(cognome);

    }

    Studente findByDataNascita(Date dataNascita) {
        return getRepository().findByDataNascita(dataNascita);
    }

}
