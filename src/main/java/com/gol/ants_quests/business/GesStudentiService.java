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
        List<Studente> studenti = studHibSrv.findAll(Sort.by(Sort.Direction.DESC, "idStudente"));
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
            studenteDTO.setNomeCorso("");
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

        return studHibSrv.save(stud);

    }

    public void saveStudenteFixed(HashMap<String, String> params) {

        User user = new User();
        user.setUsernameEmail(params.get("email"));
        user.setPasskey(bcrypt.encode("123"));
        user.setRuolo(Ruolo.studente);
        user.setFirstTime(true);
        User salvatoUser = usersSrv.save(user);

        Studente studenteTemp = new Studente(null,
                salvatoUser,
                params.get("nome"),
                params.get("cognome"),
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

        if (params.containsKey("studId") && !params.get("studId").isEmpty()) {

            Long idStudente = Long.parseLong(params.get("studId"));
            log.info("Ricerca IdStudente=" + idStudente);
            Optional<Studente> optionalStudente = studHibSrv.findByID(idStudente);
            if (optionalStudente.isPresent()) {

                Studente stud = optionalStudente.get();

                User user = stud.getUser();
                if (params.containsKey("email")) {
                    user.setUsernameEmail(params.get("email"));
                }
                if (params.containsKey("password") && !params.get("password").isEmpty()) {
                    user.setPasskey(bcrypt.encode(params.get("password")));
                }
                if (params.containsKey("ruolo")) {
                    user.setRuolo("studente".equals(params.get("ruolo")) ? Ruolo.studente : Ruolo.guest);
                }
                usersSrv.save(user);

                if (params.containsKey("nome")) {
                    stud.setNome(params.get("nome"));
                }
                if (params.containsKey("cognome")) {
                    stud.setCognome(params.get("cognome"));
                }
                if (params.containsKey("dataNascita") && params.get("dataNascita") != null) {
                    stud.setDataNascita(Date.valueOf(params.get("dataNascita")));
                }
                if (params.containsKey("cap")) {
                    stud.setCap(params.get("cap"));
                }
                if (params.containsKey("provincia")) {
                    stud.setProvincia(params.get("provincia"));
                }
                if (params.containsKey("telefono")) {
                    stud.setTelefono(params.get("telefono"));
                }
                if (params.containsKey("note")) {
                    stud.setNote(params.get("note"));
                }
                if (params.containsKey("corsofrequentato") && !params.get("corsofrequentato").isEmpty()) {
                    stud.setCorso(
                            new OnlyCorso(Long.parseLong(params.get("corsofrequentato")), null, null, null));
                }

                studHibSrv.save(stud);

            }
        }
    }

    public Optional<Studente> findByTelefono(String telefono) {
        return studHibSrv.findByTelefono(telefono);
    }

}
