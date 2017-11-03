package task_seven.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import task_four.second.domain.Author;
import task_seven.entity.EntityAuthor;
import task_seven.entity.EntityBook;
import task_seven.entity.EntityPublisher;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ValidatorTest {

    private final EntityAuthor author;
    private final boolean valAuthor;
    private final EntityBook book;
    private final boolean valBook;
    private final EntityPublisher publisher;
    private final boolean valPublisher;

    public ValidatorTest(EntityAuthor author, boolean valAuthor,
                         EntityBook book, boolean valBook,
                         EntityPublisher publisher, boolean valPublisher) {
        this.author = author;
        this.valAuthor = valAuthor;
        this.book = book;
        this.valBook = valBook;
        this.publisher = publisher;
        this.valPublisher = valPublisher;
    }

    @Parameterized.Parameters
    public static Collection setOfParametrs() {
        List<EntityPublisher> publishers;
        List<EntityBook> books;
        List<EntityAuthor> authors;
        authors = Arrays.asList(
                new EntityAuthor(1, null, null,
                        null, Author.Sex.FEMALE.name()),
                new EntityAuthor(2, "Ivan", "1995.1..3",
                        null, Author.Sex.MALE.name()),
                new EntityAuthor(3, "Anna",
                        LocalDate.of(1995, 5, 5).toString(),
                        LocalDate.now().toString(), Author.Sex.FEMALE.name())
        );
        books = Arrays.asList(
                new EntityBook(1, null,
                        null, Arrays.asList("1", "2")),
                new EntityBook(2, "Ice", "12.32;.32",
                        Arrays.asList("1", "2")),
                new EntityBook(3, "Life", LocalDate.now().toString(),
                        Arrays.asList("1", "2"))
        );
        publishers = Arrays.asList(
                new EntityPublisher(1, null, Arrays.asList("1", "2")),
                new EntityPublisher(2, "Smart",
                        new ArrayList<>()),
                new EntityPublisher(3, "Politeness",
                        Arrays.asList("1", "2"))
        );
        return Arrays.asList(new Object[][]{
                {
                        authors.get(0), false,
                        books.get(0), false,
                        publishers.get(0), false
                },
                {
                        authors.get(1), false,
                        books.get(1), false,
                        publishers.get(1), false
                },
                {
                        authors.get(2), true,
                        books.get(2), true,
                        publishers.get(2), true
                }
        });
    }

    @Test
    public void isValidAuthor() throws Exception {
        boolean validAuthor = (new Validator()).isValidAuthor(author);
        assertEquals(valAuthor, validAuthor);
    }

    @Test
    public void isValidBook() throws Exception {
        boolean validBook = (new Validator()).isValidBook(book);
        assertEquals(valBook, validBook);
    }

    @Test
    public void isValidPublisher() throws Exception {
        boolean validPublisher = (new Validator())
                .isValidPublisher(publisher);
        assertEquals(valPublisher, validPublisher);
    }

}