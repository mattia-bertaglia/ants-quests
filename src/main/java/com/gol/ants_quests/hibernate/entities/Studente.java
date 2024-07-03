package com.gol.ants_quests.hibernate.entities;

import java.sql.Date;
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
@Table(name = "studenti")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Studente extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    // private Integer idStudente;
    private Integer userId; // da eliminare una volta fatta la relazione
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String cap;
    private String provincia;
    private String telefono;
    private String note;
    private Date dataInserimento;
    private Integer corsoId; // da eliminare una volta creata la relazione

    @ToString.Exclude
    @OneToMany(mappedBy = "studente")
    private List<EsitoQuest> esquestionari;
}
