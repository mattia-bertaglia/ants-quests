package com.gol.ants_quests.hibernateStudentiCorsi.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "studenti")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Studente extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_studente")
    private Integer idStudente;
    @Column(name = "user_id")
    private Integer userId;
    private String nome;
    private String cognome;
    @Column(name = "data_nascita")
    private Date dataNascita;
    private String cap;
    private String provincia;
    private String telefono;
    private String note;
    private Date dataInserimento;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private OnlyCorso corso;

    /*
     * @OneToMany(mappedBy = "studente")
     * private List<EsitoQuest> esquestionari;
     * 
     * @OneToOne(cascade = CascadeType.ALL)
     * 
     * @JoinColumn(name = "user_id", referencedColumnName = "id")
     * private User user;
     * 
     */

    /*
     * @ManyToOne(fetch = FetchType.EAGER)
     * 
     * @JoinColumn(name = "cors_id")
     * private Studente studente;
     */

}