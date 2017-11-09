package task_twelve.factory;

import task_twelve.dao.entity.*;
import task_twelve.dao.entity.postgres.*;

public class PostgresDaoFactory {

    public static AuthorDao getAuthorDao() {
        return new ImplAuthorDao();
    }

    public static BookDao getBookDao() {
        return new ImplBookDao();
    }

    public static PublisherDao getPublisherDao() {
        return new ImplPublisherDao();
    }

    public static PublisherBookDao getPublisherBookDao( ) {
        return new ImplPublisherBookDao();
    }

    public static BookAuthorDao getBookAuthorDao( ) {
        return new ImplBookAuthorDao();
    }
}
