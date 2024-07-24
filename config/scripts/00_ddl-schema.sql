create database antsquests;

create table antsquests.users (
    id_usr bigint primary key auto_increment,
    username_email varchar(100) not null unique,
    passkey varchar(255) not null,
    ruolo enum('guest', 'studente', 'admin') default 'guest',
    first_time boolean default true
);
-- passkey: admin123
insert into
    antsquests.users (
        username_email,
        passkey,
        ruolo
    )
values (
        'admin',
        '$2a$12$WWV4Y6L/YqwmS3SE6O9qSeABbPIbJOrs6SYihJY9Zf8FC5gW27Zwe',
        'admin'
    );

create table antsquests.quests_categories (
    id_cat varchar(1) primary key,
    nome varchar(50) not null
);

insert into antsquests.quests_categories values ('I', 'Ingresso');

insert into antsquests.quests_categories values ('C', 'Corso');

create table antsquests.quests (
    id_qst bigint primary key auto_increment,
    categoria_id varchar(1) not null,
    titolo varchar(50) not null,
    attivo tinyint not null default true,
    foreign key (categoria_id) references antsquests.quests_categories (id_cat)
);

create table antsquests.quests_details (
    id_qst_det bigint primary key auto_increment,
    quest_id bigint not null,
    domanda varchar(50) not null,
    foreign key (quest_id) references antsquests.quests (id_qst)
);

create table antsquests.answers_qsts (
    id_ans bigint primary key auto_increment,
    quest_detail_id bigint not null,
    risposta varchar(100) not null,
    corretta tinyint not null default false,
    foreign key (quest_detail_id) references antsquests.quests_details (id_qst_det) on delete cascade
);

create table antsquests.corsi (
    id_corso bigint primary key auto_increment,
    nome varchar(100) not null,
    data_inizio date,
    data_fine date
);

create table antsquests.studenti (
    id_studente bigint primary key auto_increment,
    user_id bigint not null,
    nome varchar(100),
    cognome varchar(100),
    data_nascita date,
    cap varchar(5),
    provincia varchar(2),
    telefono varchar(15),
    note varchar(5000),
    data_inserimento date default(current_date()),
    corso_id bigint,
    foreign key (corso_id) references antsquests.corsi (id_corso)
);

create table antsquests.esiti_quests (
    id_est_qst bigint primary key auto_increment,
    quest_id bigint not null,
    categoria_quest varchar(50) not null,
    titolo_quest varchar(50) not null,
    studente_id bigint not null,
    data_esecuzione timestamp not null,
    punteggio varchar(7) not null,
    tempo varchar(50) not null,
    path_pdf varchar(100) not null,
    foreign key (quest_id) references antsquests.quests (id_qst),
    foreign key (studente_id) references antsquests.studenti (id_studente)
);