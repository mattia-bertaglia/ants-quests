package com.gol.ants_quests.business;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.dto.EsitoQuestDTO;
import com.gol.ants_quests.dto.StudenteDTO;

import com.gol.ants_quests.hibernate.entities.OnlyCorso;
import com.gol.ants_quests.hibernate.entities.OnlyEsitoQuest;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.entities.User;

import com.gol.ants_quests.hibernate.services.StudentiHibService;
import com.gol.ants_quests.hibernate.services.UsersHibService;

import com.gol.ants_quests.util.Ruolo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/* aggregatore */

@Slf4j
@Service
@RequiredArgsConstructor
public class GesStudentiService {

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    private final StudentiHibService studHibSrv;
    private final UsersHibService usersSrv;

    public List<Studente> findAllStudenti() {
        return studHibSrv.findAll(Sort.by(Direction.DESC, "dataInserimento"));
    }

    /* inizio per StudenteDTO */
    public List<StudenteDTO> findAllStudentiDTO() {
        List<Studente> studenti = studHibSrv.findAll(Sort.by(Sort.Direction.DESC, "dataInserimento"));
        List<StudenteDTO> result = new ArrayList<StudenteDTO>();
        for (Studente stud : studenti) {
            result.add(convertDto(stud));
        }

        return result;
    }

    public StudenteDTO convertDto(Studente stud) {
        if (stud == null) {
            log.error("Lo Studente non può essere null");
        }

        StudenteDTO studenteDTO = new StudenteDTO();
        studenteDTO.setIdStudente(stud.getIdStudente());
        studenteDTO.setNome(stud.getNome());
        studenteDTO.setCognome(stud.getCognome());
        studenteDTO.setDataNascita(stud.getDataNascita());
        studenteDTO.setCap(stud.getCap());
        studenteDTO.setProvincia(stud.getProvincia());
        studenteDTO.setTelefono(stud.getTelefono());
        studenteDTO.setNote(stud.getNote());
        studenteDTO.setDataInserimento(stud.getDataInserimento());

        // Gestione User
        if (stud.getUser() != null) {
            User user = stud.getUser();
            studenteDTO.setUserId(user.getId());
            studenteDTO.setUsernameEmail(user.getUsernameEmail() != null ? user.getUsernameEmail() : "");
            studenteDTO.setPasskey(user.getPasskey() != null ? user.getPasskey() : "");
            studenteDTO.setRuolo(user.getRuolo() != null ? user.getRuolo().toString() : "");
            studenteDTO.setFirstTime(user.isFirstTime());
        } else {
            // Imposta valori predefiniti se `User` è null
            studenteDTO.setUserId(null);
            studenteDTO.setUsernameEmail("");
            studenteDTO.setPasskey("");
            studenteDTO.setRuolo("");
            studenteDTO.setFirstTime(false);
        }

        // Gestione Corso
        if (stud.getCorso() != null) {
            OnlyCorso corso = stud.getCorso();
            studenteDTO.setCorsoId(corso.getIdCorso());
            studenteDTO.setNomeCorso(corso.getNome() != null ? corso.getNome() : "");
            studenteDTO.setDataInizio(corso.getDataInizio());
            studenteDTO.setDataFine(corso.getDataFine());
        } else {
            // Imposta valori predefiniti se `Corso` è null
            studenteDTO.setCorsoId(null);
            studenteDTO.setNomeCorso("- NESSUN CORSO-");
            studenteDTO.setDataInizio(null);
            studenteDTO.setDataFine(null);
        }

        // Gestione EsitoQuest
        List<EsitoQuestDTO> esitoQuestDTOList = new ArrayList<>();
        if (stud.getEsquestionari() != null && !stud.getEsquestionari().isEmpty()) {
            for (OnlyEsitoQuest esiti : stud.getEsquestionari()) {
                EsitoQuestDTO esitoQuestDTO = new EsitoQuestDTO();
                esitoQuestDTO.setIdEstQst(esiti.getIdEstQst());
                esitoQuestDTO.setDataEsecuzione(esiti.getDataEsecuzione());
                esitoQuestDTO.setPunteggio(esiti.getPunteggio());
                esitoQuestDTO.setTempo(esiti.getTempo());
                esitoQuestDTO.setCategoriaQuest(esiti.getCategoriaQuest());
                esitoQuestDTO.setTitoloQuest(esiti.getTitoloQuest());
                esitoQuestDTO.setQuestId(esiti.getQuestId());
                esitoQuestDTO.setStudenteId(esiti.getStudenteId());
                esitoQuestDTOList.add(esitoQuestDTO);
            }
        }
        studenteDTO.setEsquestionari(esitoQuestDTOList);

        return studenteDTO;
    }

