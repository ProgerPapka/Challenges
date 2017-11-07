package task_twelve.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class InitDataBaseTest {

    @Test
    public void createUser() throws Exception {
        DataBaseUtil util = new PostgresDBUtil();
        InitDataBase init = new InitDataBase(util.getConnection());
        init.createUser();
        util.closeConnection();
    }

    @Test
    public void createDataBase() throws Exception {
        DataBaseUtil util = new PostgresDBUtil();
        InitDataBase init = new InitDataBase(util.getConnection());
        init.createDataBase();
        util.closeConnection();
    }

    @Test
    public void createTables() throws Exception {
        DataBaseUtil util = new PostgresDBUtil();
        InitDataBase init = new InitDataBase(util.getConnection());
        init.createTables();
        util.closeConnection();
    }

}