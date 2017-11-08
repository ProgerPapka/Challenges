CREATE TABLE Publisher_Book
(
    id_publisher integer NOT NULL,
    id_book integer NOT NULL,
    CONSTRAINT id_book_fk FOREIGN KEY (id_book)
        REFERENCES Book (id),
    CONSTRAINT id_publisher_fk FOREIGN KEY (id_publisher)
        REFERENCES Publisher (id)
);
ALTER TABLE Publisher_Book
    OWNER to epam;