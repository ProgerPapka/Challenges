CREATE TABLE Book
(
    id integer NOT NULL,
    name character varying(200) NOT NULL,
    release_day date NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (id)
);
ALTER TABLE Book
    OWNER to epam;