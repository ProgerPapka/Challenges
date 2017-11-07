package task_twelve.dao.entity.postgres;

import org.junit.BeforeClass;
import org.junit.Test;
import task_twelve.entity.EntityPublisher;
import task_twelve.exception.DataBaseException;
import task_twelve.factory.DaoFactory;
import task_twelve.util.DataBaseUtil;
import task_twelve.util.PostgresDBUtil;

import static org.junit.Assert.*;

public class PublisherDaoTest {

    private static PublisherDao dao;
    private static EntityPublisher publisher;

    @BeforeClass
    public static void init() throws DataBaseException {
        DataBaseUtil util = new PostgresDBUtil();
        dao = DaoFactory.getPublisherDao(util.getConnection());
        publisher = new EntityPublisher(
                1, "Gideon"
        );
    }

    @Test
    public void insertBook() throws Exception {
        assertTrue(dao.insertPublisher(publisher));
    }

    @Test
    public void selectPublisherById() throws Exception {
        EntityPublisher p = dao.selectPublisherById(1);
        assertEquals(publisher, p);
    }

    @Test
    public void selectPublisherByName() throws Exception {
        EntityPublisher p = dao.selectPublisherByName("Gideon").get(0);
        assertEquals(publisher, p);
    }

}