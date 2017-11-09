package task_twelve.dao.entity;

import task_twelve.entity.EntityBookAuthor;

import java.sql.Connection;
import java.util.List;

public interface BookAuthorDao {

    boolean insert(EntityBookAuthor bookAuthor, Connection connection);

    List<EntityBookAuthor> selectByIdBook(int idBook, Connection connection);

    List<EntityBookAuthor> selectAll(Connection connection);
}
