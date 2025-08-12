create schema md05_ss10;
use md05_ss10;

create TABLE customers
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name    VARCHAR(70)                NOT NULL,
    phone_number VARCHAR(20)                NOT NULL UNIQUE,
    email        VARCHAR(100)               NOT NULL UNIQUE,
    password     VARCHAR(255)               NOT NULL,
    address      VARCHAR(255)               NOT NULL,
    role         ENUM ('ADMIN', 'CUSTOMER') NOT NULL
);

create table rooms
(
    id        int auto_increment primary key,
    room_name varchar(100) not null unique,
    room_type varchar(20)  not null unique,
    status    enum ('PLACED','EMPTY') default 'EMPTY',
    isDelete  bit          not null   default 0,
    price     double       not null check ( price >= 1 ),
    image     varchar(255) not null
);

CREATE TABLE bookings
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id  BIGINT NOT NULL,
    room_id      INT    NOT NULL,
    check_in     DATE   NOT NULL,
    check_out    DATE   NOT NULL,
    total_price  DOUBLE NOT NULL CHECK (total_price >= 1),
    booking_date TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP,
    status       ENUM ('PENDING','CONFIRMED','CANCELLED') DEFAULT 'PENDING',

    CONSTRAINT fk_booking_customer FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT fk_booking_room FOREIGN KEY (room_id) REFERENCES rooms (id)
);

