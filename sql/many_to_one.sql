create table suppliers(
	id serial primary key,
	position varchar(255)
);

create table shop(
	id serial primary key,
	candies varchar(255),
	suppliers_id int references suppliers(id)
);

insert into suppliers(position) values ('sweet');
insert into shop(candies, suppliers_id) VALUES ('baranka', 1);

select * from shop;

select * from suppliers where id in (select suppliers_id from shop);