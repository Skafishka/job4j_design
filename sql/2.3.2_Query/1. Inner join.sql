create table school(
	id serial primary key,
	number int
);

create table pupils(
	id serial primary key,
	name varchar(255),
	number_id int references school(id)
);

insert into school(number) values (3);
insert into school(number) values (6);
insert into school(number) values (21);

insert into pupils(name, number_id) values ('Nick', 1);
insert into pupils(name, number_id) values ('Leena', 2);
insert into pupils(name, number_id) values ('Alex', 3);

select pup.name, sch.number
from pupils as pup join school as sch on pup.number_id = sch.id;

select pup.name as "Pupil FirstName", sch.number as "SCHOOL CurrentNumber"
from pupils as pup join school as sch on pup.number_id = sch.id;

select pup.name NAME, sch.number SCHOOL_NUMBER
from pupils pup join school sch on pup.number_id = sch.id;