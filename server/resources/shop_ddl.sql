--   -------------------------------------------------- 
--   Generated by Enterprise Architect Version 8.0.863
--   Created On : czwartek, 11 maj, 2017 
--   DBMS       : PostgreSQL 
--   -------------------------------------------------- 
CREATE SCHEMA shop_schema;

--  Drop Tables, Stored Procedures and Views 

DROP TABLE IF EXISTS shop_schema.Adress CASCADE;
DROP TABLE IF EXISTS shop_schema.Auction CASCADE;
DROP TABLE IF EXISTS shop_schema.Authority CASCADE;
DROP TABLE IF EXISTS shop_schema.Bought_Product CASCADE;
DROP TABLE IF EXISTS shop_schema.Cart_Product CASCADE;
DROP TABLE IF EXISTS shop_schema.Category CASCADE;
DROP TABLE IF EXISTS shop_schema.Comment CASCADE;
DROP TABLE IF EXISTS shop_schema.Delivery CASCADE;
DROP TABLE IF EXISTS shop_schema.Delivery_Type CASCADE;
DROP TABLE IF EXISTS shop_schema.Observed_Product CASCADE;
DROP TABLE IF EXISTS shop_schema.Payment CASCADE;
DROP TABLE IF EXISTS shop_schema.Photo CASCADE;
DROP TABLE IF EXISTS shop_schema.Product CASCADE;
DROP TABLE IF EXISTS shop_schema.Product_Delivery CASCADE;
DROP TABLE IF EXISTS shop_schema.Product_Photo CASCADE;
DROP TABLE IF EXISTS shop_schema.Product_State CASCADE;
DROP TABLE IF EXISTS shop_schema.Shopping_Cart CASCADE;
DROP TABLE IF EXISTS shop_schema.User CASCADE;
DROP TABLE IF EXISTS shop_schema.User_Authority CASCADE;

--  Create Tables 
CREATE TABLE shop_schema.Adress (
  id             BIGINT       NOT NULL,
  user_id        BIGINT       NOT NULL,
  name           VARCHAR(255) NOT NULL,
  street         VARCHAR(255) NOT NULL,
  number         INTEGER,
  city           VARCHAR(255) NOT NULL,
  postal_code    VARCHAR(6)   NOT NULL,
  phone_number   VARCHAR(20),
  phone_number_2 VARCHAR(20)
);

CREATE TABLE shop_schema.Auction (
  auction_id BIGINT        NOT NULL,
  user_id    BIGINT        NOT NULL,
  product_id BIGINT        NOT NULL,
  bid        DECIMAL(2, 2) NOT NULL,
  bid_time   TIMESTAMP     NOT NULL
);

CREATE TABLE shop_schema.Authority (
  authority_id BIGINT       NOT NULL,
  authority    VARCHAR(255) NOT NULL
);

CREATE TABLE shop_schema.Bought_Product (
  user_id    BIGINT        NOT NULL,
  product_id BIGINT        NOT NULL,
  count      INTEGER       NOT NULL,
  date       TIMESTAMP     NOT NULL,
  price      DECIMAL(2, 2) NOT NULL,
  comment_id BIGINT
);

CREATE TABLE shop_schema.Cart_Product (
  cart_id              BIGINT NOT NULL,
  product_id           BIGINT NOT NULL,
  count                INTEGER,
  selected_delivery_id BIGINT NOT NULL
);

CREATE TABLE shop_schema.Category (
  id                    BIGINT       NOT NULL,
  name                  VARCHAR(255) NOT NULL,
  supperior_category_id BIGINT
);

CREATE TABLE shop_schema.Comment (
  id                  BIGINT NOT NULL,
  text                TEXT   NOT NULL,
  inferior_comment_id BIGINT,
  rate                DECIMAL(2, 2),
  user_id             BIGINT NOT NULL
);

CREATE TABLE shop_schema.Delivery (
  id                    BIGINT            NOT NULL,
  name                  VARCHAR(255)      NOT NULL,
  type_id               BIGINT            NOT NULL,
  first_piece_price     DECIMAL(2, 2)     NOT NULL,
  next_piece_price      DECIMAL(2, 2),
  max_pieces_in_package INTEGER DEFAULT 1 NOT NULL
);

