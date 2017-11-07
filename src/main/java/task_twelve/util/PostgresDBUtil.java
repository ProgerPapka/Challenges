package task_twelve.util;

import org.apache.log4j.Logger;
import task_twelve.exception.DataBaseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDBUtil extends DataBaseUtil {

    private static Logger logger = Logger.getLogger(PostgresDBUtil.class);

    private String url;
    private String user;
    private String password;
    private String classLoad;

    private Connection connection;

    public PostgresDBUtil(String pathPropertiesDB) throws DataBaseException {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(pathPropertiesDB)) {
            prop.load(input);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            classLoad = prop.getProperty("class");

            Class.forName(classLoad);

            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            logger.error(e);
            throw new DataBaseException("Class Load error", e);
        } catch (SQLException e) {
            logger.error(e);
            throw new DataBaseException("Don't get connection error", e);
        } catch (IOException e) {
            logger.error(e);
            throw new DataBaseException("Properties file error", e);
        }
    }

    public PostgresDBUtil() throws DataBaseException {
        this("src\\main\\resources\\postgres.properties");
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() {
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getClassLoad() {
        return classLoad;
    }
}
