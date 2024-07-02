package com.gol.ants_quests.hibernateStudentiCorsi.services;

import java.sql.Date;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernateStudentiCorsi.repositories.CorsiRepository;

import scala.collection.immutable.List;

@Service
public class CorsiHibService extends GenericHibService <Corso, Integer, CorsiRepository> {
    public CorsiHibService(CorsiRepository repository){
        super(repository);
    }
    List<Corso> findByNome(String nome){
        return getRepository().findByNome(nome);
    }
    List<Corso> findByDataInzio(Date dataInzio){
        return getRepository().findByDataInzio(dataInzio);
    }
    List<Corso> findByDataFine(Date dataFine){
    return getRepository().findByDataFine(dataFine);
}
}