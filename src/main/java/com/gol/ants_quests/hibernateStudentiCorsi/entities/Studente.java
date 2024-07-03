package com.gol.ants_quests.hibernateStudentiCorsi.entities;

import java.sql.Date;
import java.util.List;

import org.springframework.context.annotation.Profile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @Column(name = "corso_id")
    private Integer corsoId;

    /*
     * @ToString.Exclude
     * 
     * @OneToMany(mappedBy = "studente")
     * private List<EsitoQuest> esquestionari;
     * 
     * 
     * @ManyToOne(fetch = FetchType.EAGER)
     * 
     * @JoinColumn(name = "cors_id")
     * private Studente studente;
     * 
     * @OneToOne(cascade = CascadeType.ALL)
     * 
     * @JoinColumn(name = "user_id", referencedColumnName = "id")
     * private User user;
     */

}
