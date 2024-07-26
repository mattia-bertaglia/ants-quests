package com.gol.ants_quests.dto;

import java.sql.Date;

import com.gol.ants_quests.hibernate.entities.OnlyEsitoQuest;

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
    private String pathPdf;

    private Long questId;
    private String titoloQuest;
    private String categoriaQuest;

    public EsitoQuestDTO convertDto(OnlyEsitoQuest esito) {
        this.setIdEstQst(esito.getIdEstQst());
        this.setDataEsecuzione(esito.getDataEsecuzione());
        this.setPunteggio(esito.getPunteggio());
        this.setTempo(esito.getTempo());
        this.setPathPdf(esito.getPathPdf());
        this.setQuestId(esito.getQuestId());
        this.setCategoriaQuest(esito.getCategoriaQuest());
        this.setTitoloQuest(esito.getTitoloQuest());
        return this;
    }

}
