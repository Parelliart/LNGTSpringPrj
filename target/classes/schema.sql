
create table LN_REQUEST
(
  id                     NUMBER(12) not null,
--   REQUEST_DATE           DATE not null,
--   REGISTER_DATE          TIMESTAMP(6),
  DESCRIPTION            VARCHAR2(255),
  PROCESS_TYPE_ID        NUMBER(10) not null,
  STATUS_ID              NUMBER(10) not null,
  CONTRACT_ID            NUMBER(15),
  DTYPE                  NUMBER(2)  not null,
--   insert_date            TIMESTAMP(6),
)
;

create table GUARANTEE_REQUEST
(
  id                     NUMBER(12) not null,
  STAKEHOLDER_NUMBER     VARCHAR2(20),
  STAKEHOLDER_DATE       DATE,
  STAKEHOLDER_SUBJECT    VARCHAR2(50),
  SEPAM_CODE             VARCHAR2(16),
  CUSTOMS_GUARANTEE_NUMBER VARCHAR2(20),
  IS_IRIB_CUSTOMS        NUMBER(1),
  GUARANTEE_TYPE         NUMBER(12)
)
;

create sequence hibernate_sequence
    start with  1
    increment by  1
    cache 20;