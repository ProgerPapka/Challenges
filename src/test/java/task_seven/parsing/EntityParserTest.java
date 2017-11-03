package task_seven.parsing;

import org.junit.BeforeClass;
import org.junit.Test;
import task_seven.entity.EntityAuthor;
import task_seven.entity.EntityBook;
import task_seven.entity.EntityPublisher;
import task_seven.exception.ParseException;

import java.util.Collections;

import static org.junit.Assert.*;

public class EntityParserTest {

    private static EntityAuthor entityAuthor;
    private static EntityBook entityBook;
    private static EntityPublisher entityPublisher;

    private static StringBuilder author;
    private static String authorString;
    private static StringBuilder book;
    private static String bookString;
    private static StringBuilder publisher;
    private static String publisherString;

    @BeforeClass
    public static void initData() {
        author = new StringBuilder();
        book = new StringBuilder();
        publisher = new StringBuilder();
        author.append(EntityAuthor.START_AUTHOR).append("\n\t");
        author.append(EntityAuthor.ID).append(EntityAuthor.EQUALITY)
                .append("11").append("\n\t");
        author.append(EntityAuthor.NAME).append(EntityAuthor.EQUALITY)
                .append("Vasya").append("\n\t");
        author.append(EntityAuthor.BIRTH_DAY).append(EntityAuthor.EQUALITY)
                .append("1891-1-12").append("\n\t");
        author.append(EntityAuthor.DEATH_DAY).append(EntityAuthor.EQUALITY)
                .append("1961-1-12").append("\n\t");
        author.append(EntityAuthor.SEX).append(EntityAuthor.EQUALITY)
                .append("MALE").append("\n").append(EntityAuthor.END_AUTHOR);
        book.append(EntityBook.START_BOOK).append("\n\t");
        book.append(EntityBook.ID).append(EntityBook.EQUALITY)
                .append("1").append("\n\t");
        book.append(EntityBook.NAME).append(EntityBook.EQUALITY)
                .append("HeartStone").append("\n\t");
        book.append(EntityBook.RELEASE_DAY).append(EntityBook.EQUALITY)
                .append("1955-5-1").append("\n\t");
        book.append(EntityBook.START_LIST_ID_AUTHORS)
                .append(EntityBook.START_LIST_ID).append("\n\t");
        book.append("1").append("\n\t").append(EntityBook.END_LIST_ID_AUTHORS)
                .append("\n").append(EntityBook.END_BOOK);
        publisher.append(EntityPublisher.START_PUBLISHER).append("\n\t")
                .append(EntityPublisher.ID).append(EntityPublisher.EQUALITY)
                .append("12").append("\n\t").append(EntityPublisher.NAME)
                .append(EntityPublisher.EQUALITY).append("Queer").append("\n\t")
                .append(EntityPublisher.START_LIST_ID_BOOKS)
                .append(EntityPublisher.START_LIST_ID).append("\n\t")
                .append("1").append("\n\t").append(EntityPublisher.END_LIST_ID_BOOKS)
                .append("\n").append(EntityPublisher.END_PUBLISHER);
        authorString = EntityAuthor.ID;
        bookString = EntityBook.START_LIST_ID_AUTHORS;
        publisherString = EntityPublisher.NAME;
        entityAuthor = new EntityAuthor(
                11, "Vasya", "1891-1-12",
                "1961-1-12", "MALE"
        );
        entityBook = new EntityBook(
                1, "HeartStone", "1955-5-1",
                Collections.singletonList("1")
        );
        entityPublisher = new EntityPublisher(
                12, "Queer",
                Collections.singletonList("1")
        );
    }

    @Test
    public void parseAuthor() throws Exception {
        EntityAuthor a = EntityParser.parseAuthor(author.toString());
        assertEquals(entityAuthor, a);
    }

    @Test(expected = ParseException.class)
    public void parseAuthorWithException() throws Exception {
        EntityParser.parseAuthor(authorString);
    }

    @Test
    public void parseBook() throws Exception {
        EntityBook b = EntityParser.parseBook(book.toString());
        assertEquals(entityBook, b);
    }

    @Test(expected = ParseException.class)
    public void parseBookWithException() throws Exception {
        EntityParser.parseBook(bookString);
    }

    @Test
    public void parsePublisher() throws Exception {
        EntityPublisher p = EntityParser
                .parsePublisher(publisher.toString());
        assertEquals(entityPublisher, p);
    }

    @Test(expected = ParseException.class)
    public void parsePublisherWithException() throws Exception {
        EntityParser.parsePublisher(publisherString);

    }

}