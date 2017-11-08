package task_twelve.util;

import java.io.File;
import java.sql.Connection;

public class InitDataBase {

    private ScriptExecutor executor = new ScriptExecutor();

    public void createUser(Connection connection) {
        File user = new File("src\\main\\resources\\sql\\createUser.sql");
        executor.runScript(connection, user);
    }

    public void createDataBase(Connection connection) {
        File fileCreate = new File(
                "src\\main\\resources\\sql\\createDB.sql");
        executor.runScript(connection, fileCreate);
    }


    public void createTables(Connection connection) {
        File fileAuthor = new File(
                "src\\main\\resources\\sql\\createTableAuthor.sql");
        File fileBook = new File(
                "src\\main\\resources\\sql\\createTableBook.sql");
        File filePublisher = new File(
                "src\\main\\resources\\sql\\createTablePublisher.sql");
        File fileBookAuthor = new File(
                "src\\main\\resources\\sql\\createTableBooksAuthors.sql");
        File filePublisherBook = new File(
                "src\\main\\resources\\sql\\createTablePublishersBooks.sql");
        executor.runScript(connection, fileAuthor);
        executor.runScript(connection, fileBook);
        executor.runScript(connection, filePublisher);
        executor.runScript(connection, fileBookAuthor);
        executor.runScript(connection, filePublisherBook);
    }

}
