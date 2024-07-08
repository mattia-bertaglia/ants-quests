package com.gol.ants_quests.hibernate.services;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;
import com.gol.ants_quests.services.ErrorService;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;

@Service
public class UserHibService extends GenericHibService<User, Integer, UserRepository> {

    // TODO: TOGLIERE
    private final UserRepository userRepository;
    private final ErrorService errorService;

    // RIMANE SOLO super(userRepository)
    public UserHibService(UserRepository userRepository, ErrorService errorService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.errorService = errorService;
    }

    public User findByUsernameEmail(String usernameEmail) {
        Optional<User> userOptional = userRepository.findByUsernameEmail(usernameEmail);
        return userOptional.orElse(null);
    }

    // TODO: logica di business da fare in authservice
    public boolean validateCredentials(String usernameEmail, String passkey) {
        User user = findByUsernameEmail(usernameEmail);
        return user != null && user.getPasskey().equals(passkey);
    }

    // TODO: logica di business da fare in authservice
    public String checkRole(String usernameEmail, HttpSession session) {
        User user = findByUsernameEmail(usernameEmail);
        if (user != null) {
            Ruolo ruolo = user.getRuolo();
            if (ruolo == Ruolo.studente || ruolo == Ruolo.guest) {
                return "redirect:/homeStud";
            } else if (ruolo == Ruolo.admin) {
                return "redirect:/homeAdmin";
            } else {
                errorService.getToast(session, "unknownRuolo");
                return "redirect:/";
            }
        }
        return null;
    }

    // TODO: logica di business da fare in authservice
    public User registerUser(HashMap<String, String> params, Model model) {
        String email = params.get("usernameEmail");
        String password = params.get("passkey");

        if (email == null || password == null || findByUsernameEmail(email) != null) {
            errorService.getToast(model, "registrationError");
            return null;
        }

        User user = new User();
        user.setUsernameEmail(email);
        user.setPasskey(password);
        user.setRuolo(Ruolo.guest);
        user.setEnabled(false);

        return userRepository.save(user);
    }

    // TODO: logica di business da fare in authservice
    public void setupSession(HttpSession session, User user) {
        session.setAttribute("usrlog", true);
        session.setAttribute("usernameEmail", user.getUsernameEmail());
    }

    // TODO: logica di business da fare in authservice
    public String logInUser(HashMap<String, String> params, HttpSession session, Model model) {
        String usernameEmail = params.get("usernameEmail");
        String passkey = params.get("passkey");

        if (validateCredentials(usernameEmail, passkey)) {
            User user = findByUsernameEmail(usernameEmail);
            setupSession(session, user);
            return checkRole(usernameEmail, session);
        } else {
            if (findByUsernameEmail(usernameEmail) == null) {
                errorService.getToast(session, "erroreLog");
            } else {
                errorService.getToast(session, "passwordMismatch");
            }
            return "redirect:/";
        }
    }
}
