package com.gol.ants_quests.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.repositories.UserRepository;
import com.gol.ants_quests.services.ErrorService;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserHibService extends GenericHibService<User, Integer, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ErrorService errorService;

    public UserHibService(UserRepository repository) {
        super(repository);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsernameEmail(String usernameEmail) {
        return userRepository.findByUsernameEmail(usernameEmail);
    }

    public List<User> getUsersByRuolo(Ruolo ruolo) {
        return userRepository.findByRuolo(ruolo);
    }

    public List<User> getEnabledUsers() {
        return userRepository.findByEnabled(true);
    }

    public boolean updateUserEnabledStatus(String usernameEmail, boolean enabled) {
        Optional<User> user = userRepository.findByUsernameEmail(usernameEmail);
        if (user.isPresent()) {
            user.get().setEnabled(enabled);
            userRepository.save(user.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean userExists(String email) {
        return userRepository.findByUsernameEmail(email) != null;
    }

    public String firstTimeUser(HashMap<String, String> userData, Model model) {
        // Logica per salvare l'utente nel database
        String email = userData.get("email");
        String password = userData.get("password");

        if (email == null || password == null) {
            model.addAttribute("error", "Tutti i campi sono obbligatori.");
            return "/firstTime";
        }

        User user = new User();
        user.setUsernameEmail(email);
        user.setPasskey(password);

        userRepository.save(user);

        return "redirect:/?status=signOK";
    }

    public String logInUser(@RequestParam HashMap<String, String> params, HttpSession session, Model model) {
        if (params.containsKey("usernameEmail") && params.containsKey("passkey")) {
            String usernameEmail = params.get("usernameEmail");
            String passkey = params.get("passkey");

            Optional<User> userOptional = userRepository.findByUsernameEmail(usernameEmail);
            if (userOptional.isPresent() && userOptional.get().getPasskey().equals(passkey)) {
                User user = userOptional.get();
                session.setAttribute("usrlog", true);
                session.setAttribute("usernameEmail", user.getUsernameEmail());

                String ruolo = user.getRuolo().toString();
                switch (ruolo) {
                    case "studente":
                        return "redirect:/homeStud";
                    case "guest":
                        return "redirect:/homeStud";
                    case "admin":
                        return "redirect:/homeAdmin";
                    default:
                        params.put("status", "unknownRuolo");
                        errorService.getToast(model, params);
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
