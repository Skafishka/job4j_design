create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('phone', 2399), ('laptop', 9999), ('smart clock', 4999);
insert into people(name) values ('Alex'), ('Joe'), ('Gabriel');
insert into devices_people(device_id, people_id) values (1, 1), (1, 3), (2, 2), (2, 3), (3, 1), (3,2);

select avg(price) from devices;

select pp.name, avg(dev.price)
from devices_people as dev_pp
join people pp
on dev_pp.people_id = pp.id
join devices dev
on dev_pp.device_id = dev.id
group by pp.name
having avg(dev.price) > 5000;