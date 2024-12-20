--liquibase formatted sql

--changeset Java:1
--comment create user-data table
CREATE TABLE public.user_data (
    id int8 NOT NULL,
    "action" varchar(255) NULL,
    insert_date timestamp NULL,
    item_id int4 NULL,
    region_id int4 NULL,
    session_id varchar(255) NOT NULL,
    CONSTRAINT user_data_pkey PRIMARY KEY (id)
);
CREATE SEQUENCE public.user_data_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
--rollback truncate table user_data;

--changeset Java:2
--comment create search_data table
CREATE TABLE public.search_data (
    id int8 NOT NULL,
    insert_date timestamp NULL,
    item_id int4 NULL,
    query varchar(255) NULL,
    region_id int4 NULL,
    session_id varchar(255) NOT NULL,
    CONSTRAINT search_data_pkey PRIMARY KEY (id)
);
CREATE SEQUENCE public.search_data_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
--rollback truncate table search_data;
