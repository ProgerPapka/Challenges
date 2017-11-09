package task_twelve.dao.entity;

import task_twelve.entity.EntityBook;

import java.sql.Connection;
import java.util.List;

public interface BookDao {

    boolean insert(EntityBook book, Connection connection);

    EntityBook selectById(int id, Connection connection);

    List<EntityBook> selectByName(String name, Connection connection);

    List<EntityBook> selectAll(Connection connection);
}
