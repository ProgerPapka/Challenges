CREATE TABLE public."Author"
(
    id integer NOT NULL,
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    b_day date NOT NULL,
    d_day date,
    sex character varying(8) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Author_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Author"
    OWNER to epam;