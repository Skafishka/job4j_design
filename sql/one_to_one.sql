create table suppliers(
	id serial primary key,
	position varchar(255)
);

create table shop(
	id serial primary key,
	candies varchar(255),
	suppliers_id int references suppliers(id) unique
);
