-- V1.0000__2019.02.20.sql

CREATE TABLE store (
  store_id bigserial NOT NULL,
  name character varying(80) NOT NULL,
  address character varying(80) NOT NULL,
  CONSTRAINT store_pkey1 PRIMARY KEY (store_id),
  CONSTRAINT store_key UNIQUE (name, address)
);

INSERT INTO store VALUES (1, 'Teste', 'Teste');