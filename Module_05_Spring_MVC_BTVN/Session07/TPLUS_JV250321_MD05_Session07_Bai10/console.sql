create schema md05_ss07_db;

use md05_ss07_db;

create table movie
(
    id           int auto_increment primary key,
    title        varchar(100),
    director     varchar(50),
    release_date date,
    genre        varchar(30),
    poster       varchar(100)
);

INSERT INTO movie (title, director, release_date, genre, poster)
VALUES ('Inception', 'Christopher Nolan', '2010-07-16', 'Sci-Fi', 'https://example.com/posters/inception.jpg'),
       ('The Godfather', 'Francis Ford Coppola', '1972-03-24', 'Crime', 'https://example.com/posters/godfather.jpg'),
       ('Parasite', 'Bong Joon-ho', '2019-05-30', 'Thriller', 'https://example.com/posters/parasite.jpg'),
       ('Spirited Away', 'Hayao Miyazaki', '2001-07-20', 'Animation', 'https://example.com/posters/spirited_away.jpg'),
       ('The Dark Knight', 'Christopher Nolan', '2008-07-18', 'Action', 'https://example.com/posters/dark_knight.jpg'),
       ('Pulp Fiction', 'Quentin Tarantino', '1994-10-14', 'Crime', 'https://example.com/posters/pulp_fiction.jpg'),
       ('Forrest Gump', 'Robert Zemeckis', '1994-07-06', 'Drama', 'https://example.com/posters/forrest_gump.jpg'),
       ('Interstellar', 'Christopher Nolan', '2014-11-07', 'Sci-Fi', 'https://example.com/posters/interstellar.jpg'),
       ('Your Name', 'Makoto Shinkai', '2016-08-26', 'Animation', 'https://example.com/posters/your_name.jpg'),
       ('The Shawshank Redemption', 'Frank Darabont', '1994-09-22', 'Drama',
        'https://example.com/posters/shawshank.jpg');

DELIMITER $$
CREATE PROCEDURE add_movie(
    IN p_title VARCHAR(100),
    IN p_director VARCHAR(50),
    IN p_release_date DATE,
    IN p_genre VARCHAR(30),
    IN p_poster VARCHAR(100)
)
BEGIN
    INSERT INTO movie(title, director, release_date, genre, poster)
    VALUES (p_title, p_director, p_release_date, p_genre, p_poster);
END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE update_movie(
    IN p_id INT,
    IN p_title VARCHAR(100),
    IN p_director VARCHAR(50),
    IN p_release_date DATE,
    IN p_genre VARCHAR(30),
    IN p_poster VARCHAR(100)
)
BEGIN
    UPDATE movie
    SET title        = p_title,
        director     = p_director,
        release_date = p_release_date,
        genre        = p_genre,
        poster       = p_poster
    WHERE id = p_id;
END $$

DELIMITER ;


