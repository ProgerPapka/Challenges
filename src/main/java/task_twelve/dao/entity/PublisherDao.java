package task_twelve.dao.entity;

import task_twelve.entity.EntityPublisher;

import java.sql.Connection;
import java.util.List;

public interface PublisherDao {

    boolean insert(EntityPublisher publisher, Connection connection);

    EntityPublisher selectById(int id, Connection connection);

    List<EntityPublisher> selectByName(String name, Connection connection);

    List<EntityPublisher> selectAll(Connection connection);
}
