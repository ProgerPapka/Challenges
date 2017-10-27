package task_four.second.domain;

import java.io.Serializable;
import java.util.List;

public class Publisher implements Serializable{

    private final String name;
    private List<Book> publishedBooks;

    public Publisher(String name, List<Book> publishedBooks) {
        this.name = name;
        this.publishedBooks = publishedBooks;
    }

    public String getName() {
        return name;
    }

    public List<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void addPublishedBook(Book book) {
        publishedBooks.add(book);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", publishedBooks=" + publishedBooks +
                '}';
    }
}
