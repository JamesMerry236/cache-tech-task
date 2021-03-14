--liquibase formatted sql
--changeset James:update-table-id-generation splitStatements:true endDelimiter:;
create sequence users_table_id_seq;
create sequence answers_table_id_seq;
alter table users alter column id set default nextval('users_table_id_seq');
alter table answers alter column id set default nextval('answers_table_id_seq');