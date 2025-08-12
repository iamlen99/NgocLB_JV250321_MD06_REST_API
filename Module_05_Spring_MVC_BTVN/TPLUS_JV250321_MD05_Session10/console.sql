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

CREATE TABLE booking
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    room_id        INT  NOT NULL,
    customer_id    INT  NOT NULL,
    check_in_date  DATE NOT NULL,
    check_out_date DATE NOT NULL,
    guests         INT  NOT NULL CHECK (guests > 0),
    notes          VARCHAR(255),

    -- Nếu bạn có bảng room và customer thì tạo khóa ngoại:
    CONSTRAINT fk_booking_room FOREIGN KEY (room_id) REFERENCES room (id) ON DELETE CASCADE,
    CONSTRAINT fk_booking_customer FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

