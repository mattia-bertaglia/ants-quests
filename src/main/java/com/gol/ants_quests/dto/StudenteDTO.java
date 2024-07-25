package com.gol.ants_quests.dto;

import java.util.Date;
import java.util.List;

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
    private Date dataInserimento;

    private Long userId;
    private String usernameEmail;
    private String passkey;
    private String ruolo;
    private boolean firstTime;

    private Long corsoId;
    private String nomeCorso;
    private Date dataInizio;
    private Date dataFine;

    private List<EsitoQuestDTO> esquestionari;

}