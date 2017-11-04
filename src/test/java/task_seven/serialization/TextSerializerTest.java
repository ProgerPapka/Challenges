package task_seven.serialization;

import org.junit.BeforeClass;
import org.junit.Test;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TextSerializerTest {

    private static List<Author> authors;
    private static List<Book> books;
    private static List<Publisher> publishers;

    private static ObjectSerializer serializer;
    private static File fileAuthor;
    private static File fileBook;
    private static File filePublisher;

    @Test
    public void writeAuthor() throws Exception {
        assertTrue(serializer.writeAuthor(authors.get(0), fileAuthor));
    }

    @Test
    public void readAuthor() throws Exception {
        assertEquals(authors.get(0), serializer.readAuthor(fileAuthor));
    }

    @Test
    public void writeBook() throws Exception {
        assertTrue(serializer.writeBook(books.get(0), fileBook));
    }

    @Test
    public void readBook() throws Exception {
        assertEquals(books.get(0), serializer.readBook(fileBook));
    }

    @Test
    public void writePublisher() throws Exception {
        assertTrue(serializer.writePublisher(publishers.get(0),
                filePublisher));
    }

    @Test
    public void readPublisher() throws Exception {
        assertEquals(publishers.get(0),
                serializer.readPublisher(filePublisher));
    }

    @BeforeClass
    public static void initData() throws IOException {
        serializer = new TextSerializer();
        authors = new ArrayList<>();
        {
            authors.add(new Author("James",
                    LocalDate.of(1995, 8, 12),
                    Author.Sex.MALE));
            authors.add(new Author("Sue",
                    LocalDate.of(1920, 7, 11),
                    LocalDate.of(2002, 1, 1),
                    Author.Sex.FEMALE));
            authors.add(new Author("Hank",
                    LocalDate.of(1980, 2, 28),
                    Author.Sex.MALE));
            authors.add(new Author("Anna",
                    LocalDate.of(1911, 3, 13),
                    LocalDate.of(1988, 11, 22),
                    Author.Sex.FEMALE));
        }
        List<Author> listAuthorsBookSecretTime =
                Arrays.asList(authors.get(1), authors.get(3));
        List<Author> listAuthorsBookEllion =
                Arrays.asList(authors.get(0), authors.get(2));
        books = new ArrayList<>();
        books.add(new Book("Secret",
                LocalDate.of(1955, 4, 1),
                listAuthorsBookSecretTime));
        books.add(new Book("Ellion",
                LocalDate.of(2006, 11, 16),
                listAuthorsBookEllion));
        List<Book> booksOfWorldPublisher = Arrays.asList(
                books.get(0), books.get(1)
        );
        List<Book> booksOfEksmoPublisher = Collections.singletonList(
                books.get(0)
        );
        publishers = new ArrayList<>();
        publishers.add(new Publisher("World", booksOfWorldPublisher));
        publishers.add(new Publisher("Eksmo", booksOfEksmoPublisher));
        fileAuthor = new File("D:\\data\\author.txt");
        if (!fileAuthor.exists()) {
            fileAuthor.createNewFile();
        }
        fileBook = new File("D:\\data\\book.txt");
        if (!fileBook.exists()) {
            fileBook.createNewFile();
        }
        filePublisher = new File("D:\\data\\publisher.txt");
        if (!filePublisher.exists()) {
            filePublisher.createNewFile();
        }
    }

}