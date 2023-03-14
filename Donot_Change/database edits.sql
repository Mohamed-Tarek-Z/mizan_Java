drop table users;

CREATE TABLE users (
    userId int IDENTITY(1,1) PRIMARY KEY,
    username nvarchar(35) NOT NULL,
    password_ nvarchar(35) NOT NULL,
    status_ bit default 0
);


INSERT INTO users
VALUES ('محمد نشأت'
, '6282', 0);

INSERT INTO users
VALUES ('ميزان'
, '12345', 1);

ALTER TABLE export
ADD PRIMARY KEY (exp_id);

ALTER TABLE storage
ADD PRIMARY KEY (storage_id);

CREATE TABLE orders (
    ord_id int IDENTITY(1,1) PRIMARY KEY,
    ord_wight decimal(12,3) NOT NULL,
    ord_date date NOT NULL
);

ALTER TABLE export
ADD ord_id int;

ALTER TABLE export
ADD FOREIGN KEY (ord_id) REFERENCES orders(ord_id);