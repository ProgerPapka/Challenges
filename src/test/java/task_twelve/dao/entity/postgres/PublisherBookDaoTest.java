package task_twelve.dao.entity.postgres;

import org.junit.BeforeClass;
import org.junit.Test;
import task_twelve.dao.entity.PublisherBookDao;
import task_twelve.entity.EntityPublisherBook;
import task_twelve.exception.DataBaseException;
import task_twelve.factory.PostgresDaoFactory;
import task_twelve.util.DataBaseUtil;
import task_twelve.util.PostgresDBUtil;

import static org.junit.Assert.*;

public class PublisherBookDaoTest {

    private static EntityPublisherBook publisherBook;
    private static PublisherBookDao dao;
    private static DataBaseUtil util;

    @BeforeClass
    public static void init() throws DataBaseException {
        publisherBook = new EntityPublisherBook(1, 1);
        util = new PostgresDBUtil();
        dao = PostgresDaoFactory.getPublisherBookDao();
    }

    @Test
    public void insertPublisherBook() throws Exception {
        assertTrue(dao.insert(publisherBook, util.getConnection()));
    }

    @Test
    public void selectByIdPublisher() throws Exception {
        EntityPublisherBook p = dao.selectByIdPublisher(1,
                util.getConnection()).get(0);
        assertEquals(publisherBook, p);
    }

}