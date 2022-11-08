create table car_bodies(
	id serial primary key,
	name varchar(255)
);

create table car_engines(
	id serial primary key,
	name varchar(255)
);

create table car_transmissions(
	id serial primary key,
	name varchar(255)
);

create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('sedan'), ('hatchback'), ('pickup'), ('universal'), ('jeep'), ('cabriolet');
insert into car_engines(name) values ('fourstroke diesel'), ('2 liters bensin'), ('ecodrive bensin'), ('twostroke diesel');
insert into car_transmissions(name) values ('automat'), ('mechanic'), ('semiautomat');
insert into cars(name, body_id, engine_id, transmission_id) values ('Ford', 1, 1, 1), ('Shelby', 1, 2, 3), 
('VW', 4, 3, 3), ('Jeep', 5, 1, 1), ('Subaru', 2, 2, 3);

select c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmissions_name from cars c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

select * from car_bodies cb left join cars c on c.body_id = cb.id where c.body_id is null;

select * from car_engines ce left join cars c on c.engine_id = ce.id where c.engine_id is null;

select * from car_transmissions ct left join cars c on c.transmission_id = ct.id where c.transmission_id is null;