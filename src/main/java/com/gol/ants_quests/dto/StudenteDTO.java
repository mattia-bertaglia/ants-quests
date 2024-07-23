package com.gol.ants_quests.dto;

import java.util.Date;

import com.gol.ants_quests.util.Ruolo;

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

    public StudenteDTO(Long idStudente, String nome, String cognome, Date dataNascita, String cap, String provincia,
            String telefono, String note, Date dataInserimento, Long userId, String usernameEmail, String passkey,
            String ruolo, boolean firstTime, Long corsoId, String nomeCorso, Date dataInizio, Date dataFine) {
        this.idStudente = idStudente;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cap = cap;
        this.provincia = provincia;
        this.telefono = telefono;
        this.note = note;
        this.dataInserimento = dataInserimento;
        this.userId = userId;
        this.usernameEmail = usernameEmail;
        this.passkey = passkey;
        this.ruolo = ruolo;
        this.firstTime = firstTime;
        this.corsoId = corsoId;
        this.nomeCorso = nomeCorso;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Long getIdStudente() {
        return idStudente;
    }

    public void setIdStudente(Long idStudente) {
        this.idStudente = idStudente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsernameEmail() {
        return usernameEmail;
    }

    public void setUsernameEmail(String usernameEmail) {
        this.usernameEmail = usernameEmail;
    }

    public String getPasskey() {
        return passkey;
    }

    public void setPasskey(String passkey) {
        this.passkey = passkey;
    }

    public String getRuolo() {
        return ruolo;
    }

    public Ruolo setRuolo(Ruolo ruolo) {
        return ruolo;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public Long getCorsoId() {
        return corsoId;
    }

    public void setCorsoId(Long corsoId) {
        this.corsoId = corsoId;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "StudenteDTO [idStudente=" + idStudente + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
                + dataNascita + ", cap=" + cap + ", provincia=" + provincia + ", telefono=" + telefono + ", note="
                + note + ", dataInserimento=" + dataInserimento + ", userId=" + userId + ", usernameEmail="
                + usernameEmail + ", passkey=" + passkey + ", ruolo=" + ruolo + ", firstTime=" + firstTime
                + ", corsoId=" + corsoId + ", nomeCorso=" + nomeCorso + ", dataInizio=" + dataInizio + ", dataFine="
                + dataFine + "]";
    }

}
