create schema quanlysanpham;
use quanlysanpham;

create table category
(
    category_id   int auto_increment primary key,
    category_name varchar(50) not null unique,
    description   text,
    status        bit default 1
);

create table product
(
    product_id   int auto_increment primary key,
    product_name varchar(100) not null,
    description  text,
    price        double       not null check ( price > 0 ),
    image_url    varchar(255),
    status       bit default 1,
    created_at   datetime default CURRENT_TIMESTAMP,
    category_id  int          not null,
    constraint pc_category_id_fk
        foreign key (category_id)
            references category (category_id)
);

INSERT INTO category (category_name, description, status)
VALUES ('Điện thoại', 'Các sản phẩm điện thoại thông minh', 1),
       ('Laptop', 'Máy tính xách tay các hãng', 1),
       ('Máy tính bảng', 'Các loại tablet', 1),
       ('Phụ kiện', 'Phụ kiện cho điện thoại và laptop', 1),
       ('Thiết bị mạng', 'Router, modem, thiết bị mạng', 1),
       ('Đồng hồ thông minh', 'Smartwatch các hãng', 1),
       ('Tai nghe', 'Tai nghe không dây và có dây', 1),
       ('Loa Bluetooth', 'Loa di động chất lượng cao', 1),
       ('Camera', 'Camera giám sát và hành trình', 1),
       ('Máy in', 'Máy in và phụ kiện máy in', 1);

INSERT INTO product (product_name, description, price, image_url, status, created_at, category_id)
VALUES ('iPhone 14 Pro Max', 'Điện thoại Apple mới nhất', 32000000, 'iphone14promax.jpg', 1, NOW(), 1),
       ('Samsung Galaxy S23', 'Điện thoại Samsung cao cấp', 25000000, 'galaxys23.jpg', 1, NOW(), 1),
       ('MacBook Pro 16', 'Laptop Apple M2 Pro', 55000000, 'macbookpro16.jpg', 1, NOW(), 2),
       ('Dell XPS 13', 'Laptop Dell sang trọng', 30000000, 'dellxps13.jpg', 1, NOW(), 2),
       ('iPad Pro 11', 'Máy tính bảng Apple', 21000000, 'ipadpro11.jpg', 1, NOW(), 3),
       ('AirPods Pro 2', 'Tai nghe không dây của Apple', 6000000, 'airpodspro2.jpg', 1, NOW(), 7),
       ('Apple Watch Ultra', 'Đồng hồ thông minh cao cấp', 22000000, 'applewatchultra.jpg', 1, NOW(), 6),
       ('Loa JBL Charge 5', 'Loa Bluetooth JBL', 4500000, 'jblcharge5.jpg', 1, NOW(), 8),
       ('Camera IP Hikvision', 'Camera giám sát IP', 2000000, 'hikvision.jpg', 1, NOW(), 9),
       ('Máy in HP LaserJet', 'Máy in laser chất lượng cao', 3500000, 'hplaserjet.jpg', 1, NOW(), 10);


DELIMITER $$
create procedure get_categories_per_page(
    page int,
    page_size int
)
begin
    declare offset_index int;
    set offset_index = (page - 1) * page_size;
    select *
    from category
    limit page_size offset offset_index;
end $$
DELIMITER ;

DELIMITER $$
create procedure get_categories_by_name_per_page(
    category_name_in varchar(50),
    page int,
    page_size int
)
begin
    declare offset_index int;
    set offset_index = (page - 1) * page_size;
    select *
    from category
    where category_name like concat('%', concat(category_name_in, '%'))
    limit page_size offset offset_index;
end $$
DELIMITER ;

# drop procedure get_categories_by_name_total_pages
DELIMITER $$
create procedure get_categories_total_pages(
    page_size int,
    out total_page int
)
begin
    declare count_id int;
    set count_id = (select count(*) from category);
    set total_page = CEIL(count_id / page_size);
end $$
DELIMITER ;

DELIMITER $$
create procedure get_categories_by_name_total_pages(
    category_name_in varchar(50),
    page_size int,
    out total_page int
)
begin
    declare count_id int;
    set count_id = (select count(*)
                    from category
                    where category_name like concat('%', concat(category_name_in, '%')));
    set total_page = CEIL(count_id / page_size);
end $$
DELIMITER ;

#sdgsdgfsdgsdgsdgsdgsdgsgsgsdgsdgsgsdgsadg

DELIMITER $$
create procedure get_products_per_page(
    page int,
    page_size int
)
begin
    declare offset_index int;
    set offset_index = (page - 1) * page_size;
    select *
    from product
    limit page_size offset offset_index;
end $$
DELIMITER ;

DELIMITER $$
create procedure get_products_by_name_per_page(
    product_name_in varchar(100),
    page int,
    page_size int
)
begin
    declare offset_index int;
    set offset_index = (page - 1) * page_size;
    select *
    from product
    where product_name like concat('%', product_name_in, '%')
    limit page_size offset offset_index;
end $$
DELIMITER ;


DELIMITER $$
create procedure get_products_total_pages(
    page_size int,
    out total_page int
)
begin
    declare count_id int;
    set count_id = (select count(*) from product);
    set total_page = CEIL(count_id / page_size);
end $$
DELIMITER ;

DELIMITER $$
create procedure get_products_by_name_total_pages(
    product_name_in varchar(100),
    page_size int,
    out total_page int
)
begin
    declare count_id int;
    set count_id = (select count(*)
                    from product
                    where product_name like concat('%', product_name_in, '%'));
    set total_page = CEIL(count_id / page_size);
end $$
DELIMITER ;