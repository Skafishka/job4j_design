create table owners(
	id serial primary key,
	name varchar(255)
);

create table pets(
	id serial primary key,
	name varchar(255),
	owner_id int references owners(id) unique
);

insert into owners(name) values ('Joe'), ('George');
insert into pets(name, owner_id) values ('Pipi', 1), ('Grizzly', 2);