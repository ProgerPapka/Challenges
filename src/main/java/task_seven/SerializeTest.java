package task_seven;

import org.apache.log4j.Logger;

import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_seven.exception.SerializeException;
import task_seven.serialization.ObjectSerializer;
import task_seven.serialization.StandardSerializer;
import task_seven.serialization.TextSerializer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SerializeTest {

    private static Logger logger = Logger.getLogger(SerializeTest.class);

    private List<Author> authors;
    private List<Book> books;
    private List<Publisher> publishers;

    private SerializeTest() {
        initTestData();
    }

    private void processTestStandardSerialize() throws IOException {
        ObjectSerializer serializer = new StandardSerializer();
        File fileAuthor = new File("author.dat");
        File fileBook = new File("book.dat");
        File filePublisher = new File("publisher.dat");
        if (!fileAuthor.exists()) {
            fileAuthor.createNewFile();
        }
        if (!fileBook.exists()) {
            fileBook.createNewFile();
        }
        if (!filePublisher.exists()) {
            filePublisher.createNewFile();
        }
        logger.info("Standard serialize:");
        try {
            logger.info(authors.get(0).toString());
            serializer.writeAuthor(authors.get(0), fileAuthor);
            logger.info(serializer.readAuthor(fileAuthor).toString());
            logger.info("\n");

            logger.info(books.get(0).toString());
            serializer.writeBook(books.get(0), fileBook);
            logger.info(serializer.readBook(fileBook).toString());
            logger.info("\n");

            logger.info(publishers.get(0).toString());
            serializer.writePublisher(publishers.get(0), filePublisher);
            logger.info(serializer.readPublisher(filePublisher).toString());
            logger.info("\n");
        } catch (SerializeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void processTestTextSerialize() throws IOException {
        File fileAuthor = new File("author.txt");
        if (!fileAuthor.exists()) {
            fileAuthor.createNewFile();
        }
        logger.info("Text serialize:");
        try {
            Author james = new Author("James",
                    LocalDate.of(1995, 8, 12),
                    Author.Sex.MALE);
            ObjectSerializer serializer = new TextSerializer();
            serializer.writeAuthor(james, fileAuthor);
            Author jamesTwo = serializer.readAuthor(fileAuthor);
            logger.info(james.toString());
            logger.info(jamesTwo.toString());
            logger.info("\n");

            Author anna = new Author("Anna",
                    LocalDate.of(1922, 6, 15),
                    LocalDate.of(1923, 1, 22),
                    Author.Sex.FEMALE);
            Author masha = new Author("Masha",
                    LocalDate.of(1934, 3, 6),
                    LocalDate.of(1976, 1, 22),
                    Author.Sex.FEMALE);
            List<Author> authorsBookBig = Arrays.asList(anna, masha);
            Book bigBan = new Book(
                    "BigBan", LocalDate.now(), authors
            );
            serializer.writeBook(bigBan, fileAuthor);
            Book bigBanTwo = serializer.readBook(fileAuthor);
            logger.info(bigBan.toString());
            logger.info(bigBanTwo.toString());
            logger.info("\n");

            Book boo = new Book(
                    "Boo", LocalDate.now(), this.authors
            );
            List<Book> publishBooks = Arrays.asList(bigBan, boo);
            Publisher scrabble = new Publisher(
                    "Scrabble", publishBooks
            );
            serializer.writePublisher(scrabble, fileAuthor);
            Publisher scrabbleTwo = serializer.readPublisher(fileAuthor);
            logger.info(scrabble);
            logger.info(scrabbleTwo);
            logger.info(scrabble.equals(scrabbleTwo));
        } catch (SerializeException e) {
            logger.error(e);
        }
    }

    private void initTestData() {
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
                Arrays.asList(authors.get(5), authors.get(6));
        List<Author> listAuthorsBookTheGameOfTheGods =
                Arrays.asList(authors.get(7), authors.get(8));
        List<Author> listAuthorsBookLifeStealer =
                Arrays.asList(authors.get(9), authors.get(10));
        List<Author> listAuthorsBookSecretTime =
                Arrays.asList(authors.get(1), authors.get(5), authors.get(6));
        List<Author> listAuthorsBooksGalaxy =
                Arrays.asList(authors.get(1), authors.get(3), authors.get(6));
        List<Author> listAuthorsBookEllion =
                Arrays.asList(authors.get(0), authors.get(9),
                        authors.get(10), authors.get(2));
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
        List<Book> booksOfWorldPublisher = Arrays.asList(
                books.get(0), books.get(1),
                books.get(2), books.get(3)
        );
        List<Book> booksOfEksmoPublisher = Arrays.asList(
                books.get(4), books.get(5), books.get(6)
        );
        publishers = new ArrayList<>();
        publishers.add(new Publisher("World", booksOfWorldPublisher));
        publishers.add(new Publisher("Eksmo", booksOfEksmoPublisher));
    }

    public static void main(String[] args) {
        try {
            SerializeTest serializeTest = new SerializeTest();
            serializeTest.processTestStandardSerialize();
            serializeTest.processTestTextSerialize();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
