CREATE TABLE SALARY(
SAL_ID NUMBER NOT NULL PRIMARY KEY,
DESIGNATION VARCHAR2(100),
AMOUNT NUMBER
);
 
CREATE TABLE USERS(
U_ID NUMBER PRIMARY KEY,
NAME VARCHAR2(100) NOT NULL,
PASSWORD VARCHAR2(50) NOT NULL,
EMAIL VARCHAR2(50) NOT NULL,
SAL_ID NUMBER ,
FOREIGN KEY (SAL_ID) REFERENCES SALARY(SAL_ID)
);

CREATE TABLE SALES(
SALE_ID NUMBER NOT NULL PRIMARY KEY,
SALE_DATE DATE, 
U_ID NUMBER  ,
FOREIGN KEY (U_ID) REFERENCES USERS(U_ID)
);

CREATE SEQUENCE SALES_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER SALES_BIR 
BEFORE INSERT ON SALES
FOR EACH ROW

BEGIN
  SELECT SALES_SEQ.NEXTVAL
  INTO   :new.SALE_ID
  FROM   dual;
END;
/
 
 
CREATE TABLE SUPPLY_ORDER(
S_ID NUMBER NOT NULL PRIMARY KEY ,
S_NAME VARCHAR2(50) NOT NULL ,
S_PRICE NUMBER NOT NULL,
S_QUANTITY NUMBER NOT NULL,
MRP NUMBER NOT NULL,
SUPPLIER VARCHAR2(100) NOT NULL,
SUP_DATE DATE NOT NULL
);
 
CREATE SEQUENCE SUPPLY_ORDER_SEQ START WITH 1; 
CREATE OR REPLACE TRIGGER SUPPLY_ORDER_BIR 
BEFORE INSERT ON SUPPLY_ORDER 
FOR EACH ROW
BEGIN
  SELECT SUPPLY_ORDER_SEQ.NEXTVAL
  INTO   :new.S_ID
  FROM   dual;
END;
/
 
 
CREATE TABLE PRODUCT(
P_ID VARCHAR(20) NOT NULL PRIMARY KEY,
NAME VARCHAR2(100) NOT NULL,
S_ID NUMBER ,
S_NAME VARCHAR2(50),
CONSTRAINT S_ID_FK FOREIGN KEY (S_ID) REFERENCES SUPPLY_ORDER(S_ID) 
);
 
CREATE TABLE SALES_DETAILS(
D_ID NUMBER  PRIMARY KEY,
P_ID VARCHAR(20) ,
SALE_ID NUMBER ,
P_QUANTITY NUMBER, 
CONSTRAINT SALE_ID_FK FOREIGN KEY (SALE_ID) REFERENCES SALES(SALE_ID),
CONSTRAINT P_ID_FK FOREIGN KEY (P_ID) REFERENCES PRODUCT(P_ID)
);

CREATE SEQUENCE SALES_DETAILS2_SEQ START WITH 1;
CREATE OR REPLACE TRIGGER SALES_DETAILS_BIR 
BEFORE INSERT ON SALES_DETAILS
FOR EACH ROW
BEGIN
  SELECT SALES_DETAILS2_SEQ.NEXTVAL
  INTO   :new.D_ID
  FROM   dual;
END;
/

CREATE TABLE EXPENSES (
E_ID NUMBER NOT NULL PRIMARY KEY,
PURPOSE VARCHAR2(100) NOT NULL,
SUP_DATE DATE NOT NULL,
DESCRIPTION VARCHAR2(200),
AMOUNT NUMBER NOT NULL
);
 