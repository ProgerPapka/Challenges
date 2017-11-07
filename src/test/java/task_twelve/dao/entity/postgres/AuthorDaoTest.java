package task_twelve.dao.entity.postgres;

import org.junit.BeforeClass;
import org.junit.Test;
import task_four.second.domain.Author;
import task_twelve.entity.EntityAuthor;
import task_twelve.exception.DataBaseException;
import task_twelve.factory.DaoFactory;
import task_twelve.util.DataBaseUtil;
import task_twelve.util.PostgresDBUtil;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AuthorDaoTest {

    private static AuthorDao authorDao;
    private static EntityAuthor author;

    @BeforeClass
    public static void init() throws DataBaseException {
        DataBaseUtil util = new PostgresDBUtil();
        authorDao = DaoFactory.getAuthorDao(util.getConnection());
        author = new EntityAuthor(
                1, "Ivan", LocalDate.of(1995, 5, 6),
                null, Author.Sex.MALE.name()
        );
    }

    @Test
    public void insertAuthor() throws Exception {
        assertTrue(authorDao.insertAuthor(author));
    }

    @Test
    public void selectById() throws Exception {
        EntityAuthor a = authorDao.selectById(1);
        assertEquals(author, a);
    }

    @Test
    public void selectByName() throws Exception {
        EntityAuthor a = authorDao.selectByName("Ivan").get(0);
        assertEquals(author ,a);
    }

}