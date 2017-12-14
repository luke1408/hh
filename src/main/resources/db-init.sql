create table user(
	id integer auto_increment primary key,
	name varchar(35) not null,
	password CHAR(32) not null unique,
	email varchar(100) not null,
	admin boolean not null
);

create table unit(
	id integer auto_increment primary key,
	name varchar(50) not null
);

create table product(
	id integer auto_increment primary key,
	name varchar(35) not null,
	description varchar(500) not null,
	price number not null,
	amount integer not null,
	unit integer not null,
	image blob
);

alter table product 
add foreign key(unit)
references unit(id);


create table o_order(
	id integer auto_increment primary key,
	amount integer not null,
	product integer not null,
	user integer not null,
	active boolean not null default true
);

alter table o_order
add foreign key(product)
references product(id);

alter table o_order
add foreign key(user)
references user(id);

create view active_order as 
select * from o_order where active = true;

create view product_ordered_amount as
select product, sum(amount) as amount from o_order 
group by product;

create view product_active_amount as
select p.id, p.name, p.description, p.price,  ifnull((p.amount - poa.amount), p.amount) as amount, unit from product p
left join product_ordered_amount poa on p.id = poa.product;

create view orderable_product as 
select * from product_active_amount where amount > 0;

create view admin_user as
select * from user where admin = true;

create view regular_user as
select * from user where admin = false;

insert into user (name, password, email, admin) values ('admin', 'admin', 'asd2f@gmx.at', true);
insert into unit (name) values ('kg');
insert into unit (name) values ('Stück');

