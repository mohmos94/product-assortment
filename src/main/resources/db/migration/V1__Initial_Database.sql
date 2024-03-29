CREATE TABLE IF NOT EXISTS furniture (
    itemNumber SERIAL PRIMARY KEY,
    category VARCHAR(255),
    item VARCHAR(255),
    name VARCHAR(255),
    weight DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS hot_dogs (
    flavor VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS textile (
    itemNumber SERIAL PRIMARY KEY,
    category VARCHAR(255),
    item VARCHAR(255),
    name VARCHAR(255),
    color VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    is_member BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS customer_product_discount (
    customer_id INT REFERENCES customer(id),
    product_id INT REFERENCES product(id),
    discount DECIMAL(5, 2),
    discounted_price DECIMAL(5,2),
    PRIMARY KEY (customer_id, product_id)
);


CREATE TABLE IF NOT EXISTS order_table (
    order_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customer(id),
    product_id INT REFERENCES product(id),
    order_date DATE NOT NULL,
    category VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(10, 2) NOT NULL

);