CREATE TABLE shop_schema.Delivery_Type (
  id                        BIGINT       NOT NULL,
  name                      VARCHAR(255) NOT NULL,
  first_piece_default_price DECIMAL(2, 2),
  max_pieces_in_package     INTEGER
);

CREATE TABLE shop_schema.Observed_Product (
  user_id    BIGINT NOT NULL,
  product_id BIGINT NOT NULL
);

CREATE TABLE shop_schema.Payment (
  id                          BIGINT                NOT NULL,
  name                        VARCHAR(255)          NOT NULL,
  on_delivery                 BOOLEAN DEFAULT FALSE NOT NULL,
  bank_accunt_number          VARCHAR(255),
  seller_pays_for_consignment BOOLEAN DEFAULT FALSE NOT NULL,
  seller_gives_VAT_invoice    BOOLEAN DEFAULT FALSE NOT NULL,
  sending_after               TIMESTAMP
);

CREATE TABLE shop_schema.Photo (
  id        BIGINT       NOT NULL,
  name      VARCHAR(255) NOT NULL,
  data      BYTEA        NOT NULL,
  extension VARCHAR(10)  NOT NULL
);

CREATE TABLE shop_schema.Product (
  id                BIGINT                NOT NULL,
  category_id       BIGINT                NOT NULL,
  state_id          BIGINT                NOT NULL,
  weight            DECIMAL(4, 2),
  is_auction        BOOLEAN               NOT NULL,
  product_count     BIGINT                NOT NULL,
  price             DECIMAL(2, 2)         NOT NULL,
  infinity_end_time BOOLEAN DEFAULT FALSE NOT NULL,
  end_time          TIMESTAMP,
  start_date        TIMESTAMP             NOT NULL,
  payment_id        BIGINT                NOT NULL,
  EAN               VARCHAR(255),
  description       TEXT,
  seller_id         BIGINT                NOT NULL,
  comment_id        BIGINT
);

CREATE TABLE shop_schema.Product_Delivery (
  product_id BIGINT NOT NULL,
  deliver_id BIGINT NOT NULL
);

CREATE TABLE shop_schema.Product_Photo (
  photo_id   BIGINT NOT NULL,
  product_id BIGINT NOT NULL
);

CREATE TABLE shop_schema.Product_State (
  id         BIGINT NOT NULL,
  state_name BIGINT NOT NULL
);

CREATE TABLE shop_schema.Shopping_Cart (
  id              BIGINT    NOT NULL,
  creation_date   TIMESTAMP NOT NULL,
  user_id         BIGINT    NOT NULL,
  expiration_date TIMESTAMP NOT NULL
);

CREATE TABLE shop_schema.User (
  user_id                  BIGINT                NOT NULL,
  username                 VARCHAR(255)          NOT NULL,
  password                 VARCHAR(255)          NOT NULL,
  name                     VARCHAR(255)          NOT NULL,
  surname                  VARCHAR(100)          NOT NULL,
  birth_year               TIMESTAMP             NOT NULL,
  expiration_time          TIMESTAMP,
  account_locked           BOOLEAN DEFAULT FALSE NOT NULL,
  password_expiration_time TIMESTAMP,
  user_enabled             BOOLEAN DEFAULT TRUE  NOT NULL
);

CREATE TABLE shop_schema.User_Authority (
  user_id      BIGINT NOT NULL,
  authority_id BIGINT NOT NULL
);

--  Create Primary Key Constraints
ALTER TABLE shop_schema.Adress
  ADD CONSTRAINT PK_Adress
PRIMARY KEY (id);


ALTER TABLE shop_schema.Auction
  ADD CONSTRAINT PK_Auction
PRIMARY KEY (auction_id);


ALTER TABLE shop_schema.Authority
  ADD CONSTRAINT PK_Authority
PRIMARY KEY (authority_id);


ALTER TABLE shop_schema.Bought_Product
  ADD CONSTRAINT PK_Bought_Product
