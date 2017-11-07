package task_twelve.util;

import java.io.File;
import java.sql.Connection;

public class InitDataBase {

    private Connection connection;
    private ScriptExecutor executor = new ScriptExecutor();

    public InitDataBase(Connection connection) {
        this.connection = connection;
    }

    public void createUser() {
        File user = new File("src\\main\\resources\\sql\\createUser.sql");
        executor.runScript(connection, user);
    }

    public void createDataBase() {
        File fileCreate = new File(
                "src\\main\\resources\\sql\\createDB.sql");
        executor.runScript(connection, fileCreate);
    }


    public void createTables() {
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
