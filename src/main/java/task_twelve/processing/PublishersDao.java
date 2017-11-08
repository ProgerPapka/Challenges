package task_twelve.processing;

import task_four.second.domain.Publisher;
import task_twelve.dao.entity.postgres.*;
import task_twelve.entity.*;
import task_twelve.exception.DataBaseException;
import task_twelve.transformation.EntityTransformer;
import task_twelve.wrapper.EntityWrapper;

import java.sql.Connection;
import java.util.List;

public class PublishersDao {

    private Connection connection;
    private EntityTransformer transformer;
    private AuthorDao authorDao;
    private BookDao bookDao;
    private PublisherDao publisherDao;
    private BookAuthorDao bookAuthorDao;
    private PublisherBookDao publisherBookDao;

    public PublishersDao(Connection connection) throws DataBaseException {
        this(connection, new EntityTransformer(connection));
    }

    public PublishersDao(Connection connection, EntityTransformer transformer) {
        this.connection = connection;
        this.transformer = transformer;
        this.authorDao = new AuthorDao(connection);
        this.bookDao = new BookDao(connection);
        this.publisherDao = new PublisherDao(connection);
        this.bookAuthorDao = new BookAuthorDao(connection);
        this.publisherBookDao = new PublisherBookDao(connection);
    }

    public void setToBD(List<Publisher> publishers) {
        EntityWrapper wrapper = transformer.transformPublisherToEntity(publishers);
        for (EntityAuthor author : wrapper.getAuthors()) {
            authorDao.insertAuthor(author);
        }
        for (EntityBook book : wrapper.getBooks()) {
            bookDao.insertBook(book);
        }
        for (EntityPublisher publisher : wrapper.getPublishers()) {
            publisherDao.insertPublisher(publisher);
        }
        for (EntityBookAuthor bookAuthor : wrapper.getBooksAuthors()) {
            bookAuthorDao.insertBookAuthor(bookAuthor);
        }
        for (EntityPublisherBook publisherBook : wrapper.getPublishersBooks()) {
            publisherBookDao.insertPublisherBook(publisherBook);
        }
    }

    public List<Publisher> getFromDB() {
        List<EntityAuthor> authors = authorDao.selectAll();
        List<EntityBook> books = bookDao.selectAll();
        List<EntityPublisher> publishers = publisherDao.selectAll();
        List<EntityBookAuthor> bookAuthors = bookAuthorDao.selectAll();
        List<EntityPublisherBook> publisherBooks = publisherBookDao.selectAll();
        EntityWrapper wrapper = new EntityWrapper(
                authors, books, publishers, bookAuthors, publisherBooks
        );
        return transformer.transformEntityToPublishers(wrapper);
    }

}
