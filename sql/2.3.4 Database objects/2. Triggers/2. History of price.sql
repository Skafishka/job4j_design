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
		insert into history_of_price(name, price, date)
		values(NEW.name, NEW.price, current_date);
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger update_product
	after insert
	on products
	for each row
	execute procedure product();