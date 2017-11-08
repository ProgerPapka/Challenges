CREATE TABLE Author
(
    id integer NOT NULL,
    name character varying(200) NOT NULL,
    birth_day date NOT NULL,
    death_day date,
    sex character varying(8) NOT NULL,
    CONSTRAINT author_pkey PRIMARY KEY (id)
);
ALTER TABLE Author
    OWNER to epam;