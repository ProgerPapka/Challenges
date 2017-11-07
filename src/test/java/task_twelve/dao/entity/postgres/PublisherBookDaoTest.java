package task_twelve.dao.entity.postgres;

import org.junit.BeforeClass;
import org.junit.Test;
import task_twelve.entity.EntityPublisherBook;
import task_twelve.exception.DataBaseException;
import task_twelve.factory.DaoFactory;
import task_twelve.util.DataBaseUtil;
import task_twelve.util.PostgresDBUtil;

import static org.junit.Assert.*;

public class PublisherBookDaoTest {

    private static EntityPublisherBook publisherBook;
    private static PublisherBookDao dao;

    @BeforeClass
    public static void init() throws DataBaseException {
        publisherBook = new EntityPublisherBook(1,1);
        DataBaseUtil util = new PostgresDBUtil();
        dao = DaoFactory.getPublisherBookDao(util.getConnection());
    }

    @Test
    public void insertPublisherBook() throws Exception {
        assertTrue(dao.insertPublisherBook(publisherBook));
    }

    @Test
    public void selectByIdPublisher() throws Exception {
        EntityPublisherBook p = dao.selectByIdPublisher(1).get(0);
        assertEquals(publisherBook, p);
    }

}