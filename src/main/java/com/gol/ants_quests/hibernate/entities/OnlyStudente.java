package com.gol.ants_quests.hibernate.entities;

import java.sql.Date;

import jakarta.persistence.Column;
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
@Table(name = "studenti")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OnlyStudente extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_studente")
    private Integer idStudente;

    @Column(name = "user_id")
    private Integer userId;

    private String nome;
    private String cognome;

    @Column(name = "data_nascita")
    private Date dataNascita;

    private String cap;
    private String provincia;
    private String telefono;
    private String note;
    private Date dataInserimento;
    private int corso_id;

}
