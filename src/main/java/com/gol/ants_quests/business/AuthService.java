package com.gol.ants_quests.business;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.OnlyStudente;
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

    public void registerUser(HttpSession session, HashMap<String, String> params, Model model) {
        String email = params.get("usernameEmail");
        String password = params.get("passkey");

        if (email == null || password == null || userExists(email)) {
            errorService.getToast(model, "registrationError");
            return;
        }

        User user = new User();
        user.setUsernameEmail(email);
        user.setPasskey(bcrypt.encode(password));
        user.setRuolo(Ruolo.guest);
        user.setFirstTime(false);

        // popolare dati studente da form di firstTime.html
        user.setStudente(new OnlyStudente(null, null, null, null, null, null, null, null, null, null, 0));

        setupSession(session, userRepository.save(user));
    }

    public boolean userExists(String usernameEmail) {
        return userRepository.findByUsernameEmail(usernameEmail).isPresent();
    }

    public void setupSession(HttpSession session, User user) {
        session.setAttribute("usrlog", true);
        session.setAttribute("user", user);
    }

    public String logInUser(HashMap<String, String> params, HttpSession session, Model model) {
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
                        return "redirect:/studenti/";
                    case admin:
                        return "redirect:/admin/";
                    default:
                        params.put("status", "unknownRuolo");
                        errorService.getToast(model, "unknownRuolo");
                        return "redirect:/";
                }
            } else {
                model.addAttribute("error", "Username o password non validi.");
                return "redirect:/"; // pagina di login con errore
            }
        }

        return "redirect:/";
    }
}
