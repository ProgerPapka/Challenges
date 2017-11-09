package task_twelve.dao.entity;

import task_twelve.entity.EntityAuthor;

import java.sql.Connection;
import java.util.List;

public interface AuthorDao {

    boolean insert(EntityAuthor author, Connection connection);

    EntityAuthor selectById(int id, Connection connection);

    List<EntityAuthor> selectByName(String name, Connection connection);

    List<EntityAuthor> selectAll(Connection connection);
}
