package task_eight.dom;

import org.junit.BeforeClass;
import org.junit.Test;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DomParserTest {

    private static Publisher publisher;

    @BeforeClass
    public static void initData() {
        List<Author> authors = Arrays.asList(
                new Author("Irina", LocalDate.of(1994, 5, 19),
                        null, Author.Sex.FEMALE),
                new Author("Eldar", LocalDate.of(1995, 8, 12),
                        null, Author.Sex.MALE),
                new Author("Henry", LocalDate.of(1925, 4, 15),
                        LocalDate.of(1986, 7, 11), Author.Sex.MALE)
        );
        List<Book> books = Arrays.asList(
                new Book("Heartbreaker", LocalDate.of(2017, 10, 29),
                        Arrays.asList(authors.get(0), authors.get(1))),
                new Book("Shantaram", LocalDate.of(2010, 2, 28),
                        Collections.singletonList(authors.get(2)))
        );
        publisher = new Publisher("Penguin", books);
    }

    @Test
    public void parsePublisher() throws Exception {
        String pathXML = "src\\main\\resources\\thebestofpublisher.xml";
        DomParser parser = new DomParser();
        Publisher testPublisher = parser.parsePublisher(pathXML);
        assertEquals(publisher, testPublisher);
    }

}