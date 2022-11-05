create table vin_number(
	id serial primary key,
	numbers varchar(255)
);

create table spare_part(
	id serial primary key,
	spare_part_name varchar(255),
	vin_number_id int references vin_number(id) unique
);
