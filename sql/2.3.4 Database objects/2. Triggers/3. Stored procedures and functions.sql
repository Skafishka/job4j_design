create or replace procedure delete_data(u_count integer)
language 'plpgsql'
as $$
    BEGIN
	if u_count <= 0 THEN
    	delete from products where id = u_id;
	end if;
    END;
$$;

create or replace function f_delete_data(u_count integer)
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