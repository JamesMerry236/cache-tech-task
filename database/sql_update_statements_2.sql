--liquibase formatted sql
--changeset James:update-user-table-pkey splitStatements:true endDelimiter:;
alter table users add constraint username_is_unique unique(username);