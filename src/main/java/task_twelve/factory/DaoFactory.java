package task_twelve.factory;

import task_twelve.dao.entity.postgres.*;

public class DaoFactory {

    public static AuthorDao getAuthorDao() {
        return new AuthorDao();
    }

    public static BookDao getBookDao() {
        return new BookDao();
    }

    public static PublisherDao getPublisherDao() {
        return new PublisherDao();
    }

    public static PublisherBookDao getPublisherBookDao( ) {
        return new PublisherBookDao();
    }

    public static BookAuthorDao getBookAuthorDao( ) {
        return new BookAuthorDao();
    }
}
