create table category_c
(
    id serial8,
    name varchar(30) not null,
    primary key (id)
);
insert into category_c(name)
values ('CPU'),
       ('Monitor');


create table product_c
(
    id serial8,
    category_c_id int8 not null,
    name_c varchar(50) not null,
    price_c int4 not null,
    primary key (id),
    foreign key (category_c_id) references category_c(id)
);
insert into product_c(category_c_id, name_c, price_c)
values (1, 'Intel Core I9 9900', 100000),
       (1, 'AMD Ryzen R7 7700', 120000),
       (2, 'Samsung SU556270', 20000),
       (2, 'AOC Z215S659', 30000);


create table characteristic
(
    id serial8,
    name_char varchar(50) not null,
    category_char_id int8 not null,
    primary key (id),
    foreign key (category_char_id) references category_c(id)
);
insert into characteristic(name_char, category_char_id)
values ('Производитель', 1),
       ('Количество ядер', 1),
       ('Сокет', 1),
       ('Производитель', 2),
       ('Диагональ', 2),
       ('Матрица', 2),
       ('Разрешение', 2);


create table char_products
(
    id serial8,
    product_c_id int8,
    char_id int8,
    value varchar(50) not null,
    primary key (char_id, product_c_id),
    foreign key (char_id) references characteristic(id),
    foreign key (product_c_id) references product_c(id)
);
insert into char_products(product_c_id, char_id, value)
values (1, 1, 'Intel'),
       (1, 2, '8'),
       (1, 3, '1250'),
       (2, 1, 'AMD'),
       (2, 2, '12'),
       (2, 3, 'AM4'),
       (3, 1, 'Samsung'),
       (3, 4, '27'),
       (3, 5, 'TN'),
       (3, 6, '2560*1440'),
       (4, 1, 'AOC'),
       (4, 4, '21.5'),
       (4, 5, 'AH-IPS'),
       (4, 6, '1920*1080');

select c.name, avg(pc.price_c) average
from category_c c
         join product_c pc on c.id = pc.category_c_id
where c.name = 'CPU'
group by c.id