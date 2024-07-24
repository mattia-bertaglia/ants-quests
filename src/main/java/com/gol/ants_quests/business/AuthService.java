package com.gol.ants_quests.business;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UsersRepository;
import com.gol.ants_quests.hibernate.services.StudentiHibService;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    private final UsersRepository userRepository;
    private final StudentiHibService studHibSrv;
    private final ErrorService errorService;

    public Optional<User> findByUsernameEmail(String usernameEmail) {
        return userRepository.findByUsernameEmail(usernameEmail);
    }

    public void registerUser(HttpSession session, HashMap<String, String> params, Model model) {
        String email = params.get("usernameEmail");
        String password = params.get("passkey");

        if (email == null || password == null || userExists(email)) {
            errorService.addErrorMessageToModel(model, "registrationError");
            return;
        }

        User user = new User();
        user.setUsernameEmail(email);
        user.setPasskey(bcrypt.encode(password));
        user.setRuolo(Ruolo.guest);
        user.setFirstTime(false);

        // Salvataggio dell'utente con lo studente temporaneo
        User salvatoUser = userRepository.save(user);
        // popolare dati studente da form di firstTime.html
        Studente studenteTemp = salvatoUser.getStudente();
        studenteTemp.setNome(params.get("nome"));
        studenteTemp.setCognome(params.get("cognome"));
        studenteTemp.setDataNascita(Date.valueOf(params.get("dataNascita")));
        studenteTemp.setCap(params.get("cap"));
        studenteTemp.setProvincia(params.get("provincia"));
        studenteTemp.setTelefono(params.get("telefono"));
        studenteTemp.setNote(params.get("note"));
        studenteTemp.setDataInserimento(Date.valueOf(LocalDate.now()));
        salvatoUser.setStudente(studHibSrv.save(studenteTemp));

        if (salvatoUser == null || salvatoUser.getId() == null) {
            errorService.addErrorMessageToModel(model, "registrationError");
            return;
        }

        errorService.addErrorMessageToSession(session, "registrationSuccess");
        setupSession(session, salvatoUser);
    }

    public void updateUser(HttpSession session, HashMap<String, String> params, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            errorService.addErrorMessageToModel(model, "userNotFoundError");
            return;
        }

        String password = params.get("passkey"); // Nuova password
        if (password != null && !password.trim().isEmpty()) {
            user.setPasskey(bcrypt.encode(password));
        }

        // Aggiorna i dati dello studente
        Studente studenteTemp = user.getStudente();
        if (studenteTemp != null) {
            studenteTemp.setNome(params.get("nome"));
            studenteTemp.setCognome(params.get("cognome"));
            studenteTemp.setDataNascita(Date.valueOf(params.get("dataNascita")));
            studenteTemp.setCap(params.get("cap"));
            studenteTemp.setProvincia(params.get("provincia"));
            studenteTemp.setTelefono(params.get("telefono"));
            studenteTemp.setNote(params.get("note"));

            // Salva lo studente aggiornato
            studenteTemp = studHibSrv.save(studenteTemp);
            user.setStudente(studenteTemp);
        }

        // Salva l'utente aggiornato
        User salvatoUser = userRepository.save(user);

        if (salvatoUser == null || salvatoUser.getId() == null) {
            errorService.addErrorMessageToModel(model, "registrationError");
            return;
        }

        // Aggiorna la sessione con i nuovi dati
        setupSession(session, salvatoUser);
        errorService.addErrorMessageToSession(session, "registrationSuccess");
    }

    public boolean userExists(String usernameEmail) {
        return userRepository.findByUsernameEmail(usernameEmail).isPresent();
    }

    public void setupSession(HttpSession session, User user) {
        session.setAttribute("usrlog", true);
        session.setAttribute("user", user);
    }

    public boolean logInUser(HashMap<String, String> params, HttpSession session, Model model) {
        if (params.containsKey("usernameEmail") && params.containsKey("passkey")) {
            String usernameEmail = params.get("usernameEmail");
            String passkey = params.get("passkey");

            Optional<User> userOptional = userRepository.findByUsernameEmail(usernameEmail);
            if (userOptional.isPresent() && bcrypt.matches(passkey, userOptional.get().getPasskey())) {
                setupSession(session, userOptional.get());

                Ruolo ruolo = userOptional.get().getRuolo();
                switch (ruolo) {
                    case studente:
                    case guest:
                        if (userOptional.get().isFirstTime()) {
                            params.put("root", "/auth/signup");
                        } else
                            params.put("root", "/homeStud/");
                        break;
                    case admin:
                        params.put("root", "/homeAdmin/");
                        break;
                    default:
                        params.put("status", "unknownRuolo");
                        return false;
                }
                return true;
            } else {
                params.put("status", "erroreLog");
                return false; // pagina di login con errore

            }
        }

        return false;
    }

    public boolean isLogged(HttpSession session) {
        boolean userLogged = false;

        if (session.getAttribute("usrlog") != null)
            userLogged = (boolean) session.getAttribute("usrlog");

        return userLogged;
    }

    public boolean hasPermission(HttpSession session, Ruolo ruolo) {
        User user = (User) session.getAttribute("user");
        return user.getRuolo().equals(ruolo);
    }
}
