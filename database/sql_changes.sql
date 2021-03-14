--liquibase formatted sql
--changeset James:create-question-type-table splitStatements:true endDelimiter:;
create table answer_types
(
	id bigint not null,
	answer_type text not null
);
alter table answer_types add constraint answer_types_pkey primary key (id);
create table questions
(
	id bigint not null,
	answer_type_id bigint not null,
	question_text text not null
);
alter table questions add constraint questions_pkey primary key (id);
alter table questions add constraint question_answer_types_are_answer_types foreign key (answer_type_id) references answer_types(id);
create table users
(
	id bigint not null,
	username text not null
);
alter table users add constraint users_pkey primary key (id);
create table answers
(
	id bigint not null,
	user_id bigint not null,
	question_id bigint not null,
	answer text not null
);
alter table answers add constraint answers_pkey primary key (id);
alter table answers add constraint user_exists foreign key (user_id) references users(id);
alter table answers add constraint questions_is_a_question foreign key (id) references questions(id);
