drop database antsquests;
create database antsquests;
use antsquests;

CREATE TABLE `quests_categories`(
    `id_cat` VARCHAR(1) NOT NULL PRIMARY KEY,
    `nome` VARCHAR(50) NOT NULL
);

CREATE TABLE `quests`(
    `id_qst` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `categoria_id` VARCHAR(1) NOT NULL,
    `titolo` VARCHAR(50) NOT NULL,
    FOREIGN KEY(`categoria_id`) REFERENCES `quests_categories`(`id_cat`)
);

drop table esiti_quests;
CREATE TABLE `esiti_quests`(
    `id_est_qst` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `quest_id` BIGINT NOT NULL,
    `data_esecuzione` DATE NOT NULL,
    `punteggio` VARCHAR(7) NOT NULL,
    `tempo` VARCHAR(50) NOT NULL,
    `studente_id` BIGINT NOT NULL,
    FOREIGN KEY(`quest_id`) REFERENCES `quests`(`id_qst`),
     FOREIGN KEY(`studente_id`) REFERENCES `studenti`(`id_stud`)
);


CREATE TABLE `quests_details`(
    `id_qst_det` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `quest_id` BIGINT NOT NULL,
    `domanda` VARCHAR(50) NOT NULL,
    FOREIGN KEY(`quest_id`) REFERENCES `quests`(`id_qst`)
);

drop TABLE `studenti`;
CREATE TABLE `studenti`(
    `id_stud` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `nome`VARCHAR(100),
    `cognome` VARCHAR(100),
    `data_nascita` date NOT NULL,
    `course_id` BIGINT NOT NULL
);

