package com.gol.ants_quests.hibernate.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "esiti_quests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class  EsitoQuest extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstQst;
    private Date dataEsecuzione;
    private String punteggio;
    private String tempo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "studente_id")
    private Studente studente;
 
    
    
}
