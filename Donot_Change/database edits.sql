ALTER TABLE export
DROP CONSTRAINT FK__export__ord_id__2DE6D218;

ALTER TABLE export
DROP COLUMN ord_id; 

drop table orders;

CREATE TABLE orders (
    ord_id int IDENTITY(1,1) PRIMARY KEY,
    ord_wight decimal(12,3) NOT NULL,
    ord_date date NOT NULL
);

ALTER TABLE export
ADD ord_id int;

ALTER TABLE export
ADD FOREIGN KEY (ord_id) REFERENCES orders(ord_id);