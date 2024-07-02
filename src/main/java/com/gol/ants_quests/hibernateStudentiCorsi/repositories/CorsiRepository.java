package com.gol.ants_quests.hibernateStudentiCorsi.repositories;

import java.sql.Date;
import java.util.List;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernateStudentiCorsi.entities.Corso;
@Repository
public interface CorsiRepository extends JpaRepository <Corso, Integer> {

List <Corso> findByNome (String name);
Corso findByDataInzio(Date dataInizio);
Corso findByDataFine(Date dataFine);

}    

