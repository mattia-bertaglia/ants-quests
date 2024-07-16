package com.gol.ants_quests.hibernate.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gol.ants_quests.hibernate.entities.Studente;

@Repository
public interface StudentiRepository extends JpaRepository<Studente, Long> {

    @Modifying
    @Query(value = "update studenti set corso_id = :idCorso where id_studente = :idStudente", nativeQuery = true)
    public void modificaCorso(@Param("idStudente") Long idStudente, @Param("idCorso") Long idCorso);

    Optional<Studente> findById(Long idStudente);

    List<Studente> findByNomeAndCognome(String nome, String cognome);

    List<Studente> findByNomeOrCognome(String nome, String cognome);

    List<Studente> findAll(Sort sort);

    Studente findByDataNascita(Date dataNascita);

    List<Studente> findByCap(String cap);

    List<Studente> findByProvincia(String provincia);

    Optional<Studente> findByTelefono(String telefono);

    List<Studente> findByNote(String note);

    List<Studente> findByDataInserimento(Date dataInserimento);

}
