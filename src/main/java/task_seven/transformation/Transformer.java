package task_seven.transformation;

import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_seven.entity.EntityAuthor;
import task_seven.entity.EntityBook;
import task_seven.entity.EntityPublisher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class consist of static methods,
 * which transform authors books and publishers to entity.
 * Also transform entity to objects, but entity must be validate.
 *
 * @author Eldar
 */
public class Transformer {

    private static int idToAuthors = 1;
    private static int idToBooks = 1;
    private static int idToPublisher = 1;

    private Map<EntityAuthor, Integer> cacheEntityAuthors = new HashMap<>();
    private Map<EntityBook, Integer> cacheEntityBooks = new HashMap<>();

    public EntityAuthor transformAuthorToEntity(Author author) {
        String dateDeath = null;
        if (author.getDateOfDeath() != null) {
            dateDeath = author.getDateOfDeath().toString();
        }
        EntityAuthor entity = new EntityAuthor(author.getName(),
                author.getDateOfBirth().toString(),
                dateDeath,
                author.getSex().name());
        generateIdAuthor(entity);
        return entity;
    }

    private void generateIdAuthor(EntityAuthor entity){
        int id;
        if (cacheEntityAuthors.containsKey(entity)) {
            id = cacheEntityAuthors.get(entity);
            entity.setId(id);
        } else {
            entity.setId(idToAuthors++);
            cacheEntityAuthors.put(entity, entity.getId());
        }
    }

    public Author transformEntityToAuthor(EntityAuthor entity) {
        LocalDate dateDeath = null;
        if (entity.getDateOfDeath() != null) {
            dateDeath = LocalDate.parse(entity.getDateOfDeath());
        }
        return new Author(entity.getName(),
                LocalDate.parse(entity.getDateOfBirth()),
                dateDeath, Author.Sex.valueOf(entity.getSex()));
    }


    public EntityBook transformBookToEntity(Book book,
                                            List<EntityAuthor> authors) {
        List<String> ids = new ArrayList<>();
        for (EntityAuthor author : authors) {
            ids.add(String.valueOf(author.getId()));
        }
        EntityBook entity = new EntityBook(book.getName(),
                book.getReleaseDate().toString());
        entity.setListIdAuthors(ids);
        generateIdBook(entity);
        return entity;
    }

    private void generateIdBook(EntityBook entity){
        int id;
        if (cacheEntityBooks.containsKey(entity)) {
            id = cacheEntityBooks.get(entity);
            entity.setId(id);
        } else {
            entity.setId(idToBooks++);
            cacheEntityBooks.put(entity, entity.getId());
        }
    }

    public Book transformEntityToBook(EntityBook entity,
                                      List<EntityAuthor> entityAuthors) {
        List<Author> authors = new ArrayList<>();
        for (EntityAuthor author : entityAuthors) {
            if (entity.containsAuthor(String.valueOf(author.getId()))) {
                authors.add(new Transformer().transformEntityToAuthor(author));
            }
        }
        return new Book(entity.getName(),
                LocalDate.parse(entity.getReleaseDate()), authors);
    }

    public EntityPublisher transformPublisherToEntity(Publisher publisher,
                                                      List<EntityBook> books) {
        List<String> ids = new ArrayList<>();
        for (EntityBook book : books) {
            ids.add(String.valueOf(book.getId()));
        }
        return new EntityPublisher(idToPublisher++, publisher.getName(), ids);
    }

    public Publisher transformEntityToPublisher(EntityPublisher entity,
                                                List<EntityBook> entityBooks,
                                                List<EntityAuthor> entityAuthors) {
        List<Book> books = new ArrayList<>();
        for (EntityBook book : entityBooks) {
            if (entity.containsBook(String.valueOf(book.getId()))) {
                books.add(new Transformer().transformEntityToBook(book,
                        entityAuthors));
            }
        }
        return new Publisher(entity.getName(), books);
    }

}
