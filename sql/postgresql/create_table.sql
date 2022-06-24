create table SUPPLIERS
(SUP_ID integer NOT NULL,
 SUP_NAME varchar(40) NOT NULL,
 STREET varchar(40) NOT NULL,
 CITY varchar(20) NOT NULL,
 STATE char(2) NOT NULL,
 ZIP char(5),
 PRIMARY KEY (SUP_ID));