package com.gol.ants_quests.hibernate.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quests_categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class  CategoriaQuest extends GenericEntity {
    
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idCat;
    private String nome;

    
    @OneToMany(mappedBy = "categoriequest", fetch = FetchType.EAGER)
    private List<Quest> questionari;
}
