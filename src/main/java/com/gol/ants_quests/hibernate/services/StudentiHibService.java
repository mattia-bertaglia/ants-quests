package com.gol.ants_quests.hibernate.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.repositories.StudentiRepository;

@Service
public class StudentiHibService extends GenericHibService<Studente, Long, StudentiRepository> {

    public StudentiHibService(StudentiRepository repository) {
        super(repository);

    }

    public List<Studente> findByNomeAndCognome(String nome, String cognome) {
        return getRepository().findByNomeAndCognome(nome, cognome);

    }

    public List<Studente> findByNomeOrCognome(String nome, String cognome) {
        return getRepository().findByNomeOrCognome(nome, cognome);

    }

    public List<Studente> findAll(Sort sort) {
        return getRepository().findAll(Sort.by(Direction.DESC, "dataInserimento"));
    }

    public Optional<Studente> findById(Long idStudente) {
        return getRepository().findById(idStudente);
    }

    public Studente findByDataNascita(Date dataNascita) {
        return getRepository().findByDataNascita(dataNascita);
    }

    public List<Studente> findByCap(String cap) {
        return getRepository().findByCap(cap);

    }

    public List<Studente> findByProvincia(String provincia) {
        return getRepository().findByProvincia(provincia);

    }

    public Optional<Studente> findByTelefono(String telefono) {
        return getRepository().findByTelefono(telefono);

    }

    public List<Studente> findByNote(String note) {
        return getRepository().findByNote(note);

    }

    public List<Studente> findByDataInserimento(Date dataInserimento) {
        return getRepository().findByDataInserimento(dataInserimento);

    }

}
