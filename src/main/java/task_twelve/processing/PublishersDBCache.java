package task_twelve.processing;

import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_twelve.dao.entity.*;
import task_twelve.dao.entity.postgres.*;
import task_twelve.entity.*;
import task_twelve.exception.DataBaseException;
import task_twelve.factory.PostgresDaoFactory;
import task_twelve.util.DataBaseUtil;
import task_twelve.util.PostgresDBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublishersDBCache {

    private DataBaseUtil util;
    private AuthorDao authorDao;
    private BookDao bookDao;
    private PublisherDao publisherDao;
    private BookAuthorDao bookAuthorDao;
    private PublisherBookDao publisherBookDao;
    private int idToAuthor = 1;
    private int idToBook = 1;
    private int idToPublisher = 1;

    public PublishersDBCache() throws DataBaseException {
        this(new PostgresDBUtil());
    }

    public PublishersDBCache(DataBaseUtil util) {
        this.authorDao = PostgresDaoFactory.getAuthorDao();
        this.bookDao = PostgresDaoFactory.getBookDao();
        this.publisherDao = PostgresDaoFactory.getPublisherDao();
        this.bookAuthorDao = PostgresDaoFactory.getBookAuthorDao();
        this.publisherBookDao = PostgresDaoFactory.getPublisherBookDao();
        this.util = util;
    }

    public void setToBD(List<Publisher> list) {
        Map<EntityPublisher, Integer> publishers = new HashMap<>();
        Map<EntityBook, Integer> books = new HashMap<>();
        Map<EntityAuthor, Integer> authors = new HashMap<>();
        List<EntityBookAuthor> bookAuthor = new ArrayList<>();
        List<EntityPublisherBook> publisherBook = new ArrayList<>();
        for (Publisher publisher : list) {
            int idPublisher = idToPublisher++;
            EntityPublisher p = new EntityPublisher(idPublisher,
                    publisher.getName());
            if (!publishers.containsKey(p)) {
                publishers.put(p, p.getId());
                for (Book book : publisher.getPublishedBooks()) {
                    int idBook = idToBook++;
                    EntityBook b = new EntityBook(
                            idBook, book.getName(), book.getReleaseDate());
                    if (!books.containsKey(b)) {
                        books.put(b, b.getId());
                        publisherBook.add(new EntityPublisherBook(idPublisher,
                                idBook));
                        for (Author author : book.getAuthors()) {
                            int idAuthor = idToAuthor++;
                            EntityAuthor a = new EntityAuthor(
                                    idAuthor, author.getName(),
                                    author.getDateOfBirth(),
                                    author.getDateOfDeath(),
                                    author.getSex().name()
                            );
                            if (!authors.containsKey(a)) {
                                authors.put(a, a.getId());
                                bookAuthor.add(new EntityBookAuthor(idBook,
                                        idAuthor));
                            } else {
                                bookAuthor.add(new EntityBookAuthor(idBook,
                                        authors.get(a)));
                            }
                        }
                    } else {
                        publisherBook.add(new EntityPublisherBook(idPublisher,
                                books.get(b)));
                    }
                }
            }
        }
        for (EntityAuthor author : authors.keySet()) {
            authorDao.insert(author, util.getConnection());
        }
        for (EntityBook book : books.keySet()) {
            bookDao.insert(book, util.getConnection());
        }
        for (EntityPublisher publisher : publishers.keySet()) {
            publisherDao.insert(publisher, util.getConnection());
        }
        for (EntityBookAuthor ba : bookAuthor) {
            bookAuthorDao.insert(ba, util.getConnection());
        }
        for (EntityPublisherBook pb : publisherBook) {
            publisherBookDao.insert(pb, util.getConnection());
        }
    }

    public List<Publisher> getFromDB() {
        return getPublishersFromDB();
    }


    private List<Publisher> getPublishersFromDB() {
        List<EntityPublisher> publishers = publisherDao.selectAll(
                util.getConnection());
        List<Publisher> result = new ArrayList<>();
        for(EntityPublisher publisher : publishers){
            result.add(getPublisher(publisher));
        }
        return result;
    }

    private Publisher getPublisher(EntityPublisher publisher) {
        List<EntityPublisherBook> publisherBook = publisherBookDao
                .selectByIdPublisher(publisher.getId(), util.getConnection());
        List<Book> books = new ArrayList<>();
        for (EntityPublisherBook p : publisherBook) {
            EntityBook book = bookDao
                    .selectById(p.getIdBook(), util.getConnection());
            if (book != null) {
                books.add(getBook(book));
            }
        }
        return new Publisher(publisher.getName(), books);
    }

    private Book getBook(EntityBook book) {
        List<EntityBookAuthor> bookAuthor = bookAuthorDao
                .selectByIdBook(book.getId(), util.getConnection());
        List<Author> authors = new ArrayList<>();
        for (EntityBookAuthor ba : bookAuthor) {
            EntityAuthor author = authorDao
                    .selectById(ba.getIdAuthor(), util.getConnection());
            if (author != null) {
                authors.add(new Author(
                        author.getName(), author.getbDay(),
                        author.getdDay(), Author.Sex.valueOf(author.getSex())
                ));
            }
        }
        return new Book(book.getName(), book.getrDay(), authors);
    }

}
