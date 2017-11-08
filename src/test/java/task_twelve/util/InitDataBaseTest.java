package task_twelve.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class InitDataBaseTest {

    @Test
    public void createUser() throws Exception {
        DataBaseUtil util = new PostgresDBUtil();
        InitDataBase init = new InitDataBase();
        init.createUser(util.getConnection());
        util.closeConnection();
    }

    @Test
    public void createDataBase() throws Exception {
        DataBaseUtil util = new PostgresDBUtil();
        InitDataBase init = new InitDataBase();
        init.createDataBase(util.getConnection());
        util.closeConnection();
    }

    @Test
    public void createTables() throws Exception {
        DataBaseUtil util = new PostgresDBUtil();
        InitDataBase init = new InitDataBase();
        init.createTables(util.getConnection());
        util.closeConnection();
    }

}