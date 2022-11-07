create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
	expired_date Date,
	price int
);

insert into type(name) values ('milk'), ('cheese');

insert into product(name, type_id, expired_date, price) values 
('dairy_milk', 1, '2022-11-07', 153), 
('fat_milk', 1, '2022-12-07', 199), 
('mozarella', 2, '2021-12-07', 499),
('balls', 2, '2022-08-07', 356),
('fresh_icecream', 1, '2022-12-28', 298);

select *
from product p
join type t on p.type_id = t.id
where t.name = 'cheese';

select *
from product p
join type t on p.type_id = t.id
where p.name like '%icecream%';

select *
from product p
join type t on p.type_id = t.id
where p.expired_date < now();

select t.name, count(type_id)
from product p
join type t on p.type_id = t.id
group by t.name;

select *
from product p
join type t on p.type_id = t.id
where t.name = 'cheese' or t.name = 'milk';

select t.name, count(type_id)
from product p
join type t on p.type_id = t.id
group by t.name
having count(type_id) < 10;

select *
from product p
join type t on p.type_id = t.id;