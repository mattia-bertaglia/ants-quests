package com.gol.ants_quests.hibernate.entities;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "categoriequest")
    private List<Quest> questionari;
}
