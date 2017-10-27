package task_four.second.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Book implements Serializable{

    private final String name;
    private final LocalDate releaseDate;
    private List<Author> authors;

    public Book(String name, LocalDate releaseDate, List<Author> authors) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", authors=" + authors +
                '}';
    }
}
