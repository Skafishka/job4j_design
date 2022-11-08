create table employees(
	id serial primary key,
	name varchar(255)
);

create table departments(
	id serial primary key,
	name varchar(255),
	employees_id int references employees(id)
);

insert into employees(name) values ('George'), ('Alex'), ('Joe'), ('John'), ('Elmira');
insert into departments(name, employees_id) values ('Estimation department', 2), 
('Cost department', 1), 
('Execution department', 3), 
('Cost department', 4),
('Engineering department', null);

create table teens(
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Henri', 'male'), ('Alex', 'male'), ('Kate', 'female'), ('Angeli', 'female'), ('Kris', 'male');

select * from departments d left join employees e on d.employees_id = e.id;
select * from departments d right join employees e on d.employees_id = e.id;
select * from departments d full join employees e on d.employees_id = e.id;
select * from departments d left join employees e on d.employees_id = e.id where e.id is null;

select * from departments d left join employees e on d.employees_id = e.id;
select * from departments d right join employees e on d.employees_id = e.id;

select m.name, f.name
from teens m cross join teens f
where m.gender != f.gender;