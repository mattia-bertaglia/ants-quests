package com.gol.ants_quests.business;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.OnlyCorso;

import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.entities.User;
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
    private final UsersHibService usersSrv;

    public List<Studente> findAllStudenti() {
        return studHibSrv.findAll(Sort.by(Direction.DESC, "dataInserimento"));
    }

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
        salvatoUser.setStudente(studHibSrv.save(studenteTemp));
        // fine esempio
    }

    public Optional<Studente> findByTelefono(String telefono) {
        return studHibSrv.findByTelefono(telefono);
    }




    

}
