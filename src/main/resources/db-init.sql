CREATE TABLE product (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR2(50),
  price NUMBER
);
CREATE TABLE product_instance(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  product INT NOT NULL,
  quantity INT,
  FOREIGN KEY (product) REFERENCES product(id)
);
CREATE VIEW v_product_quantity AS
select product, sum(quantity) as quantity from product_instance
  group by product;

CREATE VIEW v_product AS
select p.*, q.quantity from product p
  join v_product_quantity q on q.product = p.id;

INSERT INTO product(name, price) VALUES ('wurst', 22);
INSERT INTO product(name, price) VALUES ('drank', 15.2);
INSERT INTO product(name, price) VALUES ('plank', 1.2);
INSERT  INTO product_instance(product, quantity) VALUES (1, 2);
INSERT  INTO product_instance(product, quantity) VALUES (1, 22);
INSERT  INTO product_instance(product, quantity) VALUES (1, -2);
INSERT  INTO product_instance(product, quantity) VALUES (1, -21);
INSERT  INTO product_instance(product, quantity) VALUES (1, 2);
INSERT  INTO product_instance(product, quantity) VALUES (2, -2);
INSERT  INTO product_instance(product, quantity) VALUES (2, 3);
INSERT  INTO product_instance(product, quantity) VALUES (2, 4);
INSERT  INTO product_instance(product, quantity) VALUES (2, -5);
INSERT  INTO product_instance(product, quantity) VALUES (3, 2);
INSERT  INTO product_instance(product, quantity) VALUES (3, -2);
