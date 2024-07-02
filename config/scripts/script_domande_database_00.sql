create database antsquests;
use antsquests;

CREATE TABLE `quests_categories`(
    `id_cat` VARCHAR(1) NOT NULL PRIMARY KEY,
    `nome` BIGINT NOT NULL
);

CREATE TABLE `quests`(
    `id_qst` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `categoria_id` VARCHAR(1) NOT NULL,
    `titolo` VARCHAR(50) NOT NULL,
    FOREIGN KEY(`categoria_id`) REFERENCES `quests_categories`(`id_cat`)
);

CREATE TABLE `esiti_quests`(
    `id_est_qst` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `quest_id` BIGINT NOT NULL,
    `data_esecuzione` DATE NOT NULL,
    `punteggio` VARCHAR(7) NOT NULL,
    `tempo` VARCHAR(50) NOT NULL,
    `studente_id` BIGINT NOT NULL,
    FOREIGN KEY(`quest_id`) REFERENCES `quests`(`id_qst`)
);

CREATE TABLE `quests_details`(
    `id_qst_det` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `quest_id` BIGINT NOT NULL,
    `domanda` VARCHAR(50) NOT NULL,
    FOREIGN KEY(`quest_id`) REFERENCES `quests`(`id_qst`)
);

CREATE TABLE `answers_qsts`(
	`id_ans` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `quest_detail_id` BIGINT NOT NULL,
	`risposta` VARCHAR(100) NOT NULL,
    `corretta` VARCHAR(1)  NOT NULL,
    FOREIGN KEY(`quest_detail_id`) REFERENCES `quests_details`(`id_qst_det`)
);
