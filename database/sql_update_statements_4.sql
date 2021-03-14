--liquibase formatted sql
--changeset James:update-answer-table-question_foreign_key splitStatements:true endDelimiter:;
alter table answers drop constraint questions_is_a_question;
alter table answers add constraint questions_is_a_question foreign key (question_id) references questions(id);