create database product_db;

use product_db;
 create table Product (
 id int primary key auto_increment,
 name varchar(255) Not Null,
 description TEXT,
 price DECIMAL(10,2) Not Null
 )

 INSERT INTO Product (name, description, price) VALUES
 ('Watch', 'A stylish and modern smartwatch with health tracking features and long battery life.', 199.99),
 ('Monitor', 'A 27-inch 4K UHD monitor with vibrant colors and excellent viewing angles.', 399.99),
 ('Headset', 'Wireless noise-cancelling headset with high-fidelity sound and a comfortable fit.', 149.99),
 ('Laptop', 'High-performance laptop with an Intel i7 processor, 16GB RAM, and 512GB SSD.', 1299.99),
 ('Mouse', 'Ergonomic wireless mouse with adjustable DPI and long-lasting battery.', 49.99);