PRIMARY KEY (user_id, product_id);


ALTER TABLE shop_schema.Cart_Product
  ADD CONSTRAINT PK_Cart_Product
PRIMARY KEY (cart_id, product_id);


ALTER TABLE shop_schema.Category
  ADD CONSTRAINT PK_Category
PRIMARY KEY (id);


ALTER TABLE shop_schema.Comment
  ADD CONSTRAINT PK_Comment
PRIMARY KEY (id);


ALTER TABLE shop_schema.Delivery
  ADD CONSTRAINT PK_Delivery
PRIMARY KEY (id);


ALTER TABLE shop_schema.Delivery_Type
  ADD CONSTRAINT PK_Delivery_Type
PRIMARY KEY (id);


ALTER TABLE shop_schema.Observed_Product
  ADD CONSTRAINT PK_Observed_Product
PRIMARY KEY (user_id, product_id);


ALTER TABLE shop_schema.Payment
  ADD CONSTRAINT PK_Payment
PRIMARY KEY (id);


ALTER TABLE shop_schema.Photo
  ADD CONSTRAINT PK_Photo
PRIMARY KEY (id);


ALTER TABLE shop_schema.Product
  ADD CONSTRAINT PK_Product
PRIMARY KEY (id);


ALTER TABLE shop_schema.Product_Delivery
  ADD CONSTRAINT PK_Product_Delivery
PRIMARY KEY (product_id, deliver_id);


ALTER TABLE shop_schema.Product_Photo
  ADD CONSTRAINT PK_Product_Photo
PRIMARY KEY (photo_id, product_id);


ALTER TABLE shop_schema.Product_State
  ADD CONSTRAINT PK_Product_State
PRIMARY KEY (id);


ALTER TABLE shop_schema.Shopping_Cart
  ADD CONSTRAINT PK_Shopping_Cart
PRIMARY KEY (id);


ALTER TABLE shop_schema.User
  ADD CONSTRAINT PK_User
PRIMARY KEY (user_id);


ALTER TABLE shop_schema.User_Authority
  ADD CONSTRAINT PK_User_Authority
PRIMARY KEY (user_id, authority_id);

--  Create Indexes
ALTER TABLE shop_schema.User
  ADD CONSTRAINT UQ_User_username UNIQUE (username);

--  Create Foreign Key Constraints 
ALTER TABLE shop_schema.Adress
  ADD CONSTRAINT FK_Adress_User
FOREIGN KEY (user_id) REFERENCES shop_schema.User (user_id);

ALTER TABLE shop_schema.Auction
  ADD CONSTRAINT FK_Auction_Product
FOREIGN KEY (product_id) REFERENCES shop_schema.Product (id);

ALTER TABLE shop_schema.Auction
  ADD CONSTRAINT FK_Auction_User
FOREIGN KEY (user_id) REFERENCES shop_schema.User (user_id);

ALTER TABLE shop_schema.Bought_Product
  ADD CONSTRAINT FK_Bought_Product_Comment
FOREIGN KEY (comment_id) REFERENCES shop_schema.Comment (id);

ALTER TABLE shop_schema.Bought_Product
  ADD CONSTRAINT FK_Bought_Product_Product
FOREIGN KEY (product_id) REFERENCES shop_schema.Product (id);

ALTER TABLE shop_schema.Bought_Product
  ADD CONSTRAINT FK_Bought_Product_User
FOREIGN KEY (user_id) REFERENCES shop_schema.User (user_id);

ALTER TABLE shop_schema.Cart_Product
  ADD CONSTRAINT FK_Cart_Product_Delivery
FOREIGN KEY (selected_delivery_id) REFERENCES shop_schema.Delivery (id);

ALTER TABLE shop_schema.Cart_Product
  ADD CONSTRAINT FK_Cart_Product_Product
FOREIGN KEY (product_id) REFERENCES shop_schema.Product (id);

ALTER TABLE shop_schema.Cart_Product
  ADD CONSTRAINT FK_Cart_Product_Shopping_Cart
FOREIGN KEY (cart_id) REFERENCES shop_schema.Shopping_Cart (id);

