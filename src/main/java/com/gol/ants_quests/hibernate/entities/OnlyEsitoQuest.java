package com.gol.ants_quests.hibernate.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
    private Date dataEsecuzione;
    private String punteggio;
    private String tempo;

    /* TODO: nel salvataggio esito aggiungere questi 2 campi */

    /* aggiungere colonne database */

    /* @Transient */
    private String categoriaQuest;
    /* @Transient */
    private String titoloQuest;

    @Column(name = "quest_id")
    private Long questId;

    @Column(name = "studente_id")
    private Long studenteId;

}
