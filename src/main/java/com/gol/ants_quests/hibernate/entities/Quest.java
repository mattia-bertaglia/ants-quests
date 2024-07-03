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
@Table(name = "quests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class  Quest extends GenericEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQst;
    private String titolo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaQuest categoriequest;

    
    @OneToMany(mappedBy = "quest") 
    private List<EsitoQuest> esquestionari;
 
    @ToString.Exclude
    @OneToMany(mappedBy = "dom") 
    private List<DomandaQuest> domanda; 

}
