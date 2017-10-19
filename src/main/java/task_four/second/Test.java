package task_four.second;

import java.time.LocalDate;
import java.util.*;

public class Test {

    private List<Author> authors;
    private List<Book> books;


    public void runTest() {
        initAuthorsAndBooks();
        StreamChallenge streamChallenge = new StreamChallenge();

        System.out.println("Average year of authors:");
        System.out.println(streamChallenge.averageAuthorYear(authors));
        System.out.println();

        System.out.println("Sorted author by age:");
        streamChallenge.sortAuthorByAgeAscending(authors);
        authors.forEach(author -> System.out.println(author.getName()));
        System.out.println();

        System.out.println("List pensioners:");
        List<Author> pensioners = streamChallenge
                .listOfAuthorsOfPensioners(authors);
        pensioners.forEach(author -> System.out.println(author.getName()));
        System.out.println();

        System.out.println("All books:");
        streamChallenge.printBooksWithAuthors(books);
        System.out.println();

        System.out.println("List books with it of age:");
        Map<String,Integer> map = streamChallenge.listOfBooksWithAge(books);
        map.forEach((n,a) -> System.out.println("Name: " + n + " Age: " + a));
        System.out.println();

        System.out.println("List co-authors:");
        Set<Author> authors = streamChallenge.listOfAuthorsWithCoAuthors(books);
        authors.forEach(author -> System.out.println(author.getName()));
        System.out.println();

        System.out.println("List author an her books:");
        Map<Author,List<StreamChallenge.Wrapper>> mapWrapper =
                streamChallenge.listAuthorAndBooks(books);
        mapWrapper.forEach((author, wrappers) -> {
            System.out.printf(author.getName() + ": ");
            wrappers.forEach(wrapper -> System.out.printf(wrapper.getName() + "; "));
            System.out.println();
        });
    }

    private void initAuthorsAndBooks() {
        authors = new ArrayList<>();
        {
            authors.add(new Author("James",
                    LocalDate.of(1995, 8, 12),
                    Author.Sex.MALE));
            authors.add(new Author("Sue",
                    LocalDate.of(1940, 7, 11),
                    LocalDate.of(2002, 1, 1),
                    Author.Sex.FEMALE));
            authors.add(new Author("Hank",
                    LocalDate.of(1980, 2, 28),
                    Author.Sex.MALE));
            authors.add(new Author("Anna",
                    LocalDate.of(1911, 3, 13),
                    LocalDate.of(1988, 11, 22),
                    Author.Sex.FEMALE));
            authors.add(new Author("Harry",
                    LocalDate.of(1807, 3, 13),
                    LocalDate.of(1895, 11, 22),
                    Author.Sex.MALE));
            authors.add(new Author("Sara",
                    LocalDate.of(1900, 7, 14),
                    LocalDate.of(1985, 5, 15),
                    Author.Sex.FEMALE));
            authors.add(new Author("Mike",
                    LocalDate.of(1895, 8, 21),
                    LocalDate.of(1967, 6, 18),
                    Author.Sex.MALE));
            authors.add(new Author("David",
                    LocalDate.of(1703, 11, 15),
                    LocalDate.of(1788, 12, 13),
                    Author.Sex.MALE));
            authors.add(new Author("Mariya",
                    LocalDate.of(1699, 7, 11),
                    LocalDate.of(1777, 1, 5),
                    Author.Sex.FEMALE));
            authors.add(new Author("Charlotte",
                    LocalDate.of(1995, 12, 11),
                    Author.Sex.MALE));
            authors.add(new Author("Emma",
                    LocalDate.of(1992, 7, 30),
                    Author.Sex.FEMALE));
        }
        List<Author> listAuthorsBookThreeOfAFool =
                Arrays.asList(authors.get(5),authors.get(6));
        List<Author> listAuthorsBookTheGameOfTheGods =
                Arrays.asList(authors.get(7),authors.get(8));
        List<Author> listAuthorsBookLifeStealer =
                Arrays.asList(authors.get(9),authors.get(10));
        List<Author> listAuthorsBookSecretTime =
                Arrays.asList(authors.get(1),authors.get(5),authors.get(6));
        List<Author> listAuthorsBooksGalaxy =
                Arrays.asList(authors.get(1),authors.get(3),authors.get(6));
        List<Author> listAuthorsBookEllion =
                Arrays.asList(authors.get(0),authors.get(9),
                        authors.get(10),authors.get(2));
        List<Author> listAuthorsBookMyMemories =
                Collections.singletonList(authors.get(4));
        books = new ArrayList<>();
        books.add(new Book("Three of a fool",
                LocalDate.of(1925, 1, 1),
                listAuthorsBookThreeOfAFool));
        books.add(new Book("The game of the gods",
                LocalDate.of(1756, 3, 12),
                listAuthorsBookTheGameOfTheGods));
        books.add(new Book("Life Stealer",
                LocalDate.of(2002, 2, 22),
                listAuthorsBookLifeStealer));
        books.add(new Book("Secret time",
                LocalDate.of(1955, 4, 1),
                listAuthorsBookSecretTime));
        books.add(new Book("Galaxy",
                LocalDate.of(1960, 1, 1),
                listAuthorsBooksGalaxy));
        books.add(new Book("Ellion",
                LocalDate.of(2006, 11, 16),
                listAuthorsBookEllion));
        books.add(new Book("My memories",
                LocalDate.of(1888, 8, 8),
                listAuthorsBookMyMemories));
    }

}
