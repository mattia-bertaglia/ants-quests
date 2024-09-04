package com.gol.ants_quests.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.dto.StudenteDTO;
import com.gol.ants_quests.hibernate.entities.Corso;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.services.CorsiHibService;
import com.gol.ants_quests.hibernate.services.EsitiHibService;
import com.gol.ants_quests.hibernate.services.StudentiHibService;
import com.gol.ants_quests.util.Ruolo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final StudentiHibService studHibSrv;
    private final EsitiHibService esitiHibSrv;
    private final CorsiHibService corsiHibSrv;
    private final AuthService authService;

    public void openHomeAdmin(HttpSession session, Model model) {
        // Verifica se l'utente è loggato
        if (!authService.isLogged(session)) {
            // Reindirizza alla pagina di login o gestisci l'accesso negato
            log.warn("Accesso negato: l'utente non è loggato.");
            // Esegui l'azione appropriata, ad esempio reindirizzare a una pagina di login
            return;
        }

        // Verifica se l'utente ha il permesso di accedere all'area admin
        if (!authService.hasPermission(session, Ruolo.admin)) {
            // Gestisci il caso in cui l'utente non abbia i permessi necessari
            log.warn("Accesso negato: l'utente non ha i permessi di amministratore.");
            // Esegui l'azione appropriata, ad esempio reindirizzare a una pagina di errore
            return;
        }

        log.info("Caricamento Ultimi Studenti ...");
        List<Studente> studenti = studHibSrv.findLastStuds();
        List<Corso> corsi = corsiHibSrv.findAll();
        List<StudenteDTO> result = new ArrayList<StudenteDTO>();
        for (Studente stud : studenti) {
            result.add(new StudenteDTO().convertDto(stud));
        }

        model.addAttribute("studenti", result);
        model.addAttribute("corsi", corsi);

        log.info("Caricamento Ultimi Questioanari ...");
        model.addAttribute("listaEsitiQuestionari", esitiHibSrv.findLastEsiti());

    }

}
