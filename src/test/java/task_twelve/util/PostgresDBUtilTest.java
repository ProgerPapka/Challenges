package task_twelve.util;

import org.junit.BeforeClass;
import org.junit.Test;
import task_twelve.exception.DataBaseException;

import java.sql.Connection;

import static org.junit.Assert.*;

public class PostgresDBUtilTest {

    private static PostgresDBUtil util;

    @BeforeClass
    public static void init() throws DataBaseException {
        util = new PostgresDBUtil();
    }

    @Test
    public void getUrl() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/epam";
        assertEquals(url, util.getUrl());
    }

    @Test
    public void getUser() throws Exception {
        String name = "epam";
        assertEquals(name, util.getUser());
    }

    @Test
    public void getClassLoad() throws Exception {
        String classload = "org.postgresql.Driver";
        assertEquals(classload, util.getClassLoad());
    }

    @Test
    public void getConnection() throws Exception {
        Connection connection = util.getConnection();
        boolean check = false;
        if (connection != null) {
            check = true;
        }
        assertTrue(check);
    }

}