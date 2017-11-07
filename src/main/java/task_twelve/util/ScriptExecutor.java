package task_twelve.util;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ScriptExecutor {

    private static Logger logger = Logger.getLogger(ScriptExecutor.class);

    public void runScript(Connection connection, File file){
        ScriptRunner runner = new ScriptRunner(connection);
        try {
            String sql = new String(Files.readAllBytes(Paths.get(file.getPath())));
            Statement stm = connection.createStatement();
            stm.execute(sql);
        } catch (IOException | SQLException e) {
            logger.error(e);
        }
    }

    public void runScript(Connection connection, String sql){
        try {
            Statement stm = connection.createStatement();
            stm.execute(sql);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

}
