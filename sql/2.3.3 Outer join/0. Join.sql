create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values ('Estimation department'),
('Cost department'),
('Execution department'),
('Cost department'),
('Engineering department');
insert into employees(name, department_id) values ('George', 1),
('Alex', 2),
('Joe', 3),
('John', 4),
('Elmira', 1),
('Elon', null);

select * from departments d
left join employees e
on d.id = e.department_id;

select * from departments d
right join employees e
on d.id = e.department_id;

select * from departments d
full join employees e
on d.id = e.department_id;

select * from departments d
left join employees e
on d.id = e.department_id
where e.id is null;

select * from departments d
left join employees e
on d.id = e.department_id
where e.department_id is not null;

select * from departments d
right join employees e
on d.id = e.department_id
where e.department_id is not null;

create table teens(
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Henri', 'male'),
('Alex', 'male'),
('Kate', 'female'),
('Angeli', 'female'),
('Kris', 'male');

select m.name, f.name
from teens m cross join teens f
where m.gender != f.gender;