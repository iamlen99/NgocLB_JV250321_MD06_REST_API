create schema md05_ss07_db;
use md05_ss07_db;

create table category
(
    id            int auto_increment primary key,
    category_name varchar(50) not null unique,
    description   text        not null,
    status        bit default 1
);

delimiter $$
create procedure add_category(
    in p_category_name varchar(50),
    in p_description text
)
begin
    insert into category(category_name, description)
    values (p_category_name, p_description);
end $$
delimiter ;

delimiter $$
create procedure update_category(
    in p_id int,
    in p_category_name varchar(50),
    in p_description text,
    in p_status bit
)
begin
    update category
    set category_name = p_category_name,
        description   = p_description,
        status        = p_status
    where id = p_id;
end $$
delimiter ;

insert into category (category_name, description, status)
values ('dien_thoai', 'san pham lien quan den dien thoai di dong', 1),
       ('may_tinh_bang', 'thiet bi cam tay co man hinh cam ung', 1),
       ('may_tinh_xach_tay', 'cac loai laptop va phu kien', 1),
       ('phu_kien', 'tai nghe, op lung, sac...', 1),
       ('gia_dung', 'thiet bi dien gia dung nhu noi com, may xay', 1),
       ('thoi_trang_nam', 'quan ao, giay dep cho nam', 1),
       ('thoi_trang_nu', 'quan ao, vay, phu kien nu', 1),
       ('dien_lanh', 'tu lanh, dieu hoa, may giat', 1),
       ('sach', 'sach hoc, tieu thuyet, truyen tranh', 1),
       ('do_choi', 'do choi tre em va nguoi lon', 1);


