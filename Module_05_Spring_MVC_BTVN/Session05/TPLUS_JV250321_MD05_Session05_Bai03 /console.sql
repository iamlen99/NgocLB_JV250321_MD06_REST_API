use md5_ss5;

create table Movie
(
    id          bigint auto_increment primary key,
    title       varchar(255) not null unique,
    director    varchar(70),
    genre       varchar(50),
    description varchar(255),
    duration    int,
    language    varchar(30)
);

create table screen_room
(
    id               bigint auto_increment primary key,
    screen_room_name varchar(100) not null,
    total_seat       int          not null
);

create table schedule
(
    id              bigint auto_increment primary key,
    movie_title     varchar(255) not null,
    movie_id        bigint       not null,
    show_time       datetime     not null,
    screen_room_id  bigint       not null,
    available_seats int          not null,
    format          varchar(10)  not null check (format in ('2d', '3d')),
    foreign key (screen_room_id) references screen_room (id)
);


INSERT INTO Movie (title, director, genre, description, duration, language)
VALUES ('Inception', 'Christopher Nolan', 'Science Fiction', 'A thief steals corporate secrets through dream-sharing.',
        148, 'English'),
       ('Parasite', 'Bong Joon-ho', 'Thriller', 'A poor family schemes to become employed by a wealthy family.', 132,
        'Korean'),
       ('Spirited Away', 'Hayao Miyazaki', 'Animation', 'A girl enters a magical world to save her parents.', 125,
        'Japanese'),
       ('The Godfather', 'Francis Ford Coppola', 'Crime', 'The aging patriarch transfers control to his son.', 175,
        'English'),
       ('Interstellar', 'Christopher Nolan', 'Science Fiction',
        'A team travels through a wormhole to find a new home for humanity.', 169, 'English');

DELIMITER $$
create procedure add_movie(
    title_in varchar(250),
    director_in varchar(70),
    genre_in varchar(50),
    description_in varchar(255),
    duration_in int,
    language_in varchar(30)
)
begin
    insert into Movie (title, director, genre, description, duration, language)
    values (title_in, director_in, genre_in, description_in, duration_in, language_in);
end $$
DELIMITER ;

DELIMITER $$
create procedure update_movie(
    id_in bigint,
    title_in varchar(250),
    director_in varchar(70),
    genre_in varchar(50),
    description_in varchar(255),
    duration_in int,
    language_in varchar(30)
)
begin
    update Movie
    set title       = title_in,
        director    = director_in,
        genre       = genre_in,
        description = description_in,
        duration    = duration_in,
        language    = language_in
    where id = id_in;
end $$
DELIMITER ;

delimiter $$
create procedure get_all_schedules()
begin
    select * from schedule;
end $$
delimiter ;

delimiter $$
create procedure get_schedule_by_id (
    in p_id bigint
)
begin
    select * from schedule where id = p_id;
end $$
delimiter ;

delimiter $$
create procedure create_schedule (
    in p_movie_title varchar(255),
    in p_movie_id bigint,
    in p_show_time datetime,
    in p_screen_room_id bigint
)
begin
    declare v_total_seat int;

    -- lấy tổng số ghế từ bảng screen_room
    select total_seat into v_total_seat
    from screen_room
    where id = p_screen_room_id;

    -- mặc định định dạng là 2d, bạn có thể thay đổi nếu cần
    insert into schedule (movie_title, movie_id, show_time, screen_room_id, available_seats, format)
    values (p_movie_title, p_movie_id, p_show_time, p_screen_room_id, v_total_seat, '2d');
end $$
delimiter ;

delimiter $$
create procedure update_schedule (
    in p_id bigint,
    in p_show_time datetime,
    in p_screen_room_id bigint,
    in p_available_seats int,
    in p_format varchar(10)
)
begin
    update schedule
    set
        show_time = p_show_time,
        screen_room_id = p_screen_room_id,
        available_seats = p_available_seats,
        format = p_format
    where id = p_id;
end $$
delimiter ;

delimiter $$
create procedure delete_schedule (
    in p_id bigint
)
begin
    delete from schedule
    where id = p_id;
end $$
delimiter ;
