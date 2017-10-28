package task_seven.transformation;

import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_seven.entity.EntityAuthor;
import task_seven.entity.EntityBook;
import task_seven.entity.EntityPublisher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class consist of static methods,
 * which transform authors books and publishers to entity.
 * Also transform entity to objects, but entity must be validate.
 *
 * @author Eldar
 */
public class Transformer {

    public EntityAuthor transformAuthorToEntity(Author author) {
        String dateDeath = null;
        if (author.getDateOfDeath() != null) {
            dateDeath = author.getDateOfDeath().toString();
        }
        return new EntityAuthor(author.getName(),
                author.getDateOfBirth().toString(),
                dateDeath,
                author.getSex().name());
    }

    public Author transformEntityToAuthor(EntityAuthor entity) {
        LocalDate dateDeath = null;
        if (entity.getDateOfDeath() != null) {
            dateDeath = LocalDate.parse(entity.getDateOfDeath());
        }
        return new Author(entity.getName(),
                LocalDate.parse(entity.getDateOfBirth()),
                dateDeath, Author.Sex.parseSex(entity.getSex()));
    }

    public EntityBook transformBookToEntity(Book book) {
        List<EntityAuthor> authors = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            authors.add(new Transformer().transformAuthorToEntity(author));
        }
        return new EntityBook(book.getName(),
                book.getReleaseDate().toString(), authors);
    }

    public Book transformEntityToBook(EntityBook entity) {
        List<Author> authors = new ArrayList<>();
        for (EntityAuthor author : entity.getAuthors()) {
            authors.add(new Transformer().transformEntityToAuthor(author));
        }
        return new Book(entity.getName(),
                LocalDate.parse(entity.getReleaseDate()), authors);
    }

    public EntityPublisher transformPublisherToEntity(Publisher publisher) {
        List<EntityBook> books = new ArrayList<>();
        for (Book book : publisher.getPublishedBooks()) {
            books.add(new Transformer().transformBookToEntity(book));
        }
        return new EntityPublisher(publisher.getName(), books);
    }

    public Publisher transformEntityToPublisher(EntityPublisher entity) {
        List<Book> books = new ArrayList<>();
        for (EntityBook book : entity.getBooks()) {
            books.add(new Transformer().transformEntityToBook(book));
        }
        return new Publisher(entity.getName(), books);
    }

}
