CREATE TABLE Book_Author
(
    book_id integer NOT NULL,
    author_id integer NOT NULL,
    CONSTRAINT author_id_fk FOREIGN KEY (author_id)
        REFERENCES Author (id),
    CONSTRAINT book_id_fk FOREIGN KEY (book_id)
        REFERENCES Book (id)
);
ALTER TABLE Book_Author
    OWNER to epam;