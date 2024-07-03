package com.gol.ants_quests.hibernate.entities;

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
@Table(name = "answers_qsts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class  RispostaQuest extends GenericEntity {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAns;
    // private Integer questDetailId;
    private String risposta;
    private String corretta;


    @ManyToOne
    @JoinColumn(name = "quest_detail_id")
    private DomandaQuest domandaQuest;

    
}