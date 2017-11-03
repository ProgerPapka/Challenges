package task_seven.transformation;

import org.junit.BeforeClass;
import org.junit.Test;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_seven.entity.EntityAuthor;
import task_seven.entity.EntityBook;
import task_seven.entity.EntityPublisher;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class TransformerTest {

    private static EntityAuthor entityAuthor;
    private static EntityBook entityBook;
    private static EntityPublisher entityPublisher;
    private static Author author;
    private static Book book;
    private static Publisher publisher;
    private static Transformer transformer;

    @BeforeClass
    public static void initTestObjects() {
        author = new Author("Diana",
                LocalDate.of(1998, 11, 2),
                null, Author.Sex.FEMALE);
        book = new Book("My pleasure",
                LocalDate.of(2017, 8, 12),
                Collections.singletonList(author));
        publisher = new Publisher("Niks",
                Collections.singletonList(book));
        entityAuthor = new EntityAuthor(1, "Diana",
                LocalDate.of(1998, 11, 2).toString(),
                null, Author.Sex.FEMALE.name());
        entityBook = new EntityBook(1, "My pleasure",
                LocalDate.of(2017, 8, 12).toString(),
                Collections.singletonList("1"));
        entityPublisher = new EntityPublisher(1, "Niks",
                Collections.singletonList("1"));
        transformer = new Transformer();
    }

    @Test
    public void transformAuthorToEntity() throws Exception {
        EntityAuthor entity = transformer.transformAuthorToEntity(author);
        assertEquals(entityAuthor, entity);
    }

    @Test
    public void transformEntityToAuthor() throws Exception {
        Author a = transformer.transformEntityToAuthor(entityAuthor);
        assertEquals(author, a);
    }

    @Test
    public void transformBookToEntity() throws Exception {
        EntityBook entity = transformer.transformBookToEntity(book,
                Collections.singletonList(entityAuthor));
        assertEquals(entityBook, entity);
    }

    @Test
    public void transformEntityToBook() throws Exception {
        Book b = transformer.transformEntityToBook(entityBook,
                Collections.singletonList(entityAuthor));
        assertEquals(book, b);
    }

    @Test
    public void transformPublisherToEntity() throws Exception {
        EntityPublisher entity = transformer
                .transformPublisherToEntity(publisher,
                        Collections.singletonList(entityBook));
        assertEquals(entityPublisher, entity);
    }

    @Test
    public void transformEntityToPublisher() throws Exception {
        Publisher p = transformer
                .transformEntityToPublisher(entityPublisher,
                        Collections.singletonList(entityBook),
                        Collections.singletonList(entityAuthor)
                );
        assertEquals(publisher, p);
    }

}