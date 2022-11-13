create view show_students_with_1_or_more_books
    as select s.name as student, count(a.name), a.name as author from students as s
         right join orders o on s.id = o.student_id
         full join books b on o.book_id = b.id
         left join authors a on b.author_id = a.id
         group by (s.name, a.name) having count(a.name) >= 1;

select * from show_students_with_1_or_more_books;