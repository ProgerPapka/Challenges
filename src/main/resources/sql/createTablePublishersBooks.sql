CREATE TABLE Publisher_Book
(
    publisher_id integer NOT NULL,
    book_id integer NOT NULL,
    CONSTRAINT book_id_fk FOREIGN KEY (book_id)
        REFERENCES Book (id),
    CONSTRAINT publisher_id_fk FOREIGN KEY (publisher_id)
        REFERENCES Publisher (id)
);
ALTER TABLE Publisher_Book
    OWNER to epam;