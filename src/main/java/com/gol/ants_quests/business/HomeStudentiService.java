package com.gol.ants_quests.business;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gol.ants_quests.hibernate.entities.EsitoQuest;
import com.gol.ants_quests.hibernate.entities.OnlyStudente;
import com.gol.ants_quests.hibernate.entities.Quest;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.entities.User;
import com.gol.ants_quests.hibernate.services.CategorieHibService;
import com.gol.ants_quests.hibernate.services.EsitiHibService;
import com.gol.ants_quests.hibernate.services.QuestsHibService;
import com.gol.ants_quests.hibernate.services.RisposteHibService;
import com.gol.ants_quests.hibernate.services.StudentiHibService;
import com.gol.ants_quests.hibernate.services.UsersHibService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeStudentiService {

    private final EsitiHibService esitiSrv;
    private final CategorieHibService categorieHibSrv;
    private final QuestsHibService questSrv;
    private final RisposteHibService risposteSrv;
    private final UsersHibService authsrv;
    private final StudentiHibService studHibSrv;
    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    private final PdfService pdfSrv;
    private final ErrorService errorService;

    public void findAllEsitiQuest(Model model) {
        model.addAttribute("listaEsitiQuestionari", esitiSrv.findAll());
    }

    public void findAllQuestByCategoria(Model model) {
        model.addAttribute("listaQuestionari", questSrv.findAll());
    }

    public void findByData(Model model, String data_inizio, String data_fine) {
        model.addAttribute("listaEsitiQuestionari",
                esitiSrv.findData(LocalDate.parse(data_inizio), LocalDate.parse(data_fine)));
    }

    public void openHomeStud(Model model, Long idStudente) {
        log.info("Caricamento Categorie e Questionari");
        model.addAttribute("questionari", categorieHibSrv.findAll(Sort.by(Direction.DESC, "nome")));
        log.info("Caricamento esiti Studente: " + idStudente);
        model.addAttribute("esiti", esitiSrv.findByStudente(idStudente));
    }

    /* metodo per la modifica dello studente dal suo profilo */
    public void modificaProfilo(HttpSession session, HashMap<String, String> params, Model model) {
        log.info("Inizio modifica profilo");
        String email = params.get("usernameEmail");
        String password = params.get("passkey");
        log.info("Email: " + email + ", Password: " + password);

        if (email == null || password == null || !authsrv.userExists(email)) {
            errorService.addErrorMessageToModel(model, "registrationError");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            errorService.addErrorMessageToModel(model, "userNotLoggedInError");
            return;
        }

        user.setPasskey(bcrypt.encode(password));
        user.setFirstTime(false);

        User salvatoUser = authsrv.save(user);
        if (salvatoUser == null || salvatoUser.getId() == null) {
            errorService.addErrorMessageToModel(model, "registrationError");
            return;
        }

        Studente studenteTemp = salvatoUser.getStudente();
        if (studenteTemp == null) {
            errorService.addErrorMessageToModel(model, "studentNotFoundError");
            return;
        }

        aggiornaDettagliStudente(studenteTemp, params);

        salvatoUser.setStudente(studHibSrv.save(studenteTemp));
        if (salvatoUser.getStudente() == null) {
            errorService.addErrorMessageToModel(model, "studentUpdateError");
            return;
        }

        errorService.addSuccessMessageToModel(model, "registrationSuccess");
        log.info("Profilo modificato con successo");
    }

    private void aggiornaDettagliStudente(Studente studente, HashMap<String, String> params) {
        studente.setNome(params.get("nome"));
        studente.setCognome(params.get("cognome"));
        studente.setDataNascita(Date.valueOf(params.get("dataNascita")));
        studente.setCap(params.get("cap"));
        studente.setProvincia(params.get("provincia"));
        studente.setTelefono(params.get("telefono"));
        studente.setNote(params.get("note"));
    }

    public void doQuestionario(Model model, Long idQuest) {
        log.info("Caricamento Questionario=" + idQuest);
        model.addAttribute("quest", questSrv.findByID(idQuest).get());
    }

    public void elaborazioneQuestionario(User user, HashMap<String, String> params) {
        log.info("Start Elaborazione Questionario, Studente=" + user.getUsernameEmail());
        Optional<Quest> quest = questSrv.findByID(Long.parseLong(params.get("id-quest")));

        int contRisposteCorrette = 0;
        HashMap<Long, Boolean> mapRisposteDate = new HashMap<Long, Boolean>();
        checkRisposte(contRisposteCorrette, mapRisposteDate, params);

        EsitoQuest esitoFinale = new EsitoQuest();
        esitoFinale.setDataEsecuzione(Timestamp.valueOf(LocalDateTime.now()));
        esitoFinale.setPunteggio(contRisposteCorrette + "/" + quest.get().getDomanda().size());
        esitoFinale.setTempo(params.get("tempo-quest"));

        esitoFinale.setQuest(quest.get());
        // TODO: salvare anche categoria e titolo questionario in esito
        esitoFinale.setStudente(
                new OnlyStudente(user.getStudente().getIdStudente(), user, null, null, null, null, null,
                        null, null, null, null));

        // elaborazione PDF
        String dataEsecuzione = new SimpleDateFormat("yyyy-MM-dd-hh-mm").format(esitoFinale.getDataEsecuzione());
        String studDir = "/" + user.getStudente().getIdStudente() + "-" + user.getStudente().getCognome()
                + user.getStudente().getNome();
        String fileName = "/" + quest.get().getTitolo() + "_" + dataEsecuzione + ".pdf";

        pdfSrv.generatePdfiText(quest.get(), mapRisposteDate, studDir, fileName, user.getStudente().getCognome() + " "
                + user.getStudente().getNome(), dataEsecuzione, quest.get().getTitolo());

        esitoFinale.setPathPdf(studDir + fileName);

        esitiSrv.save(esitoFinale);

        log.info("End Elaborazione Questionario, Studente=" + user.getUsernameEmail());

    }

    /**
     * Logica Controllo risposte corrette, con query su db.
     * Se presente per RispostaQuest.idRisposta e RispostaQuest.correta allora
     * risposta esatta
     * 
     * @param contRisposteCorrette, contatore per risposte corrette totali
     * @param mapRisposteDate,      mappa di risposte date dallo studente,
     *                              <idRisposta, true/false>
     * @param params
     */
    private final void checkRisposte(int contRisposteCorrette, HashMap<Long, Boolean> mapRisposteDate,
            HashMap<String, String> params) {
        log.info("Controllo Risposte Date");
        boolean isPresent;
        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            isPresent = false;
            if (entry.getValue().startsWith("ans")) {

                if (isPresent = risposteSrv.findRispostaCorrettaById(Long.parseLong(entry.getValue().split("-")[1]))
                        .isPresent())
                    contRisposteCorrette++;
                mapRisposteDate.put(Long.parseLong(entry.getValue().split("-")[1]), isPresent);
            }
        }

        log.info("Contatore Risposte Corrette: " + contRisposteCorrette);
        log.info("Esito Mappa Risposta Date=" + mapRisposteDate.toString());
    }

}
