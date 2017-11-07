CREATE TABLE public."Book"
(
    id integer NOT NULL,
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    r_day date NOT NULL,
    CONSTRAINT "Book_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Book"
    OWNER to epam;