    /* fine per StudenteDTO */

    public Studente saveStudente(HashMap<String, String> params) {
        Studente stud = new Studente();

        if (params.get("idstudente") != null && !"".equals(params.get("idstudente")))
            stud.setIdStudente(Long.parseLong(params.get("idstudente")));

        stud.setNome(params.get("nome"));
        stud.setCognome(params.get("cognome"));
        if (params.get("datanascita") != null && !"".equals(params.get("datanascita")))
            stud.setDataNascita(Date.valueOf(params.get("datanascita")));

        stud.setCap(params.get("cap"));
        stud.setProvincia(params.get("provincia"));
        stud.setTelefono(params.get("telefono"));
        stud.setDataInserimento(Date.valueOf(LocalDate.now()));
        stud.setNote(params.get("note"));
        if (params.get("idcorso") != null && !"".equals("idcorso"))
            stud.setCorso(new OnlyCorso(Long.parseLong(params.get("idcorso")), null, null, null));

        /*
         * Attenzione se oggetto studente che passo al save ha un id gia presente a db
         * lo aggiorna, altrimenti fa un insert
         */
        return studHibSrv.save(stud);

    }

    public void saveStudenteFixed(HashMap<String, String> params) {

        User user = new User();
        user.setUsernameEmail(params.get("emailstudente"));
        user.setPasskey(bcrypt.encode("123"));
        user.setRuolo(Ruolo.studente);
        user.setFirstTime(true);

        // Salvataggio dell'utente con lo studente temporaneo
        User salvatoUser = usersSrv.save(user);
        // popolare dati studente da form di firstTime.html
        Studente studenteTemp = new Studente(null,
                salvatoUser,
                params.get("nomestudente"),
                params.get("cognomestudente"),
                null,
                null,
                null,
                null,
                null,
                Date.valueOf(LocalDate.now()),
                null, null);

        salvatoUser.setStudente(studHibSrv.save(studenteTemp));

    }

    public void updateStudenteFixed(HashMap<String, String> params) {

        // ti vai a prendere lo studente per params.studId
        Studente stud = new Studente(); // studHibSrv.findByID
        if (params.get("idstudente") != null && !"".equals(params.get("idstudente")))
            stud.setIdStudente(Long.parseLong(params.get("idstudente")));

        User user = stud.getUser();
        /*
         * if (params.get("user") != null && !"".equals(params.get("user")))
         * stud.setUser(user); CONTINUA A NON TROVARE USER, PROVARE IN DEBUG
         * FARE OGNI CONTROLLO SE E PRESENTE PASSWORD PRENDILA O SOVRASVRIVI DEFAUEL123
         * SE NON C'è, STESSA COSA EMAIL SE C
         * OK ALTIMENRTI CAMBIA, VEDERE STORICO CHAT CPT
         */
        user.setUsernameEmail(params.get("email"));
        if (params.get("password") != null && !"".equals(params.get("password")))
            user.setPasskey(params.get("password")); // se e' presente
        user.setRuolo("studente".equals(params.get("ruolo")) ? Ruolo.studente : Ruolo.guest); // OKAY

        // partiamo a settare i dati di user
        // ti prendi params.ruolo - params.email - controlli params.password e' presente
        // setti di stud.getUser() questi campi
        // chiami hib di user e ai il .save(stud.getUser())

        // Salvataggio dell'utente con lo studente temporaneo
        stud.setUser(usersSrv.save(user));

        // Popolare dati studente da form di firstTime.html
        stud.setNome(params.get("nome"));
        stud.setCognome(params.get("cognome"));
        stud.setDataNascita(Date.valueOf(params.get("dataNascita")));
        stud.setCap(params.get("cap"));
        stud.setProvincia(params.get("provincia"));
        stud.setTelefono(params.get("telefono"));
        stud.setNote(params.get("note"));

        // Aggiorna l'ID corso associato
        if (params.get("corsofrequentato") != null && !"".equals("corsofrequentato"))
            stud.setCorso(new OnlyCorso(Long.parseLong(params.get("corsofrequentato")), null, null, null));

        studHibSrv.save(stud);

    }

    public Optional<Studente> findByTelefono(String telefono) {
        return studHibSrv.findByTelefono(telefono);
    }

}
