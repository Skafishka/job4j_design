select * from fauna;
select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 AND avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date <= '1950-01-01';