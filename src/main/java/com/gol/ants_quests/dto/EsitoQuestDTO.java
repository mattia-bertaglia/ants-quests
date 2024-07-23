package com.gol.ants_quests.dto;

import java.sql.Date;

public class EsitoQuestDTO {

    private Long idEstQst;
    private Date dataEsecuzione;
    private String punteggio;
    private String tempo;

    private Long questId;
    private String titolo;
    private String categoria;

    private Long domandaId;
    private String risposta;
    private Boolean corretta;

    public EsitoQuestDTO(Long idEstQst, Date dataEsecuzione, String punteggio, String tempo, Long questId,
            String titolo, String categoria, Long domandaId, String risposta, Boolean corretta) {
        this.idEstQst = idEstQst;
        this.dataEsecuzione = dataEsecuzione;
        this.punteggio = punteggio;
        this.tempo = tempo;
        this.questId = questId;
        this.titolo = titolo;
        this.categoria = categoria;
        this.domandaId = domandaId;
        this.risposta = risposta;
        this.corretta = corretta;
    }

    public Long getIdEstQst() {
        return idEstQst;
    }

    public void setIdEstQst(Long idEstQst) {
        this.idEstQst = idEstQst;
    }

    public Date getDataEsecuzione() {
        return dataEsecuzione;
    }

    public void setDataEsecuzione(Date dataEsecuzione) {
        this.dataEsecuzione = dataEsecuzione;
    }

    public String getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(String punteggio) {
        this.punteggio = punteggio;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getDomandaId() {
        return domandaId;
    }

    public void setDomandaId(Long domandaId) {
        this.domandaId = domandaId;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public Boolean getCorretta() {
        return corretta;
    }

    public void setCorretta(Boolean corretta) {
        this.corretta = corretta;
    }

    @Override
    public String toString() {
        return "EsitoQuestDTO [idEstQst=" + idEstQst + ", dataEsecuzione=" + dataEsecuzione + ", punteggio=" + punteggio
                + ", tempo=" + tempo + ", questId=" + questId + ", titolo=" + titolo + ", categoria=" + categoria
                + ", domandaId=" + domandaId + ", risposta=" + risposta + ", corretta=" + corretta + ", getIdEstQst()="
                + getIdEstQst() + ", getDataEsecuzione()=" + getDataEsecuzione() + ", getPunteggio()=" + getPunteggio()
                + ", getTempo()=" + getTempo() + ", getQuestId()=" + getQuestId() + ", getTitolo()=" + getTitolo()
                + ", getCategoria()=" + getCategoria() + ", getDomandaId()=" + getDomandaId() + ", getRisposta()="
                + getRisposta() + ", getCorretta()=" + getCorretta() + "]";
    }

}
