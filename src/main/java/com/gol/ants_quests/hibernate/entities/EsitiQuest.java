package com.gol.ants_quests.hibernate.entities;

import java.sql.Date;

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
public class  EsitiQuest extends GenericEntity {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstQst;
    private Integer questId;
    private Date dataEsecuzione;
    private String punteggio;
    private String tempo;
    private Integer studenteId; // foreign key








    
}