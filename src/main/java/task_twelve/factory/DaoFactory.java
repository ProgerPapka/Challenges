package task_twelve.factory;

import task_twelve.dao.entity.postgres.*;

import java.sql.Connection;

public class DaoFactory {

    public static AuthorDao getAuthorDao(Connection connection) {
        return (connection != null) ? new AuthorDao(connection) : null;
    }

    public static BookDao getBookDao(Connection connection) {
        return (connection != null) ? new BookDao(connection) : null;
    }

    public static PublisherDao getPublisherDao(Connection connection) {
        return (connection != null) ? new PublisherDao(connection) : null;
    }

    public static PublisherBookDao getPublisherBookDao(Connection connection) {
        return (connection != null) ? new PublisherBookDao(connection) : null;
    }

    public static BookAuthorDao getBookAuthorDao(Connection connection) {
        return (connection != null) ? new BookAuthorDao(connection) : null;
    }
}
