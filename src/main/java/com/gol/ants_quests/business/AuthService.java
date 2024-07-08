package com.gol.ants_quests.business;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final ErrorService errorService;

    public Optional<User> findByUsernameEmail(String usernameEmail) {
        return userRepository.findByUsernameEmail(usernameEmail);
    }

    public boolean validateCredentials(String usernameEmail, String passkey) {
        Optional<User> userOptional = findByUsernameEmail(usernameEmail);
        return userOptional.isPresent() && userOptional.get().getPasskey().equals(passkey);
    }

    public String checkRole(String usernameEmail, HttpSession session) {
        Optional<User> userOptional = userRepository.findByUsernameEmail(usernameEmail);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            switch (user.getRuolo()) {
                case studente:
                case guest:
                    return "redirect:/homeStud";
                case admin:
                    return "redirect:/homeAdmin";
                default:
                    errorService.getToast(session, "unknownRuolo");
                    return "redirect:/";
            }
        } else {
            return null;
        }
    }

    public User registerUser(HashMap<String, String> userData, Model model) {
        String email = userData.get("usernameEmail");
        String password = userData.get("passkey");

        if (email == null || password == null || userExists(email)) {
            errorService.getToast(model, "registrationError");
            return null;
        }

        User user = new User();
        user.setUsernameEmail(email);
        user.setPasskey(bcrypt.encode(password));
        user.setRuolo(Ruolo.guest);
        user.setEnabled(false);

        return userRepository.save(user);
    }

    public boolean userExists(String usernameEmail) {
        return userRepository.findByUsernameEmail(usernameEmail).isPresent();
    }

    public void setupSession(HttpSession session, User user) {
        session.setAttribute("usrlog", true);
        session.setAttribute("usernameEmail", user.getUsernameEmail());
    }

    public void handleError(Model model, String errorMessage) {
        model.addAttribute("error", errorMessage);
    }

    public String logInUser(HashMap<String, String> params, HttpSession session, Model model) {
        if (params.containsKey("usernameEmail") && params.containsKey("passkey")) {
            String usernameEmail = params.get("usernameEmail");
            String passkey = params.get("passkey");

            Optional<User> userOptional = userRepository.findByUsernameEmail(usernameEmail);
            if (userOptional.isPresent() && bcrypt.matches(passkey, userOptional.get().getPasskey())) {
                User user = userOptional.get();
                session.setAttribute("usrlog", true);
                session.setAttribute("usernameEmail", user.getUsernameEmail());

                String ruolo = user.getRuolo().toString();
                switch (ruolo) {
                    case "studente":
                    case "guest":
                        return "redirect:/homeStud";
                    case "admin":
                        return "redirect:/homeAdmin";
                    default:
                        params.put("status", "unknownRuolo");
                        errorService.getToast(model, "unknownRuolo");
                        return "redirect:/";
                }
            } else {
                model.addAttribute("error", "Username o password non validi.");
                return "login"; // pagina di login con errore
            }
        }

        return "redirect:/";
    }
}
