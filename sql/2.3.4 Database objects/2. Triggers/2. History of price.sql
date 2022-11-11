create table history_of_price (
	id serial primary key,
	name varchar(50),
	price integer,
	date timestamp
);

create or replace function product()
	returns trigger as
$$
	BEGIN
		update history_of_price
		set name = name, price = price, date = date
		where id = new.id;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger update_product
	after insert
	on history_of_price
	for each row
	execute procedure product();