package com.gol.ants_quests.hibernate.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.Studente;

@Repository
public interface StudentiRepository extends JpaRepository<Studente, Long> {

    List<Studente> findByNomeAndCognome(String nome, String cognome);

    Optional<Studente> findByUserId(long userID);

    Optional<Studente> findNomeByUserUsernameEmail(String user);

    Optional<Studente> findCognomeByUserUsernameEmail(String user);
    /*
     * List<Studente> findByNome(String nome);
     * 
     * List<Studente> findByCognome(String cognome);
     * 
     * Studente findByDataNascita(Date dataNascita);
     * 
     * List<Studente> findByCap(String cap);
     * 
     * List<Studente> findByProvincia(String provincia);
     * 
     * List<Studente> findByTelefono(String telefono);
     * 
     * List<Studente> findByDataInserimento(Date dataInserimento);
     */

}
