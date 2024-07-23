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

import com.gol.ants_quests.dto.StudenteDTO;
import com.gol.ants_quests.hibernate.entities.Studente;

@Repository
public interface StudentiRepository extends JpaRepository<Studente, Long> {

        @Modifying
        @Query(value = "update studenti set corso_id = :idCorso where id_studente = :idStudente", nativeQuery = true)
        public void modificaCorso(@Param("idStudente") Long idStudente, @Param("idCorso") Long idCorso);

        @Query(value = "select id_studente, nome, cognome from studenti where corso_id is null and " +
                        "((id_studente = :idStudente OR :idStudente IS NULL) " +
                        "and (nome = :nome OR :nome IS NULL) " +
                        "and (cognome = :cognome OR :cognome IS NULL))", nativeQuery = true)
        public List<Object[]> cercaStudenti(@Param("idStudente") Long idStudente, @Param("nome") String nome,
                        @Param("cognome") String cognome);

        Optional<Studente> findById(Long idStudente);

        List<Studente> findByNomeAndCognome(String nome, String cognome);

        List<Studente> findAll(Sort sort);

        /* List<StudenteDTO> findAllStudentiDTO(); */

        @Query("SELECT new com.gol.ants_quests.dto.StudenteDTO(s.idStudente, s.nome, s.cognome, s.dataNascita, s.cap, s.provincia, s.telefono, s.note, s.dataInserimento, "
                        +
                        "u.id, u.usernameEmail, u.passkey, u.ruolo, u.firstTime, " +
                        "c.idCorso, c.nome, c.dataInizio, c.dataFine) " +
                        "FROM Studente s " +
                        "LEFT JOIN s.user u " +
                        "LEFT JOIN s.corso c ")
        public List<StudenteDTO> findAllStudentiDTO();

        Studente findByDataNascita(Date dataNascita);

        List<Studente> findByCap(String cap);

        List<Studente> findByProvincia(String provincia);

        Optional<Studente> findByTelefono(String telefono);

        List<Studente> findByNote(String note);

        List<Studente> findByDataInserimento(Date dataInserimento);

}
