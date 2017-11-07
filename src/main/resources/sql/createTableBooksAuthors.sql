CREATE TABLE public."Books_Authors"
(
    id_book integer NOT NULL,
    id_author integer NOT NULL,
    CONSTRAINT id_author_fk FOREIGN KEY (id_author)
        REFERENCES public."Author" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_book_fk FOREIGN KEY (id_book)
        REFERENCES public."Book" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Books_Authors"
    OWNER to epam;