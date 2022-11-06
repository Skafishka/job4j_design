create table users (
	id serial primary key,
	name text,
	role_id int references role(id)
);

create table role (
	id serial primary key,
	roles text
);

create table rules (
	id serial primary key,
	rule text
);

create table role_rules (
	id serial primary key,
	role_id int references role(id),
	rules_id int referens rules(id)
);

create table item (
	id serial primary key,
	items text,
	users_id int references users(id),
	comments_id int references comments(id),
	attachs_id int references attachs(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments (
	id serial primary key,
	comment text
);

create table attachs (
	id serial primary key,
	attach text
);

create table category (
	id serial primary key,
	category text
);

create table state (
	id serial primary key,
	state text
);