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
    private static DataBaseUtil util;

    @BeforeClass
    public static void init() throws DataBaseException {
        util = new PostgresDBUtil();
        dao = DaoFactory.getPublisherDao();
        publisher = new EntityPublisher(
                1, "Gideon"
        );
    }

    @Test
    public void insertPublisher() throws Exception {
        assertTrue(dao.insert(publisher, util.getConnection()));
    }

    @Test
    public void selectPublisherById() throws Exception {
        EntityPublisher p = dao.selectById(1, util.getConnection());
        assertEquals(publisher, p);
    }

    @Test
    public void selectPublisherByName() throws Exception {
        EntityPublisher p = dao.selectByName("Gideon",
                util.getConnection()).get(0);
        assertEquals(publisher, p);
    }

}