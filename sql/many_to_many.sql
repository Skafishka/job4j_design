create table suppliers(
	id serial primary key,
	position varchar(255)
);

create table shop(
	id serial primary key,
	candies varchar(255)
);

create table suppliers_shop(
	id serial primary key,
	supplier_id int references suppliers(id),
	shop_id int references shop(id)
);
