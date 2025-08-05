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
    director_in    varchar(70),
    genre_in     varchar(50),
    description_in varchar(255),
    duration_in    int,
    language_in    varchar(30)
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
    director_in    varchar(70),
    genre_in     varchar(50),
    description_in varchar(255),
    duration_in    int,
    language_in    varchar(30)
)
begin
    update Movie
    set title = title_in,
        director = director_in,
        genre = genre_in,
        description = description_in,
        duration = duration_in,
        language = language_in
    where id = id_in;
end $$
DELIMITER ;
