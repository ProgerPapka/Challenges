package task_twelve.dao.entity.postgres;

import org.junit.BeforeClass;
import org.junit.Test;
import task_twelve.entity.EntityBook;
import task_twelve.exception.DataBaseException;
import task_twelve.factory.DaoFactory;
import task_twelve.util.DataBaseUtil;
import task_twelve.util.PostgresDBUtil;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BookDaoTest {

    private static BookDao bookDao;
    private static EntityBook book;
    private static DataBaseUtil util;

    @BeforeClass
    public static void init() throws DataBaseException {
        util = new PostgresDBUtil();
        bookDao = DaoFactory.getBookDao();
        book = new EntityBook(
                1, "HeartStone", LocalDate.now()
        );
    }

    @Test
    public void insertBook() throws Exception {
        assertTrue(bookDao.insert(book, util.getConnection()));
    }

    @Test
    public void selectBookById() throws Exception {
        EntityBook b = bookDao.selectById(1, util.getConnection());
        assertEquals(book, b);
    }

    @Test
    public void selectBookByName() throws Exception {
        EntityBook b = bookDao.selectByName("HeartStone",
                util.getConnection()).get(0);
        assertEquals(book, b);
    }

}