package com.gol.ants_quests.hibernate.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class OnlyEsitoQuest extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstQst;
    private Timestamp dataEsecuzione;
    private String punteggio;
    private String tempo;
    private String pathPdf;

    @Column(name = "quest_id")
    private Long questId;
    private String categoriaQuest;
    private String titoloQuest;

    @Column(name = "studente_id")
    private Long studenteId;

}
