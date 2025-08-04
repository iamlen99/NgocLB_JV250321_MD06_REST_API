create schema md05_ss04;

use md05_ss04;
# drop table category;
create table category
(
    id          int auto_increment primary key,
    cate_name   varchar(50),
    description varchar(50),
    status      bit default 1
);

INSERT INTO category (cate_name, description)
VALUES ('Dien thoai', 'San pham cong nghe'),
       ('Thoi trang', 'Quan ao, phu kien'),
       ('Do gia dung', 'Thiet bi nha bep'),
       ('Sach', 'Sach hoc tap va giai tri'),
       ('Do choi', 'Do choi cho tre em');
