create database ants_quests;
CREATE TABLE users (
    id_usr INT AUTO_INCREMENT PRIMARY KEY,
    username_email VARCHAR(100) NOT NULL UNIQUE,
    passkey VARCHAR(255) NOT NULL,
    ruolo ENUM('guest', 'studente', 'admin') not null DEFAULT 'guest',
    enabled BOOLEAN DEFAULT false
);