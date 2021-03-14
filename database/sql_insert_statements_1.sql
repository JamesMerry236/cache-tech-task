--liquibase formatted sql
--changeset James:insert-into-survey-tables splitStatements:true endDelimiter:;
insert into answer_types(id, answer_type) values
	(1, 'TF');
insert into questions(id, answer_type_id, question_text) values
	(1, 1, 'Do you enjoy pizza?'),
	(2, 1, 'Do you enjoy icecream?'),
	(3, 1, 'Do you enjoy noodles?'),
	(4, 1, 'Do you enjoy breakfast for dinner?');