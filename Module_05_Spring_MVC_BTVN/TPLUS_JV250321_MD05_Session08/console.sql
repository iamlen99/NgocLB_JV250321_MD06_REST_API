create schema md05_ss08;
use
md05_ss08;

create table student
(
    id    int primary key auto_increment,
    name  varchar(100),
    email varchar(100),
    dob   date
);

DELIMITER
$$
create procedure add_student(
    name_in varchar (100),
    email_in varchar (100),
    dob_in date
)
begin
insert into student(name, email, dob)
values (name_in,
        email_in,
        dob_in);
end $$
DELIMITER ;

DELIMITER
$$
create procedure update_student(
    id_in int,
    name_in varchar (100),
    email_in varchar (100),
    dob_in date
)
begin
update student
set name  = name_in,
    email = email_in,
    dob   = dob_in
where id = id_in;
end $$
DELIMITER ;

INSERT INTO student (name, email, dob)
VALUES ('Nguyễn Văn A', 'vana@example.com', '2000-01-15'),
       ('Trần Thị B', 'thib@example.com', '2001-02-20'),
       ('Lê Văn C', 'vanc@example.com', '1999-12-05'),
       ('Phạm Thị D', 'thid@example.com', '2000-07-30'),
       ('Hoàng Văn E', 'vane@example.com', '2002-03-25');