ALTER TABLE shop_schema.Category
  ADD CONSTRAINT FK_Category_Category
FOREIGN KEY (supperior_category_id) REFERENCES shop_schema.Category (id);

ALTER TABLE shop_schema.Comment
  ADD CONSTRAINT FK_Comment_Comment
FOREIGN KEY (inferior_comment_id) REFERENCES shop_schema.Comment (id);

ALTER TABLE shop_schema.Comment
  ADD CONSTRAINT FK_Comment_User
FOREIGN KEY (user_id) REFERENCES shop_schema.User (user_id);

ALTER TABLE shop_schema.Delivery
  ADD CONSTRAINT FK_Delivery_Delivery_Type
FOREIGN KEY (id) REFERENCES shop_schema.Delivery_Type (id);

ALTER TABLE shop_schema.Observed_Product
  ADD CONSTRAINT FK_Observed_Product_Product
FOREIGN KEY (product_id) REFERENCES shop_schema.Product (id);

ALTER TABLE shop_schema.Observed_Product
  ADD CONSTRAINT FK_Observed_Product_User
FOREIGN KEY (user_id) REFERENCES shop_schema.User (user_id);

ALTER TABLE shop_schema.Product
  ADD CONSTRAINT FK_Product_Category
FOREIGN KEY (id) REFERENCES shop_schema.Category (id);

ALTER TABLE shop_schema.Product
  ADD CONSTRAINT FK_Product_Comment
FOREIGN KEY (comment_id) REFERENCES shop_schema.Comment (id);

ALTER TABLE shop_schema.Product
  ADD CONSTRAINT FK_Product_Payment
FOREIGN KEY (payment_id) REFERENCES shop_schema.Payment (id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE shop_schema.Product
  ADD CONSTRAINT FK_Product_Product_State
FOREIGN KEY (state_id) REFERENCES shop_schema.Product_State (id);

ALTER TABLE shop_schema.Product
  ADD CONSTRAINT FK_Product_User
FOREIGN KEY (seller_id) REFERENCES shop_schema.User (user_id);

ALTER TABLE shop_schema.Product_Delivery
  ADD CONSTRAINT FK_Product_Delivery_Delivery
FOREIGN KEY (product_id) REFERENCES shop_schema.Delivery (id);

ALTER TABLE shop_schema.Product_Delivery
  ADD CONSTRAINT FK_Product_Delivery_Product
FOREIGN KEY (product_id) REFERENCES shop_schema.Product (id);

ALTER TABLE shop_schema.Product_Photo
  ADD CONSTRAINT FK_Product_Photo_Photo
FOREIGN KEY (product_id) REFERENCES shop_schema.Photo (id);

ALTER TABLE shop_schema.Product_Photo
  ADD CONSTRAINT FK_Product_Photo_Product
FOREIGN KEY (photo_id) REFERENCES shop_schema.Product (id);

ALTER TABLE shop_schema.Shopping_Cart
  ADD CONSTRAINT FK_Shopping_Cart_User
FOREIGN KEY (user_id) REFERENCES shop_schema.User (user_id);

ALTER TABLE shop_schema.User_Authority
  ADD CONSTRAINT FK_User_Authority_Authority
FOREIGN KEY (authority_id) REFERENCES shop_schema.Authority (authority_id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE shop_schema.User_Authority
  ADD CONSTRAINT FK_User_Authority_User
FOREIGN KEY (user_id) REFERENCES shop_schema.User (user_id)
ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO shop_schema.user VALUES (1, 'admin', 'admin', 'admin', 'admin', to_date('1994-10-10', 'YYYY-MM-DD'),
                                     to_date('2100-01-01 00:00', 'YYYY-MM-DD HH24:MI'), FALSE,
                                     to_date('2100-01-01 00:00', 'YYYY-MM-DD HH24:MI'), TRUE);

INSERT INTO shop_schema.authority VALUES (1, 'ROLE_ADMIN');
INSERT INTO shop_schema.authority VALUES (2, 'ROLE_USER');

INSERT INTO shop_schema.user_authority VALUES (1, 1);