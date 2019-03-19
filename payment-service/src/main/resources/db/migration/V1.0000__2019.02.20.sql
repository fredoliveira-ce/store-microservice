-- V1.0000__2019.02.20.sql

CREATE TABLE payment
(
  payment_id bigserial NOT NULL,
  status character(1) NOT NULL DEFAULT 1,
  credit_card_number numeric(16,0) NOT NULL,
  payment_date timestamp without time zone NOT NULL,
  CONSTRAINT payment_pkey1 PRIMARY KEY (payment_id),
  CONSTRAINT payment_key UNIQUE (status, credit_card_number, payment_date)
);
