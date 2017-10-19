package task_four.second;

import task_four.second.comporator.AuthorComparator;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamChallenge {

    private int getAge(LocalDate after, LocalDate before) {
        return Period.between(before, after).getYears();
    }

    public void printBooksWithAuthors(List<Book> books) {
        books.forEach(book -> {
                    System.out.print("Book: " + book.getName() +
                            " Authors: ");
                    book.getAuthors().forEach(s ->
                            System.out.print(s.getName() + " "));
                    System.out.println();
                });
    }

    public long averageAuthorYear(List<Author> authors) {
        OptionalDouble optional = authors.stream()
                .mapToInt(a -> {
                    if (a.isDead()) {
                        return getAge(a.getDateOfDeath(), a.getDateOfBirth());
                    } else {
                        return getAge(LocalDate.now(), a.getDateOfBirth());
                    }
                }).average();
        return Math.round(optional.getAsDouble());
    }

    public void sortAuthorByAgeAscending(List<Author> authors) {
        authors.stream()
                .sorted(new AuthorComparator());
    }

    public List<Author> listOfAuthorsOfPensioners(List<Author> authors) {
        return authors.stream()
                .filter(a -> a.getDateOfDeath() == null)
                .filter(a -> (a.getSex() == Author.Sex.MALE &&
                        getAge(LocalDate.now(), a.getDateOfBirth()) > 65) ||
                        (a.getSex() == Author.Sex.FEMALE &&
                                getAge(LocalDate.now(), a.getDateOfBirth()) > 63)
                ).collect(Collectors.toList());
    }

    public Map<String,Integer> listOfBooksWithAge(List<Book> books) {
        return books
                .stream()
                .collect(Collectors.toMap(Book::getName,
                        book -> getAge(LocalDate.now(),book.getReleaseDate())));
    }

    public Set<Author> listOfAuthorsWithCoAuthors(List<Book> books) {
        return books.stream()
                .filter(book -> book.getAuthors().size()>1)
                .flatMap(book -> book.getAuthors().stream())
                .collect(Collectors.toSet());
    }

    public Map<Author, List<Wrapper>> listAuthorAndBooks(List<Book> books) {
        return books.stream()
                .flatMap(book -> book.getAuthors().stream()
                        .map(author -> new Wrapper(book.getName(),author)))
                .collect(Collectors.groupingBy(o -> o.author));
    }

    static class Wrapper {
        private String name;
        private Author author;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        private Wrapper(String name, Author author) {
            this.name = name;
            this.author = author;
        }
    }

}
