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

    public static EntityAuthor transformAuthorToEntity(Author author) {
        if (author.getDateOfDeath() != null) {
            return new EntityAuthor(author.getName(),
                    author.getDateOfBirth().toString(),
                    author.getDateOfDeath().toString(),
                    author.getSex().name());
        } else {
            return new EntityAuthor(author.getName(),
                    author.getDateOfBirth().toString(),
                    null,
                    author.getSex().name());
        }
    }

    public static Author transformEntityToAuthor(EntityAuthor entity) {
        Author author;
        if (entity.getDateOfDeath() == null) {
            author = new Author(entity.getName(),
                    LocalDate.parse(entity.getDateOfBirth()),
                    null, Author.Sex.parseSex(entity.getSex()));
        } else {
            author = new Author(entity.getName(),
                    LocalDate.parse(entity.getDateOfBirth()),
                    LocalDate.parse(entity.getDateOfDeath()),
                    Author.Sex.parseSex(entity.getSex()));
        }
        return author;
    }

    public static EntityBook transformBookToEntity(Book book) {
        List<EntityAuthor> authors = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            authors.add(Transformer.transformAuthorToEntity(author));
        }
        return new EntityBook(book.getName(),
                book.getReleaseDate().toString(), authors);
    }

    public static Book transformEntityToBook(EntityBook entity) {
        List<Author> authors = new ArrayList<>();
        for (EntityAuthor author : entity.getAuthors()) {
            authors.add(Transformer.transformEntityToAuthor(author));
        }
        return new Book(entity.getName(),
                LocalDate.parse(entity.getReleaseDate()), authors);
    }

    public static EntityPublisher transformPublisherToEntity(Publisher publisher) {
        List<EntityBook> books = new ArrayList<>();
        for (Book book : publisher.getPublishedBooks()) {
            books.add(Transformer.transformBookToEntity(book));
        }
        return new EntityPublisher(publisher.getName(), books);
    }

    public static Publisher transformEntityToPublisher(EntityPublisher entity) {
        List<Book> books = new ArrayList<>();
        for (EntityBook book : entity.getBooks()) {
            books.add(Transformer.transformEntityToBook(book));
        }
        return new Publisher(entity.getName(), books);
    }

}
