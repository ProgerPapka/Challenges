package task_twelve.dao.entity.postgres;

import org.junit.BeforeClass;
import org.junit.Test;
import task_twelve.entity.EntityBookAuthor;
import task_twelve.exception.DataBaseException;
import task_twelve.factory.DaoFactory;
import task_twelve.util.DataBaseUtil;
import task_twelve.util.PostgresDBUtil;

import java.sql.Connection;

import static org.junit.Assert.*;

public class BookAuthorDaoTest {

    private static BookAuthorDao dao;
    private static EntityBookAuthor bookAuthor;
    private static DataBaseUtil util;

    @BeforeClass
    public static void init() throws DataBaseException {
        util = new PostgresDBUtil();
        dao = DaoFactory.getBookAuthorDao();
        bookAuthor = new EntityBookAuthor(1, 1);
    }

    @Test
    public void insertBookAuthor() throws Exception {
        assertTrue(dao.insert(bookAuthor, util.getConnection()));
    }

    @Test
    public void selectByIdBook() throws Exception {
        EntityBookAuthor b = dao.selectByIdBook(1,
                util.getConnection()).get(0);
        assertEquals(bookAuthor, b);
    }

}