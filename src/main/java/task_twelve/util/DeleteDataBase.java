package task_twelve.util;

import java.io.File;
import java.sql.Connection;

public class DeleteDataBase {

    private ScriptExecutor scriptExecutor = new ScriptExecutor();
    private Connection connection;

    public DeleteDataBase(Connection connection) {
        this.connection = connection;
    }

    public void dropDB() {
        File file = new File("src\\main\\resources\\sql\\dropDB.sql");
        scriptExecutor.runScript(connection, file);
    }

}
