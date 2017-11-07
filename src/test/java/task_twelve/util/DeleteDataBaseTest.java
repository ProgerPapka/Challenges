package task_twelve.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteDataBaseTest {
    @Test
    public void dropDB() throws Exception {
        DataBaseUtil util = new PostgresDBUtil();
        DeleteDataBase deleter = new DeleteDataBase(util.getConnection());
        deleter.dropDB();
    }

}