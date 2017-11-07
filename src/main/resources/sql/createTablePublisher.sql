CREATE TABLE public."Publisher"
(
    id integer NOT NULL,
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Publisher_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Publisher"
    OWNER to epam;