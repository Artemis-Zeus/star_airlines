create table car
(
    id      int auto_increment
        primary key,
    address varchar(20) not null,
    price   int         not null,
    brand   varchar(20) not null
);

create table flights
(
    id           int         not null
        primary key,
    depart       varchar(20) null,
    arrival      varchar(20) null,
    depart_time  datetime    null,
    arrival_time datetime    null,
    price        int         null,
    type         int         null comment '1-经济舱 2-商务舱 3-头等舱',
    name         varchar(20) null
);

create table hotel
(
    id      int auto_increment
        primary key,
    address varchar(20) not null,
    price   int         not null,
    name    varchar(20) not null
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(20)   not null,
    password varchar(20)   not null,
    email    varchar(20)   not null,
    phone    varchar(20)   null,
    card_num varchar(20)   null,
    point    int default 0 null
);

create table record
(
    id          varchar(100) not null comment '订单号，UUID'
        primary key,
    user_id     int          not null,
    flight_id   int          null,
    hotel_id    int          null,
    days        int          null,
    car_id      int          null,
    price       int          null,
    type        int          not null comment 'Flight:1-book 2-Refund',
    update_time datetime     not null,
    use_point   int          not null comment '0-不用积分 1-使用积分（-500）',
    constraint user_id
        foreign key (user_id) references user (id)
);


