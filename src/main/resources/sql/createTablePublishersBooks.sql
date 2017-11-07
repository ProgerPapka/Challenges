CREATE TABLE public."Publishers_Books"
(
    id_publisher integer NOT NULL,
    id_book integer NOT NULL,
    CONSTRAINT id_book_fk FOREIGN KEY (id_book)
        REFERENCES public."Book" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_publisher_fk FOREIGN KEY (id_publisher)
        REFERENCES public."Publisher" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Publishers_Books"
    OWNER to epam;