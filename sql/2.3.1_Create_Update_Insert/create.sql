create table role (
	id serial primary key,
	roles text
);

create table users (
	id serial primary key,
	name text,
	role_id int references role(id)
);

create table rules (
	id serial primary key,
	rule text
);

create table role_rules (
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

create table category (
	id serial primary key,
	category text
);

create table state (
	id serial primary key,
	state text
);

create table item (
	id serial primary key,
	items text,
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments (
	id serial primary key,
	comment text,
	item_id int references item(id)
);

create table attachs (
	id serial primary key,
	attach text,
	item_id int references item(id)
);