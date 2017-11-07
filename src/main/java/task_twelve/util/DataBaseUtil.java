package task_twelve.util;

import java.sql.Connection;

public abstract class DataBaseUtil {

    public abstract Connection getConnection();

    public abstract void closeConnection();

    public abstract String getUrl();

    public abstract String getUser();

    public abstract String getClassLoad();
}
