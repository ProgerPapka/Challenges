CREATE TABLE Publisher
(
    id integer NOT NULL,
    name character varying(200) NOT NULL,
    CONSTRAINT publisher_pkey PRIMARY KEY (id)
);
ALTER TABLE Publisher
    OWNER to epam;