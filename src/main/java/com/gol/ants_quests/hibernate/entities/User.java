package com.gol.ants_quests.hibernate.entities;

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
public class User extends GenericEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usr")
    private int id;

    @Column(name = "username_email", nullable = false, unique = true)
    private String usernameEmail;

    @Column(name = "passkey", nullable = false)
    private String passkey;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('guest', 'studente', 'admin') default 'guest'")
    private Role role = Role.GUEST;

    @Column(name = "enable", nullable = false, columnDefinition = "BOOLEAN default false")
    private boolean enable = false;

    public enum Role {
        GUEST("guest"),
        STUDENTE("studente"),
        ADMIN("admin");

        private String value;

        Role(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}