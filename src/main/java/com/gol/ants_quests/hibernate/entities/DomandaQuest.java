package com.gol.ants_quests.hibernate.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "quests_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class  DomandaQuest extends GenericEntity {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQstDet;
   //private Integer questId;
    private String domanda;


     @ManyToOne
     @JoinColumn(name = "quest_id")
     private Quest dom ;

    @OneToMany(mappedBy = "domandaQuest") 
    private List<RispostaQuest> risp;

    

    
}