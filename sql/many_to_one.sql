create table brand(
	id serial primary key,
	name varchar(255)
);

create table model(
	id serial primary key,
	name varchar(255),
	brand_id int references brand(id)
);

insert into brand(name) values ('VW'), ('Ford');
insert into model(name, brand_id) values ('Transporter', 1), ('Caddy', 1), ('Mustang', 2);
