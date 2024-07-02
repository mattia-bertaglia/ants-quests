package com.gol.ants_quests.hibernateStudentiCorsi.entities;

import org.hibernate.mapping.List;

import jakarta.persistence.Column;
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

@Entity
@Table(name = "corsi")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Corso extends GenericEntity {
    /*id_corso INT,
    nome varchar(100),
	data_inizio DATE,
	data_fine DATE */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_corso")
    private Integer idCorso;
    private String nome;

    @Column(name ="data_inizio")
    private Data dataInizio;
    @Column(name = "data_fine")
    private Data dataFine;
   /*  @OneToMany(mappedBy = "corso")
    private List<Studente> studenti;*/
}
