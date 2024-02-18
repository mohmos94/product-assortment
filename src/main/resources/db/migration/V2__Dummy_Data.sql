-- Inserting dummy data into the furniture table
INSERT INTO furniture (item, name, weight) VALUES
('Chair', 'Wooden Chair', 15.5),
('Table', 'Coffee Table', 25.0),
('Bookshelf', 'Tall Bookshelf', 35.8);

-- Inserting dummy data into the hot_dogs table
INSERT INTO hot_dogs (flavor) VALUES
('Beef'),
('Pork'),
('Chicken'),
('Vegetarian');

-- Inserting dummy data into the textile table
INSERT INTO textile (item, name, color) VALUES
('Towel', 'Bath Towel', 'Blue'),
('Curtains', 'Bedroom Curtains', 'Red'),
('Blanket', 'Wool Blanket', 'Green');

-- Inserting dummy data into the customer table
INSERT INTO customer (name, is_member) VALUES
('John Doe', true),
('Jane Smith', false),
('Bob Johnson', true);

-- Inserting dummy data into the product table
INSERT INTO product (name, price, description) VALUES
('T-Shirt', 20.00, 'Cotton T-Shirt'),
('Jeans', 50.00, 'Denim Jeans'),
('Sneakers', 80.00, 'Running Shoes');

-- Inserting dummy data into the customer_product_discount table
INSERT INTO customer_product_discount (customer_id, product_id, discount) VALUES
(1, 1, 0.10), -- John Doe gets a 10% discount on T-Shirts
(1, 3, 0.05), -- John Doe gets a 5% discount on Sneakers
(2, 2, 0.15); -- Jane Smith gets a 15% discount on Jeans
