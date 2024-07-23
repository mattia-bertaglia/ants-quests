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

import com.gol.ants_quests.dto.StudenteDTO;
import com.gol.ants_quests.hibernate.entities.OnlyCorso;
import com.gol.ants_quests.hibernate.entities.OnlyEsitoQuest;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.services.CorsiHibService;
import com.gol.ants_quests.hibernate.services.StudentiHibService;
import com.gol.ants_quests.hibernate.services.UsersHibService;
import com.gol.ants_quests.util.Ruolo;

import lombok.RequiredArgsConstructor;

/* aggregatore */

@Service
@RequiredArgsConstructor
public class GesStudentiService {

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    private final StudentiHibService studHibSrv;
    private final CorsiHibService corsiHibSrv;

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

        studenteDTO.setUserId(stud.getUser().getId());
        studenteDTO.setUsernameEmail(stud.getUser().getUsernameEmail());
        studenteDTO.setPasskey(stud.getUser().getPasskey());
        studenteDTO.setRuolo(stud.getUser().getRuolo().toString());
        studenteDTO.setFirstTime(stud.getUser().isFirstTime());

        studenteDTO.setCorsoId(stud.getCorso().getIdCorso());
        studenteDTO.setNomeCorso(stud.getCorso().getNome());
        studenteDTO.setDataInizio(stud.getCorso().getDataInizio());
        studenteDTO.setDataFine(stud.getCorso().getDataFine());

        for (OnlyEsitoQuest esiti : stud.getEsquestionari()) {
            // studenteDTO.setEsquestionario.add(esiti.convert)
        }

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
        // esempio
        User user = new User();
        user.setUsernameEmail(params.get("email")); // params.get
        user.setPasskey(bcrypt.encode("123"));
        user.setRuolo(Ruolo.studente);
        user.setFirstTime(true);

        // Salvataggio dell'utente con lo studente temporaneo
        User salvatoUser = usersSrv.save(user);
        // popolare dati studente da form di firstTime.html
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
                null,
                null);

        /*
         * if (params.get("idcorso") != null && !"".equals("idcorso"))
         * stud.setCorso(new OnlyCorso(Long.parseLong(params.get("idcorso")), null,
         * null, null));
         */
        salvatoUser.setStudente(studHibSrv.save(studenteTemp));
        // fine esempio

    }

    public Optional<Studente> findByTelefono(String telefono) {
        return studHibSrv.findByTelefono(telefono);
    }

}
