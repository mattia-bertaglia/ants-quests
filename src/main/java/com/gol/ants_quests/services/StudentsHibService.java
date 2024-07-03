package com.gol.ants_quests.services;

import org.springframework.stereotype.Service;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.repositories.StudentsRepository;

@Service
public class StudentsHibService extends GenericHibService<Studente, Integer, StudentsRepository> {

    public StudentsHibService(StudentsRepository repository) {
        super(repository);
    }

    public Studente findByNomeAndCognome(String nome, String cognome) {
        return getRepository().findByNomeAndCognome(nome, cognome);
    }

}
