create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
	returns trigger as
$$
	BEGIN
		update products
		set price = price  * 1.2
		where id = (select id from inserted);
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure tax();


create or replace function tax_row()
	returns trigger as
$$
	BEGIN
		NEW.price = NEW.price * 1.24;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row_trigger
	before insert
	on products
	for each row
	execute procedure tax_row();