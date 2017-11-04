package task_four.second.domain;

import java.io.Serializable;
import java.time.Period;
import java.util.List;

public class Publisher implements Serializable{

    private final String name;
    private List<Book> publishedBooks;

    public Publisher(String name, List<Book> publishedBooks) {
        this.name = name;
        this.publishedBooks = publishedBooks;
        this.publishedBooks.sort((o1, o2) -> {
            int a = Period.between(o1.getReleaseDate(),o2.getReleaseDate())
                    .getDays();
            if (a > 0) {
                return 1;
            }
            if (a < 0) {
                return -1;
            }
            return o1.getName().compareTo(o2.getName());
        });
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        if (!name.equals(publisher.name)) return false;
        return publishedBooks.equals(publisher.publishedBooks);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + publishedBooks.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", publishedBooks=" + publishedBooks +
                '}';
    }
}
