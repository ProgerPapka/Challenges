CREATE TABLE Book_Author
(
    id_book integer NOT NULL,
    id_author integer NOT NULL,
    CONSTRAINT id_author_fk FOREIGN KEY (id_author)
        REFERENCES Author (id),
    CONSTRAINT id_book_fk FOREIGN KEY (id_book)
        REFERENCES Book (id)
);
ALTER TABLE Book_Author
    OWNER to epam;