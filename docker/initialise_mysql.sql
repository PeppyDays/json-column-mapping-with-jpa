create database cart;

create table cart.carts (
    id int auto_increment primary key,
    name varchar(100),
    items json
);

insert into carts values (100, 'dongkyun', '[{"sku": "KNIFE", "quantity": 5}, {"sku": "SPOON", "quantity": 10}]');
insert into carts values (101, 'hajoon', '[{"sku": "KNIFE", "quantity": 5, "qty": 5}]');
