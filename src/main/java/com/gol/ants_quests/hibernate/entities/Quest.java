package com.gol.ants_quests.hibernate.entities;

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
@Table(name = "quests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class  Quest extends GenericEntity {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQst;
    private String categoriaId;
    private String titolo;





    
}
