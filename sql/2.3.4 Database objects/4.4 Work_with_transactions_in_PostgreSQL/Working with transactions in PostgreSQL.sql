create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

begin transaction;
insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 9, 99);
savepoint first_savepoint;
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_3', 8, 88);
savepoint second_savepoint;
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_7', 45, 77);
savepoint third_savepoint;
insert into products (name, producer, count, price) VALUES ('product_8', 'producer_5', 47, 39);
savepoint fourth_savepoint;
select * from products;

rollback to first_savepoint;
select * from products;

rollback to second_savepoint;
select * from products;

rollback to third_savepoint;
select * from products;

commit transaction;