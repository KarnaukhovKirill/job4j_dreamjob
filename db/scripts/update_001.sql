create table cities (
    id SERIAL PRIMARY KEY,
    title varchar(100)
);

insert into cities(title) values ('Moscow');
insert into cities(title) values ('Tokyo');
insert into cities(title) values ('Deli');

CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TIMESTAMP
);

CREATE TABLE candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    created TIMESTAMP,
    city_id int null,
    constraint city_id_fk foreign key (city_id) references cities(id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email varchar(50),
    password TEXT
);












