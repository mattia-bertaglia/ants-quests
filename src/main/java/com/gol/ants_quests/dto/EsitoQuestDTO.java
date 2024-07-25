package com.gol.ants_quests.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsitoQuestDTO {

    private Long idEstQst;
    private Date dataEsecuzione;
    private String punteggio;
    private String tempo;

    private Long questId;
    private String titoloQuest;
    private String categoriaQuest;

    private Long studenteId;

    private Long domandaId;
    private String risposta;
    private Boolean corretta;

}
