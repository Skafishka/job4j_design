create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Pseudalopex_fulvipes_fox', 3650, '1831-01-01');
insert into fauna(name, avg_age, discovery_date) values('Craspedacusta_sowerbii_jellyfish', 180, '1880-01-01');
insert into fauna(name, avg_age, discovery_date) values('Sceloporus_huichol_lizard', 2190, '2022-01-01');
insert into fauna(name, avg_age, discovery_date) values('Pithecophaga_jefferyi_eagle', 10585, '1963-01-01');
insert into fauna(name, avg_age, discovery_date) values('Phocidae_seals', 10950, null);

