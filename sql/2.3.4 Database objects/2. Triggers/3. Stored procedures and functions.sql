create or replace procedure delete_data_if_less_than_zero(u_count integer, u_id integer)
language 'plpgsql'
as $$
    BEGIN
	    if u_count <= 0 THEN
    	    delete from products
    	    where id = u_id;
    END;
$$;

create or replace function f_delete_data_if_less_than_zero(u_count integer, u_id integer)
returns void
language 'plpgsql'
as
$$
    begin
		if u_count <= 0 THEN
        	delete from products where id = u_id;
    end if;
	end;
$$;