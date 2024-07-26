package com.gol.ants_quests.hibernate.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.repositories.StudentiRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentiHibService extends GenericHibService<Studente, Long, StudentiRepository> {

    public StudentiHibService(StudentiRepository repository) {
        super(repository);

    }

    /**
     * Metodo che permette di modificare idCorso dello Studente
     * 
     * @param idStudente
     * @param idCorso
     */
    @Transactional
    public void modificaCorso(Long idStudente, Long idCorso) {
        getRepository().modificaCorso(idStudente, idCorso);
    }
    /*
     * @Transactional
     * public void modificaStudente(Long idStudente, Long id) {
     * getRepository().modificaStudente(idStudente, id);
     * }
     */

    public List<Studente> findByNomeAndCognome(String nome, String cognome) {
        return getRepository().findByNomeAndCognome(nome, cognome);

    }

    public List<Object[]> cercaStudenti(Long idStudente, String nome, String cognome) {
        return getRepository().cercaStudenti(idStudente, nome, cognome);
    }

    public List<Studente> findLastStuds() {
        return getRepository().findLastStuds();
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
