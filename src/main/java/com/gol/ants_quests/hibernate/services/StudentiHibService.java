//Mattia
package com.gol.ants_quests.hibernate.services;

import java.util.List;
import java.util.Optional;

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

    public Optional<Studente> findNomeByUser(String user) {
        return getRepository().findNomeByUserUsernameEmail(user);
    }

    public Optional<Studente> findCognomeByUser(String user) {
        return getRepository().findCognomeByUserUsernameEmail(user);
    }

    /*
     * List<Studente> findByNome(String nome) {
     * return getRepository().findByNome(nome);
     * 
     * }
     * 
     * List<Studente> findByCognome(String cognome) {
     * return getRepository().findByCognome(cognome);
     * 
     * }
     * 
     * Studente findByDataNascita(Date dataNascita) {
     * return getRepository().findByDataNascita(dataNascita);
     * }
     * 
     * List<Studente> findByCap(String cap) {
     * return getRepository().findByCap(cap);
     * 
     * }
     * 
     * List<Studente> findByProvincia(String provincia) {
     * return getRepository().findByProvincia(provincia);
     * 
     * }
     * 
     * List<Studente> findByTelefono(String telefono) {
     * return getRepository().findByTelefono(telefono);
     * 
     * }
     * 
     * List<Studente> findByNote(String note) {
     * return getRepository().findByNote(note);
     * 
     * }
     * 
     * List<Studente> findByDataInserimento(Date dataInserimento) {
     * return getRepository().findByDataInserimento(dataInserimento);
     * 
     * }
     */

}
