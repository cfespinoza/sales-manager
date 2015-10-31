-- Database: development

-- DROP DATABASE development;

CREATE DATABASE development;

-- DROP TABLE t_customers;
-- DROP TABLE t_products;
-- DROP TABLE t_bills;
-- DROP TABLE t_details;

CREATE TABLE t_customers
(
  id serial NOT NULL,
  dni character varying NOT NULL,
  name character varying NOT NULL,
  CONSTRAINT t_customers_pkey PRIMARY KEY (dni),
  CONSTRAINT t_customers_id_key UNIQUE (id)
);

CREATE TABLE t_products
(
  id serial NOT NULL,
  name character varying NOT NULL,
  price numeric(5,2) NOT NULL,
  CONSTRAINT t_products_pkey PRIMARY KEY (id)
);


CREATE TABLE t_bills
(
  id serial NOT NULL,
  customer_id integer NOT NULL,
  CONSTRAINT t_bills_pkey PRIMARY KEY (id),
  CONSTRAINT t_bills_customer_id_fkey FOREIGN KEY (customer_id)
    REFERENCES t_customers (id)
);


CREATE TABLE t_details
(
  id serial NOT NULL,
  bill_id integer NOT NULL,
  product_id integer NOT NULL,
  CONSTRAINT t_details_pkey PRIMARY KEY (id),
  CONSTRAINT t_details_bill_id_fkey FOREIGN KEY (bill_id)
    REFERENCES t_bills (id),
  CONSTRAINT t_details_product_id_fkey FOREIGN KEY (product_id)
    REFERENCES t_products (id)
);