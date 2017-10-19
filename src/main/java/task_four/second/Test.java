package task_four.second;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    private List<Author> list;
    private List<Book> books;


    public void runTest() {
        initAuthorsAndBooks();

        System.out.println("Average year of authors:");
        averageAuthorYear();
        System.out.println();

        System.out.println("Sorted author by age:");
        sortAuthorByAgeAscending();
        System.out.println();

        System.out.println("List pensioners:");
        listOfAuthorsOfPensioners();
        System.out.println();

        System.out.println("List books with it of age:");
        listOfBooksWithAge();
        System.out.println();

        System.out.println("All books:");
        printBooksWithAuthors();
        System.out.println();

        System.out.println("List co-authors:");
        listOfAuthorsWithCoAuthors();
        System.out.println();

        System.out.println("List author an her books:");
        listAuthorAndBooks();
    }

    private void initAuthorsAndBooks() {
        list = new ArrayList<>();
        {
            list.add(new Author("James",
                    LocalDate.of(1995, 8, 12),
                    Author.Sex.MALE));
            list.add(new Author("Sue",
                    LocalDate.of(1940, 7, 11),
                    LocalDate.of(2002, 1, 1),
                    Author.Sex.FEMALE));
            list.add(new Author("Hank",
                    LocalDate.of(1980, 2, 28),
                    Author.Sex.MALE));
            list.add(new Author("Anna",
                    LocalDate.of(1911, 3, 13),
                    LocalDate.of(1988, 11, 22),
                    Author.Sex.FEMALE));
            list.add(new Author("Harry",
                    LocalDate.of(1807, 3, 13),
                    LocalDate.of(1895, 11, 22),
                    Author.Sex.MALE));
            list.add(new Author("Sara",
                    LocalDate.of(1900, 7, 14),
                    LocalDate.of(1985, 5, 15),
                    Author.Sex.FEMALE));
            list.add(new Author("Mike",
                    LocalDate.of(1895, 8, 21),
                    LocalDate.of(1967, 6, 18),
                    Author.Sex.MALE));
            list.add(new Author("David",
                    LocalDate.of(1703, 11, 15),
                    LocalDate.of(1788, 12, 13),
                    Author.Sex.MALE));
            list.add(new Author("Mariya",
                    LocalDate.of(1699, 7, 11),
                    LocalDate.of(1777, 1, 5),
                    Author.Sex.FEMALE));
            list.add(new Author("Charlotte",
                    LocalDate.of(1995, 12, 11),
                    Author.Sex.MALE));
            list.add(new Author("Emma",
                    LocalDate.of(1992, 7, 30),
                    Author.Sex.FEMALE));
        }
        List<Author> list1 = new ArrayList<>();
        List<Author> list2 = new ArrayList<>();
        List<Author> list3 = new ArrayList<>();
        List<Author> list4 = new ArrayList<>();
        List<Author> list5 = new ArrayList<>();
        List<Author> list6 = new ArrayList<>();
        List<Author> list7 = new ArrayList<>();
        {
            list1.add(list.get(5));
            list1.add(list.get(6));
        }
        {
            list2.add(list.get(7));
            list2.add(list.get(8));
        }
        {
            list3.add(list.get(9));
            list3.add(list.get(10));
        }
        {
            list4.add(list.get(1));
            list4.add(list.get(5));
            list4.add(list.get(6));
        }
        {
            list5.add(list.get(1));
            list5.add(list.get(3));
            list5.add(list.get(6));
        }
        {
            list6.add(list.get(0));
            list6.add(list.get(9));
            list6.add(list.get(10));
            list6.add(list.get(2));
        }
        {
            list7.add(list.get(4));
        }
        books = new ArrayList<>();
        books.add(new Book("Three of a fool",
                LocalDate.of(1925, 1, 1), list1));
        books.add(new Book("The game of the gods",
                LocalDate.of(1756, 3, 12), list2));
        books.add(new Book("Life Stealer",
                LocalDate.of(2002, 2, 22), list3));
        books.add(new Book("Secret time",
                LocalDate.of(1955, 4, 1), list4));
        books.add(new Book("Galaxy",
                LocalDate.of(1960, 1, 1), list5));
        books.add(new Book("Ellion",
                LocalDate.of(2006, 11, 16), list6));
        books.add(new Book("My memories",
                LocalDate.of(1888, 8, 8), list7));
    }

    private void printBooksWithAuthors() {
        books
                .stream()
                .forEach(book -> {
                    System.out.print("Book: " + book.getName() +
                            " Authors: ");
                    List<Author> list = book.getAuthors();
                    list.stream().forEach(s ->
                            System.out.print(s.getName() + " "));
                    System.out.println();
                });
    }

    private void averageAuthorYear() {
        list
                .stream()
                .mapToInt(value -> {
                    if (value.isDead()) {
                        return value.getDateOfDeath().getYear() -
                                value.getDateOfBirth().getYear();
                    } else {
                        return LocalDate.now().getYear() -
                                value.getDateOfBirth().getYear();
                    }
                }).average().ifPresent(s ->
        {
            System.out.println(Math.round(s));
        });
    }

    private void sortAuthorByAgeAscending() {
        list
                .stream()
                .sorted((o1, o2) -> {
                    int age1, age2;
                    if (o1.isDead()) {
                        age1 = o1.getDateOfDeath().getYear() -
                                o1.getDateOfBirth().getYear();
                    } else {
                        age1 = LocalDate.now().getYear() -
                                o1.getDateOfBirth().getYear();
                    }
                    if (o2.isDead()) {
                        age2 = o2.getDateOfDeath().getYear() -
                                o2.getDateOfBirth().getYear();
                    } else {
                        age2 = LocalDate.now().getYear() -
                                o2.getDateOfBirth().getYear();
                    }
                    if (age1 > age2) {
                        return 1;
                    } else {
                        return -1;
                    }

                }).forEach(s -> System.out.println(s.getName()));
    }

    private void listOfAuthorsOfPensioners() {
        list
                .stream()
                .forEach(author -> {
                    int age;
                    if (author.isDead()) {
                        age = author.getDateOfDeath().getYear() -
                                author.getDateOfBirth().getYear();
                    } else {
                        age = LocalDate.now().getYear() -
                                author.getDateOfBirth().getYear();
                    }
                    Author.Sex sex = author.getSex();
                    if ((sex == Author.Sex.MALE && age > 65) ||
                            (sex == Author.Sex.FEMALE && age > 63)) {
                        System.out.println(author.getName());
                    }
                });
    }

    private void listOfBooksWithAge() {
        books
                .stream()
                .forEach(book -> {
                    int age = LocalDate.now().getYear() -
                            book.getReleaseDate().getYear();
                    System.out.println("Name: " + book.getName() +
                            " Age: " + age);
                });
    }

    private void listOfAuthorsWithCoAuthors() {
        /*List<Author> list = new ArrayList<>();
        books
                .stream()
                .forEach(book -> {
                    if (book.getAuthors().size() > 1) {
                        book.getAuthors()
                                .stream()
                                .forEach(author -> {
                                    list.add(author);
                                });
                    }
                });
        list.stream().distinct().forEach(author ->
                System.out.println(author.getName()));*/

        books
                .stream()
                .filter(book -> book.getAuthors().size()>1)
                .flatMap(book -> book.getAuthors().stream())
                .collect(Collectors.toSet())
                .stream()
                .forEach(author -> System.out.println(author.getName()));
    }


    private void listAuthorAndBooks() {
        class A {
            String name;
            Author author;

            public A(String name, Author author) {
                this.name = name;
                this.author = author;
            }
        }
        /*List<A> list = new ArrayList<>();
        books
                .stream()
                .forEach(book -> {
                    book.getAuthors()
                            .stream()
                            .forEach(author -> {
                                list.add(new A(book.getName(), author));
                            });
                });
        list
                .stream()
                .collect(Collectors.groupingBy(o -> o.author))
                .forEach(((author, as) -> {
                    System.out.printf(author.getName() + ": ");
                    as.forEach(a -> System.out.printf(a.name + "; "));
                    System.out.println();
                }
                ));*/
        books
                .stream()
                .flatMap(book -> book.getAuthors().stream()
                        .map(author -> new A(book.getName(),author)))
                .collect(Collectors.groupingBy(o -> o.author))
                .forEach(((author, as) -> {
                    System.out.printf(author.getName() + ": ");
                    as.forEach(a -> System.out.printf(a.name + "; "));
                    System.out.println();
                }
                ));
    }
}
