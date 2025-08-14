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
    created_at    datetime,
    category_id  int          not null,
    constraint pc_category_id_fk
        foreign key (category_id)
            references category (category_id)
);

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
    product_name_in varchar(50),
    page int,
    page_size int
)
begin
    declare offset_index int;
    set offset_index = (page - 1) * page_size;
    select *
    from product
    where product_name like concat('%', concat(product_name_in, '%'))
    limit page_size offset offset_index;
end $$
DELIMITER ;

# drop procedure get_products_by_name_total_pages
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
    product_name_in varchar(50),
    page_size int,
    out total_page int
)
begin
    declare count_id int;
    set count_id = (select count(*)
                    from product
                    where product_name like concat('%', concat(product_name_in, '%')));
    set total_page = CEIL(count_id / page_size);
end $$
DELIMITER ;