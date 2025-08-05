create schema md5_ss5;
use md5_ss5;

create table customer
(
    id       bigint auto_increment primary key,
    username varchar(50),
    password varchar(50),
    phone    varchar(50),
    address  varchar(50),
    gender   varchar(50),
    email    varchar(50),
    role     enum ('ADMIN', 'USER')
);

INSERT INTO customer (username, password, phone, address, gender, email, role)
VALUES ('nguyenvana', '123456', '0909123456', 'Ha Noi', 'Nam', 'vana@gmail.com', 'USER'),
       ('tranthib', 'abc123', '0912345678', 'Ho Chi Minh', 'Nu', 'thib@gmail.com', 'USER'),
       ('leductrung', 'trungpass', '0987654321', 'Da Nang', 'Nam', 'trungld@gmail.com', 'ADMIN'),
       ('phamthanhh', 'hello123', '0933123123', 'Can Tho', 'Nu', 'thanhhpham@gmail.com', 'USER'),
       ('hoangminh', 'minh2024', '0978989898', 'Hai Phong', 'Nam', 'minhhoang@gmail.com', 'USER');

