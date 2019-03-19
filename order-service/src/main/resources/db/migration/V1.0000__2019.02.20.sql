-- V1.0000__2019.02.20.sql

 CREATE TABLE "order"
(
  order_id bigserial NOT NULL,
  address character varying(160) NOT NULL,
  confirmation_date timestamp without time zone,
  status character(1) DEFAULT 1,
  payment_id numeric(12,0),
  CONSTRAINT order_pkey1 PRIMARY KEY (order_id),
  CONSTRAINT order_key UNIQUE (address, confirmation_date, status)
);

CREATE TABLE order_item
(
  order_item_id bigserial NOT NULL,
  "order" bigint NOT NULL,
  description character varying(120) NOT NULL,
  unit_price numeric(8,2) NOT NULL,
  quantity numeric(8,2) NOT NULL,
  status character(1) DEFAULT 1,
  CONSTRAINT order_item_pkey1 PRIMARY KEY (order_item_id),
  CONSTRAINT fk1_order_item FOREIGN KEY ("order")
      REFERENCES "order" (order_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT order_item_key UNIQUE ("order", description, unit_price, quantity)
);