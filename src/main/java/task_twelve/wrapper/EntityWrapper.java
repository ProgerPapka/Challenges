package task_twelve.wrapper;

import task_twelve.entity.*;

import java.util.List;

public class EntityWrapper {

    private List<EntityAuthor> authors;
    private List<EntityBook> books;
    private List<EntityPublisher> publishers;
    private List<EntityBookAuthor> booksAuthors;
    private List<EntityPublisherBook> publishersBooks;

    public EntityWrapper(List<EntityAuthor> authors,
                         List<EntityBook> books,
                         List<EntityPublisher> publishers,
                         List<EntityBookAuthor> booksAuthors,
                         List<EntityPublisherBook> publishersBooks) {
        this.authors = authors;
        this.books = books;
        this.publishers = publishers;
        this.booksAuthors = booksAuthors;
        this.publishersBooks = publishersBooks;
    }

    public List<EntityAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<EntityAuthor> authors) {
        this.authors = authors;
    }

    public List<EntityBook> getBooks() {
        return books;
    }

    public void setBooks(List<EntityBook> books) {
        this.books = books;
    }

    public List<EntityPublisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<EntityPublisher> publishers) {
        this.publishers = publishers;
    }

    public List<EntityBookAuthor> getBooksAuthors() {
        return booksAuthors;
    }

    public void setBooksAuthors(List<EntityBookAuthor> booksAuthors) {
        this.booksAuthors = booksAuthors;
    }

    public List<EntityPublisherBook> getPublishersBooks() {
        return publishersBooks;
    }

    public void setPublishersBooks(List<EntityPublisherBook> publishersBooks) {
        this.publishersBooks = publishersBooks;
    }
}
