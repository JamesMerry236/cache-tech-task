--liquibase formatted sql
--changeset James:update-user-table-pkey splitStatements:true endDelimiter:;
alter table answers add column survey_attempt bigint not null;