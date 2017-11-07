package task_twelve.transformation;

import org.apache.log4j.Logger;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_twelve.entity.*;
import task_twelve.exception.DataBaseException;
import task_twelve.wrapper.EntityWrapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntityTransformer {

    private static Logger logger = Logger.getLogger(EntityTransformer.class);

    private int idToAuthor;
    private int idToBook;
    private int idToPublisher;

    public EntityTransformer(int idToAuthor, int idToBook, int idToPublisher) {
        this.idToAuthor = idToAuthor;
        this.idToBook = idToBook;
        this.idToPublisher = idToPublisher;
    }

    public EntityTransformer() {
        this(1, 1, 1);
    }

    public EntityTransformer(Connection connection) throws DataBaseException {
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT max(id) as id FROM \"Author\"");
            rs.next();
            idToAuthor = rs.getInt("id") + 1;
            rs = stm.executeQuery("SELECT max(id) as id FROM \"Book\"");
            rs.next();
            idToBook = rs.getInt("id") + 1;
            rs = stm.executeQuery("SELECT max(id) as id FROM \"Publisher\"");
            rs.next();
            idToPublisher = rs.getInt("id") + 1;
        } catch (SQLException e) {
            logger.error(e);
            throw new DataBaseException("Don't get connection error", e);
        }
    }

    public EntityWrapper transformPublisherToEntity(List<Publisher> list) {
        List<EntityPublisher> publishers = new ArrayList<>();
        List<EntityBook> books = new ArrayList<>();
        List<EntityAuthor> authors = new ArrayList<>();
        List<EntityBookAuthor> bookAuthor = new ArrayList<>();
        List<EntityPublisherBook> publisherBook = new ArrayList<>();
        for (Publisher publisher : list) {
            int idPublisher = idToPublisher++;
            publishers.add(new EntityPublisher(idPublisher,
                    publisher.getName()));
            for (Book book : publisher.getPublishedBooks()) {
                int idBook = idToBook++;
                books.add(new EntityBook(
                        idBook, book.getName(), book.getReleaseDate()));
                publisherBook.add(new EntityPublisherBook(idPublisher, idBook));
                for (Author author : book.getAuthors()) {
                    int idAuthor = idToAuthor++;
                    authors.add(new EntityAuthor(
                            idAuthor, author.getName(), author.getDateOfBirth(),
                            author.getDateOfDeath(), author.getSex().name()
                    ));
                    bookAuthor.add(new EntityBookAuthor(idBook, idAuthor));
                }
            }
        }
        return new EntityWrapper(authors, books, publishers,
                bookAuthor, publisherBook);
    }

    public List<Publisher> transformEntityToPublishers(EntityWrapper wrapper) {
        List<Publisher> publishers = new ArrayList<>();
        for (EntityPublisher publisher : wrapper.getPublishers()) {
            int idPublisher = publisher.getId();
            List<Book> books = new ArrayList<>();
            for(EntityPublisherBook publisherBook : wrapper.getPublishersBooks()) {
                if(idPublisher != publisherBook.getIdPublisher()){
                    continue;
                }
                int idBook = publisherBook.getIdBook();
                for (EntityBook book : wrapper.getBooks()) {
                    if(idBook != book.getId()){
                        continue;
                    }
                    List<Author> authors = new ArrayList<>();
                    for (EntityBookAuthor bookAuthor : wrapper.getBooksAuthors()) {
                        if (idBook != bookAuthor.getIdBook()) {
                            continue;
                        }
                        int idAuthor = bookAuthor.getIdAuthor();
                        for (EntityAuthor author : wrapper.getAuthors()) {
                            if (idAuthor != author.getId()) {
                                continue;
                            }
                            authors.add(new Author(
                                    author.getName(),
                                    author.getbDay(),
                                    author.getdDay(),
                                    Author.Sex.valueOf(author.getSex())
                            ));
                        }
                    }
                    books.add(new Book(
                            book.getName(),
                            book.getrDay(),
                            authors
                    ));
                }
            }
            publishers.add(new Publisher(publisher.getName(),books));
        }
        return publishers;
    }

}
