package task_four.second;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private final String name;
    private final LocalDate releaseDate;
    private List<Author> authors;

    public Book(String name, LocalDate releaseDate, List<Author> authors) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.authors = authors;
    }

    public Book(String name, LocalDate releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
        authors = new ArrayList<>();
    }

    public void addAuthor(Author author){
        authors.add(author);
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
}
