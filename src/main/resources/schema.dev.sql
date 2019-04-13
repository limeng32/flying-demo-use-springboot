DROP TABLE IF EXISTS account_;
CREATE TABLE account_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  email varchar(320) DEFAULT NULL,
  password varchar(64) DEFAULT NULL,
  activated tinyint(1) DEFAULT NULL,
  activateValue varchar(8) DEFAULT NULL,
  opLock int(11) DEFAULT NULL,
  status char(1) DEFAULT NULL,
  role_id int(11) DEFAULT NULL,
  deputy_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=456 DEFAULT CHARSET=utf8;

drop table if exists CART;

drop table if exists COMMODITY;

drop table if exists CART_COMMODITY;

create table CART
(
   ID         			varchar(40) not null ,
   DEAL                 tinyint ,
   DEAL_TIME            datetime ,
   PERSON_ID			varchar(40),
   primary key (ID)
);

create table COMMODITY
(
   ID         			varchar(40) not null ,
   NAME                 varchar(64) ,
   PRICE            	integer ,
   primary key (ID)
);

create table CART_COMMODITY
(
   ID         			bigint not null ,
   CART_ID              varchar(40) ,
   COMM_ID				varchar(40) ,
   AMOUNT            	integer ,
   primary key (ID)
);

drop table if exists ROLE;

drop table if exists PERSON;

create table ROLE
(
   ID         			varchar(40) not null ,
   NAME                 varchar(40) ,
   VALUE            	varchar(20) ,
   primary key (ID)
);

create table PERSON
(
   _ID         			varchar(40) not null ,
   NAME                 varchar(40) ,
   ROLE_ID            	varchar(40) ,
   primary key (_ID)
);