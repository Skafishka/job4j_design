create table recipe(
	id serial primary key,
	contents varchar(255)
);

create table foods(
	id serial primary key,
	product varchar(255),
	recipe_id int references recipe(id)
);

insert into recipe(contents) values ('salad');
insert into foods(product, recipe_id) VALUES ('tomato', 1);