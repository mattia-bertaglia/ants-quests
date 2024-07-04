package com.gol.ants_quests.hibernateStudentiCorsi.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernateStudentiCorsi.entities.Studente;

@Repository
public interface StudentiRepository extends JpaRepository<Studente, Integer> {

    List<Studente> findByNomeAndCognome(String nome, String cognome);

    List<Studente> findByNome(String nome);

    List<Studente> findByCognome(String cognome);

    Studente findByDataNascita(Date dataNascita);

    List<Studente> findByCap(String cap);

    List<Studente> findByProvincia(String provincia);

    List<Studente> findByTelefono(String telefono);

    List<Studente> findByNote(String note);

    List<Studente> findAll();

    List<Studente> findByDataInserimento(Date dataInserimento);

}
