package com.gol.ants_quests.hibernateStudentiCorsi.entities;




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
@Table(name = "corsi")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Corso extends GenericEntity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer idCorso;
    private String nome;
    private Date dataInizio;
    private Date dataFine;

/* @ToString.Exclude
     @OneToMany(mappedBy = ("corso"))
     private List<Studente> stud; */
     
}
