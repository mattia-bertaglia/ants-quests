package com.gol.ants_quests.hibernate.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Corso;

import com.gol.ants_quests.hibernate.repositories.CorsiRepository;

@Service
public class CorsiHibService extends GenericHibService<Corso, Long, CorsiRepository> {
    public CorsiHibService(CorsiRepository repository) {
        super(repository);
    }

    public Optional<Corso> findById(Long idCorso) {
        return getRepository().findById(idCorso);
    }

    public List<Corso> findByNome(String nome) {
        return getRepository().findByNome(nome);
    }

    public List<Corso> findByDataInizioOrDataFine(Date dataInizio, Date dataFine) {
        return getRepository().findByDataInizioOrDataFine(dataInizio, dataFine);
    }

    public List<Corso> findAll(Sort sort) {
        return getRepository().findAll(Sort.by(Direction.DESC, "dataInizio"));
    }

    public void update(Corso corso) {

    }

}
