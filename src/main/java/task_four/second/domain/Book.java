package task_four.second.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

public class Book implements Serializable {

    private final String name;
    private final LocalDate releaseDate;
    private List<Author> authors;

    public Book(String name, LocalDate releaseDate, List<Author> authors) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.authors = authors;
        this.authors.sort((o1, o2) -> {
            int a = Period.between(o1.getDateOfBirth(), o2.getDateOfBirth())
                    .getDays();
            if (a > 0) {
                return 1;
            }
            if (a < 0) {
                return -1;
            }
            int b = Period.between(o1.getDateOfDeath(), o2.getDateOfDeath())
                    .getDays();
            if (b > 0) {
                return 1;
            }
            if (b < 0) {
                return -1;
            }
            return o1.getName().compareTo(o2.getName());
        });
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!name.equals(book.name)) return false;
        if (!releaseDate.equals(book.releaseDate)) return false;
        return authors.equals(book.authors);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + authors.hashCode();
        return result;
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
