CREATE TABLE public._blood_bank_series
(
    name character varying(255) NOT NULL,
    next_series bigint,
    PRIMARY KEY (name)
);

CREATE TABLE public._user
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'),
    user_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(50),
    enable_yn boolean DEFAULT true,
    PRIMARY KEY (id),
    CONSTRAINT idx_user_name UNIQUE (user_name)
);

CREATE TABLE public.token_seq
(
    next_val bigint
);

CREATE TABLE public._token
(
    id bigint NOT NULL DEFAULT nextval('token_id_seq'),
    user_id bigint,
    token character varying(255),
    token_type character varying(255),
    expired boolean NOT NULL DEFAULT false,
    revoked boolean NOT NULL DEFAULT false,
    PRIMARY KEY (id),
    CONSTRAINT fk_token_user_id FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE public._hospital (
  id bigint NOT NULL DEFAULT nextval('hospital_id_seq'),
  hospital_name character varying(255) NOT NULL,
  login_name character varying(8) NOT NULL,
  email character varying(255) NOT NULL,
  contact_number character varying(10) NOT NULL,
  address_line character varying(4096) NOT NULL,
  city character varying(255) NOT NULL,
  state character varying(255) NOT NULL,
  pincode character varying(6) NOT NULL,
  created_by character varying(255) NOT NULL,
  creation_ts TIMESTAMP NOT NULL,
  last_modification_ts TIMESTAMP DEFAULT NULL,
  last_modified_by character varying(255) DEFAULT NULL,
  version bigint DEFAULT '0',
  PRIMARY KEY (id),
  CONSTRAINT fk_hos_login_name FOREIGN KEY (login_name)
        REFERENCES public."user" (user_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE public._reciever (
  id bigint NOT NULL DEFAULT nextval('reciever_id_seq'),
  name character varying(255) NOT NULL,
  login_name character varying(8) NOT NULL,
  email character varying(255) NOT NULL,
  mobile_no character varying(10) NOT NULL,
  address_line character varying(4096) NOT NULL,
  city character varying(255) NOT NULL,
  state character varying(255) NOT NULL,
  pincode character varying(6) NOT NULL,
  blood_group character varying(12) NOT NULL,
  created_by character varying(255) NOT NULL,
  creation_ts TIMESTAMP NOT NULL,
  last_modification_ts TIMESTAMP DEFAULT NULL,
  last_modified_by character varying(255) DEFAULT NULL,
  version bigint DEFAULT '0',
  PRIMARY KEY (id),
  CONSTRAINT fk_rec_login_name FOREIGN KEY (login_name)
        REFERENCES public."user" (user_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE _blood_stock (
  id bigint NOT NULL DEFAULT nextval('blood_stock_id_seq'),
  hospital_id bigint NOT NULL,
  blood_group character varying(12) NOT NULL,
  stock_count bigint NOT NULL DEFAULT '0',
  created_by character varying(255) NOT NULL,
  creation_ts TIMESTAMP NOT NULL,
  last_modification_ts TIMESTAMP DEFAULT NULL,
  last_modified_by character varying(255) DEFAULT NULL,
  version bigint DEFAULT '0',
  PRIMARY KEY (id),
  CONSTRAINT fk_bstock_hospital_id FOREIGN KEY (hospital_id)
        REFERENCES public."hospital" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE _requests (
  id bigint NOT NULL DEFAULT nextval('request_id_seq'),
  reciever_id bigint NOT NULL,
  patient_name character varying(255) NOT NULL,
  request_ref_no character varying(255) NOT NULL,
  patient_email character varying(255) NOT NULL,
  hospital_id bigint NOT NULL,
  blood_group character varying(12) NOT NULL,
  bottles bigint NOT NULL,
  status boolean NOT NULL DEFAULT false,
  created_by character varying(255) NOT NULL,
  creation_ts TIMESTAMP NOT NULL,
  last_modification_ts TIMESTAMP DEFAULT NULL,
  last_modified_by character varying(255) DEFAULT NULL,
  version bigint DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_req_hospital_id FOREIGN KEY (hospital_id)
        REFERENCES public."hospital" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
  CONSTRAINT fk_req_reciever_id FOREIGN KEY (reciever_id)
        REFERENCES public."reciever" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public._requests
    ALTER COLUMN status TYPE character varying(10);

ALTER TABLE IF EXISTS public._requests
    ADD COLUMN documents_uploaded_yn boolean NOT NULL DEFAULT false;