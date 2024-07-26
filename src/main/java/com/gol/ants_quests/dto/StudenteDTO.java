package com.gol.ants_quests.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gol.ants_quests.hibernate.entities.OnlyCorso;
import com.gol.ants_quests.hibernate.entities.OnlyEsitoQuest;
import com.gol.ants_quests.hibernate.entities.Studente;
import com.gol.ants_quests.hibernate.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudenteDTO {

    private Long idStudente;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String cap;
    private String provincia;
    private String telefono;
    private String note;

    private Long userId;
    private String usernameEmail;
    private String passkey;
    private String ruolo;

    private Long corsoId;
    private String nomeCorso;

    private List<EsitoQuestDTO> esquestionari;

    public StudenteDTO convertDto(Studente stud) {
        this.setIdStudente(stud.getIdStudente());
        this.setNome(stud.getNome());
        this.setCognome(stud.getCognome());
        this.setDataNascita(stud.getDataNascita());
        this.setCap(stud.getCap());
        this.setProvincia(stud.getProvincia());
        this.setTelefono(stud.getTelefono());
        this.setNote(stud.getNote());

        if (stud.getUser() != null) {
            User user = stud.getUser();
            this.setUserId(user.getId());
            this.setUsernameEmail(user.getUsernameEmail() != null ? user.getUsernameEmail() : "");
            this.setPasskey(user.getPasskey() != null ? user.getPasskey() : "");
            this.setRuolo(user.getRuolo() != null ? user.getRuolo().toString() : "");
        }

        if (stud.getCorso() != null) {
            OnlyCorso corso = stud.getCorso();
            this.setCorsoId(corso.getIdCorso());
            this.setNomeCorso(corso.getNome() != null ? corso.getNome() : "");
        }

        this.esquestionari = new ArrayList<EsitoQuestDTO>();
        if (stud.getEsquestionari() != null && !stud.getEsquestionari().isEmpty())
            for (OnlyEsitoQuest esito : stud.getEsquestionari())
                this.esquestionari.add(new EsitoQuestDTO().convertDto(esito));

        return this;
    }

}