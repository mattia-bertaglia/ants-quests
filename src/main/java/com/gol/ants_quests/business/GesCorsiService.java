package com.gol.ants_quests.business;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gol.ants_quests.hibernate.entities.Corso;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.services.CorsiHibService;
import com.gol.ants_quests.hibernate.services.StudentiHibService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GesCorsiService {

    private final CorsiHibService corsiHibSrv;
    private final StudentiHibService studHibSrv;

    public List<Corso> findAll() {
        return corsiHibSrv.findAll(Sort.by(Direction.DESC, "dataInizio"));
    }

    public Optional<Corso> findById(Long idCorso) {

        return corsiHibSrv.findById(idCorso);
    }

    public Optional<Studente> findByTelefono(String telefono) {
        return studHibSrv.findByTelefono(telefono);
    }

    public Corso saveCorso(HashMap<String, String> params) {
        Corso corso = new Corso();

        if (params.get("idcorso") != null && !"".equals(params.get("idcorso")))
            corso.setIdCorso(Long.parseLong(params.get("idcorso")));
        corso.setNome(params.get("nome"));
        if (params.get("datainizio") != null && !"".equals(params.get("datainizio")))
            corso.setDataInizio(Date.valueOf(params.get("datainizio")));
        if (params.get("datafine") != null && !"".equals(params.get("datafine")))
            corso.setDataFine(Date.valueOf(params.get("datafine")));

        return corsiHibSrv.save(corso);
    }

    /*
     * public User registerUser(HashMap<String, String> userData, Model model) {
     * String email = userData.get("usernameEmail");
     * String password = userData.get("passkey");
     * 
     * if (email == null || password == null || userExists(email)) {
     * errorService.getToast(model, "registrationError");
     * return null;
     * }
     * 
     * User user = new User();
     * user.setUsernameEmail(email);
     * user.setPasskey(bcrypt.encode(password));
     * user.setRuolo(Ruolo.guest);
     * user.setFirstTime(false);
     * 
     * return userRepository.save(user);
     * }
     * 
     */

    // al momento aggiungendo lo studente tramite numero di telefono, eventualmente
    // con email, LISTA STUDENTI

    /*
     * public List<Studente> aggiungiStudenteCorso(HashMap<String, String> params) {
     * Corso corso = new Corso();
     * 
     * for (OnlyStudente studente : corso.getStudenti()) {
     * System.out.println(studente);
     * }
     * return studHibSrv.findAll();
     * }
     */

    // lista studenti in corsi

}
