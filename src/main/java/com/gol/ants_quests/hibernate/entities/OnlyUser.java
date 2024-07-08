package com.gol.ants_quests.hibernate.entities;

import com.gol.ants_quests.util.Ruolo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OnlyUser extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usr")
    private int id;

    @Column(name = "username_email", nullable = false, unique = true)
    private String usernameEmail;

    @Column(name = "passkey", nullable = false)
    private String passkey;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo = Ruolo.guest;

    @Column(name = "first_time", nullable = false, columnDefinition = "boolean default true")
    private boolean firstTime;

}
