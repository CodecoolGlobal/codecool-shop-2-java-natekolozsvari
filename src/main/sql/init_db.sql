DROP TABLE IF EXISTS products;
CREATE TABLE products (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text NOT NULL ,
    defaultPrice float NOT NULL,
    defaultCurrency text NOT NULL,
    productCategory_id integer NOT NULL,
    supplier_id integer NOT NULL
);

DROP TABLE IF EXISTS productCategories;
CREATE TABLE productCategories (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text NOT NULL,
    department text NOT NULL
);

DROP TABLE IF EXISTS suppliers;
CREATE TABLE suppliers(
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text NOT NULL
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    email text NOT NULL,
    password text NOT NULL ,
    reg_date timestamp NOT NULL
    );


DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    id serial NOT NULL PRIMARY KEY,
    user_id integer NOT NULL,
    date timestamp NOT NULL,
    order_status text NOT NULL,
    total_price money NOT NULL,
    product_list text[] NOT NULL
);

DROP TABLE IF EXISTS cart;
CREATE TABLE cart (
    id serial NOT NULL PRIMARY KEY,
    user_id integer NOT NULL,
    date timestamp NOT NULL,
    total_price money NOT NULL,
    product_list text[] NOT NULL
);


DROP TABLE IF EXISTS billingInfo;
CREATE TABLE  billingInfo (
    id serial NOT NULL PRIMARY KEY,
    user_id integer NOT NULL,
    name text NOT NULL,
    email text NOT NULL,
    phoneNumber text NOT NULL,
    country text NOT NULL,
    address text NOT NULL,
    city text NOT NULL,
    zip_code integer NOT NULL,
    card_name text,
    card_number text,
    expiration_date text,
    cvv text
);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_productCategories_id FOREIGN KEY (productCategory_id) REFERENCES productCategories(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_suppliers_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE ONLY billingInfo
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id);